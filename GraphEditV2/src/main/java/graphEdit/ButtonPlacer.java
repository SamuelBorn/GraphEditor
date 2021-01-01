package graphEdit;

import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.RoundedButtonBorder;

import javax.swing.*;
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
        String name = "q" + gui.graph.size();

        JButton button = new JButton(name);
        button.setBounds(x - MainGUI.buttonRadius / 2, y - MainGUI.buttonRadius / 2, MainGUI.buttonRadius, MainGUI.buttonRadius);
        button.setBorder(new RoundedButtonBorder());
        button.setContentAreaFilled(false);
        button.addActionListener(gui.buttonClicker);

        Vertex vertex = new Vertex(name);
        gui.graph.addVertex(vertex);
        gui.buttonVertexBiMap.put(button, vertex);

        gui.contentPane.add(button);
        gui.contentPane.revalidate();
        gui.contentPane.repaint();
    }
}
