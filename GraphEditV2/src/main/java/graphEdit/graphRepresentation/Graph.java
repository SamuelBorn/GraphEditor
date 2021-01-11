package graphEdit.graphRepresentation;

import java.util.ArrayList;
import java.util.Collection;
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

    public int size() {
        return vertices.size();
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Vertex> getNextVertices(Vertex vertex) {
        Set<Vertex> next = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.getStartVertex().equals(vertex)) {
                next.add(edge.getEndVertex());
            }
        }
        return next;
    }

    public Set<Vertex> getNextVertices(Vertex vertex, char symbol) {
        Set<Vertex> next = new HashSet<>();
        for (Edge edge : edges) {
            if (edge.getStartVertex().equals(vertex) && edge.getSymbol() == symbol) {
                next.add(edge.getEndVertex());
            }
        }
        return next;
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

    public void removeVertices(Collection<Vertex> remove){
        vertices.removeAll(remove);
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

    public void addEdges(Collection<Edge> edges){
        this.edges.addAll(edges);
    }

    public void removeEdges(Collection<Edge> edges) {
        for (Edge edge : edges) {
            this.edges.remove(edge);
        }
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Collection<Edge> getEdgesContainingVertex(Vertex vertex) {
        Collection<Edge> containList = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.containsVertex(vertex)) {
                containList.add(edge);
            }
        }
        return containList;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public void resetStartVertex() {
        startVertex = null;
    }

    public Collection<Vertex> getFinalVertices() {
        Collection<Vertex> finalVertices = new HashSet<>();
        for (Vertex vertex : vertices) {
            if (vertex.isFinalState()) finalVertices.add(vertex);
        }
        return finalVertices;
    }

    public Collection<Vertex> getNonFinalVertices() {
        Collection<Vertex> finalVertices = new HashSet<>();
        for (Vertex vertex : vertices) {
            if (!vertex.isFinalState()) finalVertices.add(vertex);
        }
        return finalVertices;
    }

    public String getNextName() {
        if (vertices.size() == 0) return "q0";
        int index = 0;
        boolean unusedIndexFound;
        do {
            unusedIndexFound = true;
            String newName = "" + index;
            for (Vertex vertex : vertices) {
                if (vertex.getName().contains(newName)){
                    unusedIndexFound = false;
                    break;
                }
            }
            if (!unusedIndexFound) index++; //do not update a last time
        } while (!unusedIndexFound);
        return "q" + index;
    }

    @Override
    public String toString() {
        return "Start: " + getStartVertex() + "\n" +
                "Vertices: " + getVertices() + "\n" +
                "Edges: " + getEdges() + "\n";

    }
}