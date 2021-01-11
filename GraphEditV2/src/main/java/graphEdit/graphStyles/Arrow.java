package graphEdit.graphStyles;

import graphEdit.MainGUI;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This Class ist used to indicate a Edge on Screen between two Vertices
 */
public class Arrow extends JComponent {
    private final Edge edge;
    private final MainGUI gui;

    public Arrow(Edge edge, MainGUI gui) {
        this.edge = edge;
        this.gui = gui;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //paint line
        int offsetButton = MainGUI.buttonRadius / 2;
        JButton startButton = gui.buttonVertexBiMap.inverse().get(edge.getStartVertex());
        JButton endButton = gui.buttonVertexBiMap.inverse().get(edge.getEndVertex());
        if (startButton == null || endButton == null) return;
        int x1 = startButton.getX() + offsetButton; //following are all calculations on how to draw the line
        int y1 = startButton.getY() + offsetButton;
        int x2 = endButton.getX() + offsetButton;
        int y2 = endButton.getY() + offsetButton;
        Point2D.Double start = new Point2D.Double(x1, y1);
        Point2D.Double end = new Point2D.Double(x2, y2);
        double distance = start.distance(end);
        Point2D.Double direction = new Point2D.Double(((end.x - start.x) / distance), ((end.y - start.y) / distance));
        Point2D.Double offset = new Point2D.Double(direction.x * MainGUI.buttonRadius / 2, direction.y * MainGUI.buttonRadius / 2);
        Point2D.Double offsetStart = new Point2D.Double(start.x + offset.x, start.y + offset.y);
        Point2D.Double offsetEnd = new Point2D.Double(end.x - offset.x, end.y - offset.y);
        g.drawLine((int) offsetStart.x, (int) offsetStart.y, (int) offsetEnd.x, (int) offsetEnd.y);
        int pointSize = 10;
        if (start.equals(end)){
            int newX = startButton.getX() + 2;
            int newY = startButton.getY() + 2;
            g.fillOval(newX, newY, pointSize, pointSize);
            g.drawString(edge.getSymbol() + "", startButton.getX(), startButton.getY());
        }else {
            g.fillOval((int) offsetEnd.x - pointSize / 2, (int) offsetEnd.y - pointSize / 2, pointSize, pointSize);
            //paint symbol
            int offsetSymbol = -10;
            g.drawString(edge.getSymbol() + "", (x1 + x2) / 2 + offsetSymbol, (y1 + y2) / 2 + offsetSymbol);
        }



    }
}
