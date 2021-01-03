package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.FinalStateBorder;
import graphEdit.graphStyles.RoundedButtonBorder;
import graphEdit.graphStyles.StartAndFinalStateBorder;
import graphEdit.graphStyles.StartStateBorder;

import javax.swing.*;

public class SetStartVertexStrategy extends EditStrategy {
    public SetStartVertexStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed = null;
        Vertex vertex = gui.buttonVertexBiMap.get(pressed);
        if (gui.graph.getStartVertex() != null && gui.graph.getStartVertex().equals(vertex)) {
            //vertex is start state -> now set to non start and update graph
            gui.graph.resetStartVertex();
            if (vertex.isFinalState()){
                pressed.setBorder(new FinalStateBorder());
            }else{
                pressed.setBorder(new RoundedButtonBorder());
            }
        } else {
            //vertex is non start state -> now set to start
            gui.graph.setStartVertex(vertex);
            if (vertex.isFinalState()){
                pressed.setBorder(new StartAndFinalStateBorder());
            }else{
                pressed.setBorder(new StartStateBorder());
            }
        }
    }
}
