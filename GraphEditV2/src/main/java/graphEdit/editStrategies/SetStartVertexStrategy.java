package graphEdit.editStrategies;

import graphEdit.MainGUI;

import javax.swing.*;

public class SetStartVertexStrategy extends EditStrategy {
    public SetStartVertexStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed =null;
    }
}
