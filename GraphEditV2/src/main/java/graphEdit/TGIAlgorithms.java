package graphEdit;

import graphEdit.editStrategies.RemoveVertexStrategy;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Graph;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TGIAlgorithms {
    private final MainGUI gui;
    private final Graph graph;

    public TGIAlgorithms(MainGUI gui) {
        this.gui = gui;
        this.graph = gui.graph;
    }

    public void minimize() {
        int worldLength = 1;
        boolean setSplit = true;
        Collection<Character> transitionSymbols = getTransitionSymbols();
        Collection<Vertex> finalVertices = graph.getFinalVertices();
        Collection<Vertex> nonFinalVertices = graph.getNonFinalVertices();
        Collection<Collection<Vertex>> sets = new HashSet<>();
        sets.add(finalVertices);
        sets.add(nonFinalVertices);

        if (graph.getStartVertex() == null) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            return;
        }

        removeUnnecassaryVertices();

        while (setSplit) {
            setSplit = false;
            Collection<String> words = getAllWordsOfLengthN(transitionSymbols, worldLength);
            for (String word : words) {
                Collection<Collection<Vertex>> splitSets = new HashSet<>();
                for (Collection<Vertex> set : sets) {
                    Collection<Vertex> finalSet = new HashSet<>();
                    Collection<Vertex> nonFinalSet = new HashSet<>();
                    for (Vertex vertex : set) {
                        if (isFinalAfterExecution(vertex, word)) {
                            finalSet.add(vertex);
                        } else {
                            nonFinalSet.add(vertex);
                        }
                    }
                    if (finalSet.size() > 0) splitSets.add(finalSet);
                    if (nonFinalSet.size() > 0) splitSets.add(nonFinalSet);
                }
                if(!splitSets.equals(sets)) setSplit = true;
            }
            worldLength++;
        }
    }

    private boolean isFinalAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        return vertexAfterExecution(vertex, word).isFinalState();
    }

    private Vertex vertexAfterExecution(Vertex vertex, String word) throws IllegalArgumentException {
        if (word.equals("")) {
            return vertex;
        }
        if (graph.getNextVertices(vertex, word.charAt(0)).size() != 1) {
            System.err.println("The given Graph is not a DEA");
            JOptionPane.showMessageDialog(gui.frame, "The given Graph is not a DEA");
            throw new IllegalArgumentException();
        }
        Vertex nextVertex = graph.getNextVertices(vertex, word.charAt(0)).iterator().next();
        String subString = word.substring(1, word.length() - 1);
        return vertexAfterExecution(nextVertex, subString);
    }

    private Collection<Character> getTransitionSymbols() {
        Collection<Character> transitionSymbols = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            transitionSymbols.add(edge.getSymbol());
        }
        return transitionSymbols;
    }

    private Collection<String> getAllWordsOfLengthN(Collection<Character> characters, int length) {
        Collection<String> words = new HashSet<>();
        getAllWordsOfLengthN(words, characters, length, "");
        return words;
    }

    private void getAllWordsOfLengthN(Collection<String> words, Collection<Character> characters, int length, String prefix) {
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
        for (Vertex vertex : graph.getVertices()) {
            if (!allAccessibleVertices.contains(vertex)) {
                JButton vertexButton = gui.buttonVertexBiMap.inverse().get(vertex);
                new RemoveVertexStrategy(gui).editGGraph(vertexButton);
            }
        }
    }

    private Set<Vertex> getAllAccessibleVertices() {
        Set<Vertex> allAccessibleVertices = new HashSet<>();
        if (graph.getStartVertex() == null) return allAccessibleVertices;
        allAccessibleVertices.add(graph.getStartVertex());
        return getAllAccessibleVertices(allAccessibleVertices);
    }

    private Set<Vertex> getAllAccessibleVertices(Set<Vertex> accessible) {
        int accessibleSize = accessible.size();
        for (Vertex vertex : accessible) {
            accessible.addAll(graph.getNextVertices(vertex));
        }
        if (accessible.size() == accessibleSize) {
            return accessible;
        } else {
            return getAllAccessibleVertices(accessible);
        }

    }
}
