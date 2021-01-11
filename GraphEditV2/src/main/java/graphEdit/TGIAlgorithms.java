package graphEdit;

import graphEdit.editStrategies.AddEdgeStrategy;
import graphEdit.editStrategies.RemoveVertexStrategy;
import graphEdit.editStrategies.SetFinalStrategy;
import graphEdit.editStrategies.SetStartVertexStrategy;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Graph;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;
import java.util.*;

public class TGIAlgorithms {
    private final MainGUI gui;
    private final Graph graph;

    /**
     * @param gui the gui to be later updated
     */
    public TGIAlgorithms(MainGUI gui) {
        this.gui = gui;
        this.graph = gui.graph;
    }

    /**
     * minimizes a DEA
     */
    public void minimize() {

        //first check if there is a start vertex
        if (graph.getStartVertex() == null) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            return;
        }

        removeUnnecassaryVertices();

        //the equivalency classes are stroed as sets
        Set<Set<Vertex>> equivalencyClasses = getEquivalencyClasses();

        if (equivalencyClasses == null) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            return;
        }

        applyMinimizeToGUI(equivalencyClasses);

    }

    /**
     * gets the equi classes and applies them to the gui
     * @param equivalencyClasses
     */
    private void applyMinimizeToGUI(Set<Set<Vertex>> equivalencyClasses) {
        Set<Edge> edges = new HashSet<>(graph.getEdges());

        //rename and apply vertex attributes
        for (Set<Vertex> equivalencyClass : equivalencyClasses) {
            Vertex representative = equivalencyClass.iterator().next();
            JButton representativeButton = gui.buttonVertexBiMap.inverse().get(representative);
            String newName = getEquivalencyClassName(equivalencyClass);
            for (Vertex vertex : equivalencyClass) {
                if (vertex.equals(representative)) continue;
                new RemoveVertexStrategy(gui).editGGraph(gui.buttonVertexBiMap.inverse().get(vertex));
            }
            representative.setName(newName);
            representativeButton.setText(newName);
            if (equivalencyClass.contains(graph.getStartVertex())) {
                new SetStartVertexStrategy(gui).setNewStart(representative);
            }
        }

        //restore old edges
        for (Edge edge : edges) {
            Vertex start = graph.getVertexByName(edge.getStartVertex().getName());
            Vertex end = graph.getVertexByName(edge.getEndVertex().getName());
            char symbol = edge.getSymbol();
            new AddEdgeStrategy(gui).placeEdge(new Edge(start, end, symbol));
        }
    }

    /**
     * get a equivalency class and returns a name
     * like a class contains q0, q1, q3 them out put will be "{q0, q1, q3}"
     * @param equivalencyClass
     * @return
     */
    private String getEquivalencyClassName(Set<Vertex> equivalencyClass) {
        String newName = "{";
        for (Vertex vertex : equivalencyClass) {
            newName = newName + "" + vertex.getName() + ", ";
        }
        return newName.substring(0, newName.length() - 2) + "}";
    }

    /**
     * @return the equivalency classes
     */
    private Set<Set<Vertex>> getEquivalencyClasses() {
        Set<Character> transitionSymbols = getTransitionSymbols();
        Set<Vertex> finalVertices = new HashSet<>(graph.getFinalVertices());
        Set<Vertex> nonFinalVertices = new HashSet<>(graph.getNonFinalVertices());
        Set<Set<Vertex>> equivalencyClasses = new HashSet<>();
        equivalencyClasses.add(finalVertices);
        equivalencyClasses.add(nonFinalVertices);

        boolean finished = false;
        int wordLength = 1;
        while (!finished) {
            finished = true;


            for (String s : getAllWordsOfLengthN(transitionSymbols, wordLength)) {
                Set<Set<Vertex>> tempEquivalencyClasses = new HashSet<>();
                for (Set<Vertex> equivalencyClass : equivalencyClasses) {
                    Set<Vertex> tempFinalVertices = new HashSet<>();
                    Set<Vertex> tempNonFinalVertices = new HashSet<>();
                    for (Vertex vertex : equivalencyClass) {
                        if (isFinalAfterExecution(vertex, s)) {
                            tempFinalVertices.add(vertex);
                        } else {
                            tempNonFinalVertices.add(vertex);
                        }
                    }
                    if (tempFinalVertices.size() > 0 & tempNonFinalVertices.size() > 0)
                        finished = false; //split happened -> not yet finished
                    if (tempFinalVertices.size() > 0) tempEquivalencyClasses.add(tempFinalVertices);
                    if (tempNonFinalVertices.size() > 0) tempEquivalencyClasses.add(tempNonFinalVertices);
                }
                equivalencyClasses = copyEquivalenyClasses(tempEquivalencyClasses);
            }
            wordLength++;
        }

        return equivalencyClasses;
    }

    /**
     * copys the equivaleny classes just to be sure
     * @param toCopy
     * @return
     */
    private Set<Set<Vertex>> copyEquivalenyClasses(Set<Set<Vertex>> toCopy) {
        Set<Set<Vertex>> output = new HashSet<>();
        for (Set<Vertex> vertices : toCopy) {
            output.add(new HashSet<>(vertices));
        }
        return output;
    }

    /**
     * returns if given a vertex and a word and you traverse the dea
     * that in  the end the vertex is final or not
     * @param vertex start vertex
     * @param word word to traverse
     * @return true if end vertex if final vertex
     * @throws IllegalArgumentException if graph is not dea
     */
    private boolean isFinalAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        return vertexAfterExecution(vertex, word).isFinalState();
    }

    /**
     * gets the vertex after exectuion of a word at a vertex
     * @param vertex start
     * @param word traverse
     * @return end vertex
     * @throws IllegalArgumentException if graph is not dea
     */
    private Vertex vertexAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        if (word.equals("")) {
            return vertex;
        }
        if (graph.getNextVertices(vertex, word.charAt(0)).size() > 1 || graph.getNextVertices(vertex, word.charAt(0)).size() == 0) {
            throw new IllegalArgumentException();
        }
        Iterator<Vertex> iterator = graph.getNextVertices(vertex, word.charAt(0)).iterator();
        Vertex nextVertex = iterator.hasNext() ? iterator.next() : null;
        String subString = word.substring(1);
        return vertexAfterExecution(nextVertex, subString);
    }

    /**
     *
     * @return a list of all symbols in  a graph
     */
    private Set<Character> getTransitionSymbols() {
        Set<Character> transitionSymbols = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            transitionSymbols.add(edge.getSymbol());
        }
        return transitionSymbols;
    }

    /**
     *
     * @param characters input
     * @param length length
     * @return all permutations of length n oveer characters as alphabet
     */
    private Set<String> getAllWordsOfLengthN(Set<Character> characters, int length) {
        Set<String> words = new HashSet<>();
        getAllWordsOfLengthN(words, characters, length, "");
        return words;
    }

    /**
     * recursive function of above
     * @param words
     * @param characters
     * @param length
     * @param prefix
     */
    private void getAllWordsOfLengthN(Set<String> words, Set<Character> characters, int length, String prefix) {
        if (prefix.length() == length) {
            words.add(prefix);
        } else {
            for (Character character : characters) {
                getAllWordsOfLengthN(words, characters, length, prefix + character);
            }
        }
    }

    /**
     * removes all Vertices that arent accessible from start vertex
     */
    private void removeUnnecassaryVertices() {
        Set<Vertex> allAccessibleVertices = getAllAccessibleVertices();
        Set<Vertex> toBeRemoved = new HashSet<>();
        for (Vertex vertex : graph.getVertices()) {
            if (!allAccessibleVertices.contains(vertex)) {
                toBeRemoved.add(vertex);
            }
        }
        for (Vertex vertex : toBeRemoved) {
            JButton vertexButton = gui.buttonVertexBiMap.inverse().get(vertex);
            new RemoveVertexStrategy(gui).editGGraph(vertexButton);
        }
    }

    /**
     * @return the set of vertices that are reachable from start
     */
    private Set<Vertex> getAllAccessibleVertices() {
        Set<Vertex> allAccessibleVertices = new HashSet<>();
        if (graph.getStartVertex() == null) return allAccessibleVertices;
        allAccessibleVertices.add(graph.getStartVertex());
        return getAllAccessibleVertices(allAccessibleVertices);
    }

    /**
     * returns a list of all accesible vertices from start vertex
     * @param accessible
     * @return
     */
    private Set<Vertex> getAllAccessibleVertices(Set<Vertex> accessible) {
        int oldSize = accessible.size();
        Set<Vertex> newAccessible = new HashSet<>();
        for (Vertex vertex : accessible) {
            newAccessible.add(vertex);
            newAccessible.addAll(graph.getNextVertices(vertex));
        }
        if (oldSize == newAccessible.size()) {
            return accessible;
        } else {
            return getAllAccessibleVertices(newAccessible);
        }

    }
}
