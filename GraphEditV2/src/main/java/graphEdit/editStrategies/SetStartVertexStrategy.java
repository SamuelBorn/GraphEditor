package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.FinalStateBorder;
import graphEdit.graphStyles.RoundedButtonBorder;
import graphEdit.graphStyles.StartAndFinalStateBorder;
import graphEdit.graphStyles.StartStateBorder;

import javax.swing.*;

/**
 * this togggles the start vertex state of a vertex
 * it keeps the graph and the gui always the same
 */
public class SetStartVertexStrategy extends EditStrategy {
    public SetStartVertexStrategy(MainGUI gui) {
        super(gui);
    }

    /**
     * toggles a pressed button from start to non start and vice versa
     * @param pressed button to be toggled
     */
    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed = null; //penultimate is reset as only should be used by add edge
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
            setNewStart(vertex);
        }
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }

    public void setNewStart(Vertex vertex) {
        JButton pressed = gui.buttonVertexBiMap.inverse().get(vertex);
        if (gui.graph.getStartVertex()!=null){ //reset previous start vertex
            if (gui.graph.getStartVertex().isFinalState()){
                gui.buttonVertexBiMap.inverse().get(gui.graph.getStartVertex()).setBorder(new FinalStateBorder());
            }else{
                gui.buttonVertexBiMap.inverse().get(gui.graph.getStartVertex()).setBorder(new RoundedButtonBorder());
            }
        }

        gui.graph.setStartVertex(vertex);
        if (vertex.isFinalState()){
            pressed.setBorder(new StartAndFinalStateBorder());
        }else{
            pressed.setBorder(new StartStateBorder());
        }
    }

}
