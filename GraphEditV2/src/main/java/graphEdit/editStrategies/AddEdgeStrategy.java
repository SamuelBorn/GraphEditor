package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphStyles.Arrow;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;

public class AddEdgeStrategy extends EditStrategy {
    private static final char ERROR_SYMBOL = ' ';
    private static final char STOP_SYMBOL = '°';

    public AddEdgeStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        if (gui.penultimatePressed == null) {
            gui.penultimatePressed = pressed;
        } else {
            Vertex startVertex = gui.buttonVertexBiMap.get(gui.penultimatePressed);
            Vertex endVertex = gui.buttonVertexBiMap.get(pressed);
            addEdge(startVertex, endVertex);
        }
    }

    public void addEdge(Vertex startVertex, Vertex endVertex){
        char symbol;
        do {
            symbol = getSymbol();
            //symbol = 'a'; //TODO REMOVE LINE
            if (symbol == STOP_SYMBOL){
                gui.penultimatePressed = null;
                return;
            }
        } while (symbol == ERROR_SYMBOL);
        Edge edge = new Edge(startVertex, endVertex, symbol);
        placeEdge(edge);
    }

    public void placeEdge(Edge edge){
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
