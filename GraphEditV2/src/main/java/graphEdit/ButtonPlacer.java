package graphEdit;

import graphEdit.vertexStyles.RoundedButtonBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonPlacer extends MouseAdapter {

    private final int buttonSize;
    private final MainGUI gui;

    public ButtonPlacer(MainGUI gui) {
        buttonSize = 60;
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        JButton button = new JButton("");
        Dimension dim = button.getPreferredSize();
        button.setBounds(x, y, buttonSize, buttonSize);
        button.setBorder(new RoundedButtonBorder(buttonSize));
        button.setContentAreaFilled(false);
        gui.contentPane.add(button);
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
