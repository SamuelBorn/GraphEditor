package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphStyles.Arrow;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;

/**
 * this class adds edges to a graph
 * it keeps the graph and the gui always the same
 */
public class AddEdgeStrategy extends EditStrategy {
    private static final char ERROR_SYMBOL = ' ';
    private static final char STOP_SYMBOL = '°';

    public AddEdgeStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        if (gui.penultimatePressed == null) { //updating penultimate
            gui.penultimatePressed = pressed;
        } else {
            Vertex startVertex = gui.buttonVertexBiMap.get(gui.penultimatePressed);
            Vertex endVertex = gui.buttonVertexBiMap.get(pressed);
            addEdge(startVertex, endVertex);
        }
    }

    /**
     * this is an extension it simbly fetches a symbol from the user and afterwards uses @placeEdge to place the edge
     *
     * @param startVertex startVertex of edge
     * @param endVertex endVertex of edge
     */
    public void addEdge(Vertex startVertex, Vertex endVertex) {
        char symbol;
        do {
            symbol = getSymbol();
            if (symbol == STOP_SYMBOL) {
                gui.penultimatePressed = null;
                return;
            }
        } while (symbol == ERROR_SYMBOL);
        Edge edge = new Edge(startVertex, endVertex, symbol);
        placeEdge(edge);
    }

    /**
     * this simply places an edge on the screen and puts it into the graph
     *
     * @param edge to be put on screen and in graph
     */
    public void placeEdge(Edge edge) {
        if (gui.graph.getEdges().contains(edge)) {
            return;
        }
        gui.graph.addEdge(edge);
        Arrow arrow = new Arrow(edge, gui);
        arrow.setSize(gui.contentPane.getSize());
        gui.contentPane.add(arrow);
        gui.arrowEdgeBiMap.put(arrow, edge);
        gui.penultimatePressed = null;
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }

    /**
     * opens a popup where user can type an input
     *
     * @return the input of the user
     */
    private char getSymbol() {
        String s = (String) JOptionPane.showInputDialog(
                gui.frame,
                "Enter transition Symbol",
                "",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

        if (s == null) {
            return STOP_SYMBOL;
        }
        //If a string was returned, say so.
        if (s.length() == 1) {
            return s.charAt(0);
        }
        return ERROR_SYMBOL;


    }
}
