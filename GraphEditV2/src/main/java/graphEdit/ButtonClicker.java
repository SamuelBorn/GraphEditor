package graphEdit;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClicker implements ActionListener {
    private final MainGUI gui;

    public ButtonClicker(MainGUI gui) {
        this.gui = gui;
    }

    /**
     * a simple action listener that depending on which option is selected presses edits the graph
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressedButton = (JButton) e.getSource();
        gui.editOption.editGGraph(pressedButton);
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
