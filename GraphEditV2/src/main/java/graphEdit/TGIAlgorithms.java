package graphEdit;

import graphEdit.editStrategies.RemoveVertexStrategy;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Graph;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;
import java.util.*;

public class TGIAlgorithms {
    private final MainGUI gui;
    private final Graph graph;

    public TGIAlgorithms(MainGUI gui) {
        this.gui = gui;
        this.graph = gui.graph;
    }

    public void minimize() {

        if (graph.getStartVertex() == null) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            return;
        }

        removeUnnecassaryVertices();

        Set<Set<Vertex>> equivalencyClasses = getEquivalencyClasses();
        if (equivalencyClasses == null) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            return;
        }
        for (Set<Vertex> equivalencyClass : equivalencyClasses) {
            System.out.print(equivalencyClass + " ");
        }

        for (Set<Vertex> equivalencyClass : equivalencyClasses) {
            if (equivalencyClass == null || equivalencyClass.size() == 0) continue;
            Iterator<Vertex> vertexIterator = equivalencyClass.iterator();
            Vertex firstElement = vertexIterator.next();
            while (vertexIterator.hasNext()){

            }

        }


    }

    private String getEquivalenyClassName(Set<Vertex> equivalenyClass){
        String newName = "{";
        for (Vertex vertex : equivalenyClass) {
            newName = newName.concat(vertex.getName()+", ");
        }
        return newName.substring(0,newName.length()-3)+"}";
    }

    private Set<Set<Vertex>> getEquivalencyClasses() {
        Set<Character> transitionSymbols = getTransitionSymbols();
        Set<Vertex> finalVertices = new HashSet<>(graph.getFinalVertices());
        Set<Vertex> nonFinalVertices = new HashSet<>(graph.getNonFinalVertices());
        Set<Set<Vertex>> equivalencyClasses = new HashSet<>();
        equivalencyClasses.add(finalVertices);
        equivalencyClasses.add(nonFinalVertices);

        int worldLength = 1;
        boolean finishedSplitting = false;
        while (!finishedSplitting) {
            finishedSplitting = true;
            Set<String> words = getAllWordsOfLengthN(transitionSymbols, worldLength);
            for (String word : words) {
                Set<Set<Vertex>> updatedEquivalencyClasses = new HashSet<>();
                for (Set<Vertex> equivalencyClass : equivalencyClasses) {
                    Set<Vertex> newFinalVertices = new HashSet<>();
                    Set<Vertex> newNonFinalVertices = new HashSet<>();
                    for (Vertex vertex : equivalencyClass) {
                        try {
                            if (isFinalAfterExecution(vertex, word)) {
                                newFinalVertices.add(vertex);
                            } else {
                                newNonFinalVertices.add(vertex);
                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println("The given Graph is not a DEA");
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
                            return null;
                        }
                    }
                    if (newFinalVertices.size() > 0 && newNonFinalVertices.size() > 0) finishedSplitting = false;
                    if (newFinalVertices.size() > 0) updatedEquivalencyClasses.add(newFinalVertices);
                    if (newNonFinalVertices.size() > 0) updatedEquivalencyClasses.add(newNonFinalVertices);
                }
                equivalencyClasses = updatedEquivalencyClasses;
            }
            worldLength++;
        }
        return new TreeSet<>(equivalencyClasses);
    }

    private boolean isFinalAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        return vertexAfterExecution(vertex, word).isFinalState();
    }

    private Vertex vertexAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        if (word.equals("")) {
            return vertex;
        }
        if (graph.getNextVertices(vertex, word.charAt(0)).size() > 1 || graph.getNextVertices(vertex, word.charAt(0)).size() == 0) {
            throw new IllegalArgumentException();
        }
        Iterator<Vertex> iterator = graph.getNextVertices(vertex, word.charAt(0)).iterator();
        Vertex nextVertex = iterator.hasNext() ? iterator.next() : null;
        String subString = word.length() > 1 ? word.substring(1, word.length() - 1) : "";
        return vertexAfterExecution(nextVertex, subString);
    }

    private Set<Character> getTransitionSymbols() {
        Set<Character> transitionSymbols = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            transitionSymbols.add(edge.getSymbol());
        }
        return transitionSymbols;
    }

    private Set<String> getAllWordsOfLengthN(Set<Character> characters, int length) {
        Set<String> words = new HashSet<>();
        getAllWordsOfLengthN(words, characters, length, "");
        return words;
    }

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
