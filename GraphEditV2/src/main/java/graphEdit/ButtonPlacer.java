package graphEdit;

import graphEdit.vertexStyles.RoundedButtonBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonPlacer extends MouseAdapter {

    private final MainGUI gui;

    public ButtonPlacer(MainGUI gui) {
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        JButton button = new JButton("");
        Dimension dim = button.getPreferredSize();
        button.setBounds(x, y, MainGUI.radius, MainGUI.radius);
        button.setBorder(new RoundedButtonBorder());
        button.setContentAreaFilled(false);
        gui.contentPane.add(button);
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
