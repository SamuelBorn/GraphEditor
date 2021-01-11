package graphEdit.editStrategies;

import graphEdit.MainGUI;

import javax.swing.*;

/**
 * subclasses of this class are yoused to update the graph and gui simultaniously
 */
public abstract class EditStrategy {
    MainGUI gui;

    public EditStrategy(MainGUI gui) {
        this.gui = gui;
    }

    public abstract void editGGraph(JButton pressed);
}
