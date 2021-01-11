package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.Arrow;

import javax.swing.*;
import java.util.Collection;

public class RemoveVertexStrategy extends EditStrategy {
    public RemoveVertexStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed = null;
        Vertex vertex = gui.buttonVertexBiMap.get(pressed);
        Collection<Edge> edges = gui.graph.getEdgesContainingVertex(vertex);

        for (Edge edge : edges) {
            Arrow arrowToBeRemoved = gui.arrowEdgeBiMap.inverse().get(edge);
            if (arrowToBeRemoved!=null){
                gui.contentPane.remove(arrowToBeRemoved);
                gui.arrowEdgeBiMap.remove(arrowToBeRemoved);
                gui.arrowEdgeBiMap.inverse().remove(edge);
            }
        }

        gui.graph.removeVertex(vertex);
        gui.graph.removeEdges(edges);

        gui.buttonVertexBiMap.remove(pressed);
        gui.contentPane.remove(pressed);

        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
