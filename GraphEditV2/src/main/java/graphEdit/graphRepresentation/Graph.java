package graphEdit.graphRepresentation;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private final Set<Vertex> vertices;
    private Set<Edge> edges;
    private Vertex startVertex;

    public Graph() {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        startVertex = null;
    }

    public int size(){
        return vertices.size();
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void removeVertex(Vertex vertex) {
        if (vertex.equals(startVertex)) resetStartVertex();
        vertices.remove(vertex);
        Set<Edge> newEdges = new HashSet<>();
        for (Edge edge : edges) { //cant remove object from list on iteration -> this is the workaround
            if (!edge.containsVertex(vertex)) {
                newEdges.add(edge);
            }
        }
        edges = newEdges;
    }

    public void addVertex(Vertex vertex) {
        for (Vertex vertexIterator : vertices) { //the name ist used as an ID so it must be unique
            if (vertex.getName().equals(vertexIterator.getName())) return;
        }
        vertices.add(vertex);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public void addEdge(Edge edge) {
        addEdge(edge.getStartVertex(), edge.getEndVertex());
    }

    public void addEdge(Vertex startVertex, Vertex endVertex) {
        if (!vertices.contains(startVertex) || !vertices.contains(endVertex)) {
            return;
        }
        edges.add(new Edge(startVertex, endVertex));
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public void resetStartVertex(){
        startVertex = null;
    }

}