package graphEdit.editStrategies;

import graphEdit.MainGUI;
import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.FinalStateBorder;
import graphEdit.graphStyles.RoundedButtonBorder;
import graphEdit.graphStyles.StartAndFinalStateBorder;
import graphEdit.graphStyles.StartStateBorder;

import javax.swing.*;
/**
 * this class sets vertices as final
 * it keeps the graph and the gui always the same
 */
public class SetFinalStrategy extends EditStrategy {
    public SetFinalStrategy(MainGUI gui) {
        super(gui);
    }

    /**
     * toggles the pressed button from final to non final and vice versa
     * @param pressed button to be toggled
     */
    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed = null;//penultimate is reset as only should be used by add edge
        Vertex vertex = gui.buttonVertexBiMap.get(pressed);
        if (vertex.isFinalState()) {
            //vertex is final state -> now set to non final
            vertex.setFinalState(false);
            if (gui.graph.getStartVertex()!= null && gui.graph.getStartVertex().equals(vertex)){
                pressed.setBorder(new StartStateBorder());
            } else {
                pressed.setBorder(new RoundedButtonBorder());
            }
        } else {
            //vertex is non final state -> now set to final
            vertex.setFinalState(true);
            if (gui.graph.getStartVertex()!= null && gui.graph.getStartVertex().equals(vertex)){
                pressed.setBorder(new StartAndFinalStateBorder());
            } else {
                pressed.setBorder(new FinalStateBorder());
            }
        }
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
