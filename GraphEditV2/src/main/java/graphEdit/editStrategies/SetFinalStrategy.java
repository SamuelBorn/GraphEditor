package graphEdit.editStrategies;

import graphEdit.MainGUI;

import javax.swing.*;

public class SetFinalStrategy extends EditStrategy {
    public SetFinalStrategy(MainGUI gui) {
        super(gui);
    }

    @Override
    public void editGGraph(JButton pressed) {
        gui.penultimatePressed =null;

    }
}
