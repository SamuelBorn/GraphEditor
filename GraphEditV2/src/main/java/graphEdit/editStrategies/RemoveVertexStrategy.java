package graphEdit.editStrategies;

import graphEdit.MainGUI;

import javax.swing.*;

public class RemoveVertexStrategy extends EditStrategy {
    public RemoveVertexStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed =null;

    }
}
