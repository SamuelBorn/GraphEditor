package graphEdit.editStrategies;

import graphEdit.MainGUI;

import javax.swing.*;

public abstract class EditStrategy {
    MainGUI gui;

    public EditStrategy(MainGUI gui) {
        this.gui = gui;
    }

    public abstract void editGGraph(JButton pressed);
}
