package graphEdit;

import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Graph;
import graphEdit.graphRepresentation.Vertex;

import java.util.Collection;
import java.util.HashSet;

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

        while (setSplit) {
            setSplit = false;
            Collection<String> words = getAllWordsOfLengthN(transitionSymbols,worldLength);

            for (String word : words) {

            }

            worldLength++;
        }
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
                getAllWordsOfLengthN(words, characters, length, prefix+character);
            }
        }
    }
}
