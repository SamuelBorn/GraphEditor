package graphEdit;

import graphEdit.graphRepresentation.Vertex;
import graphEdit.graphStyles.RoundedButtonBorder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * this is a mouse listener that checks if the user clicked the ground and then places a button there
 */
public class ButtonPlacer extends MouseAdapter {

    private final MainGUI gui;

    public ButtonPlacer(MainGUI gui) {
        this.gui = gui;
    }

    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        placeButton(x,y);
    }

    public void placeButton(int x, int y){
        String name = gui.graph.getNextName();

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
