package graphEdit.editStrategies;

import graphEdit.MainGUI;

public abstract class EditStrategy {
    MainGUI gui;

    public EditStrategy(MainGUI gui){
        this.gui = gui;
    }

    public abstract void editGGraph();
}
