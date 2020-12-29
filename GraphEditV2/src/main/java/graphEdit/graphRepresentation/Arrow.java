package graphEdit.graphRepresentation;

import graphEdit.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This Class ist used to indicate a Edge on Screen between two Vertices
 */
public class Arrow extends JComponent {
    private Edge edge;
    private MainGUI gui;

    public Arrow(Edge edge, MainGUI gui) {
        this.edge = edge;
    }

    public Arrow(Vertex startVertex, Vertex endVertex, MainGUI gui) {
        new Arrow(new Edge(startVertex, endVertex), gui);
    }

    public Arrow() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        int offsetButton = (int) (gui.buttonVertexBiMap.inverse().get(edge.getStartVertex()).getSize().getHeight() / 2);
        int x1 = gui.buttonVertexBiMap.inverse().get(edge.getStartVertex()).getX()+offsetButton;
        int y1 = gui.buttonVertexBiMap.inverse().get(edge.getStartVertex()).getY()+offsetButton;
        int x2 = gui.buttonVertexBiMap.inverse().get(edge.getEndVertex()).getX()+offsetButton;
        int y2 = gui.buttonVertexBiMap.inverse().get(edge.getEndVertex()).getY()+offsetButton;
        Point2D.Double start = new Point2D.Double(x1, y1);
        Point2D.Double end = new Point2D.Double(x2, y2);
        double distance = start.distance(end);
        Point2D.Double direction = new Point2D.Double( ((end.x - start.x) / distance), ((end.y - start.y) / distance));
        Point2D.Double offset = new Point2D.Double(direction.x * MainGUI.radius/2, direction.y * MainGUI.radius/2);
        Point2D.Double offsetStart = new Point2D.Double(start.x + offset.x, start.y + offset.y);
        Point2D.Double offsetEnd = new Point2D.Double(end.x - offset.x, end.y - offset.y);

        System.out.println(direction);

        g.drawLine((int) offsetStart.x, (int) offsetStart.y, (int) offsetEnd.x, (int) offsetEnd.y);
        g.fillOval((int) offsetEnd.x, (int) offsetEnd.y, 10, 10);
    }
}
