import java.util.HashSet;
import java.util.Set;

public class Graph {
    private final Set<Vertex> vertices;

    public Graph() {
        vertices = new HashSet<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void connect(Vertex vertexStart, Vertex vertexEnd) {
        if (!vertices.contains(vertexStart)||!vertices.contains(vertexEnd)){
            throw new IllegalArgumentException("start or end vertex is not in the graph");
        }
        vertexStart.addNext(vertexEnd);
    }

    public int getSize() {
        return vertices.size();
    }

    public Set<Vertex> getInwardEdges(Vertex vertex) {
        if (!vertices.contains(vertex)) throw new IllegalArgumentException("vertex not in graph");
        Set<Vertex> ingoingVertices = new HashSet<>();
        for (Vertex vertexIterator : vertices) {
            if (vertexIterator == vertex) continue;
            if (vertexIterator.getOutwardEdges().contains(vertex)){
                ingoingVertices.add(vertexIterator);
            }
        }
        return ingoingVertices;
    }

    public Set<Vertex> getOutwardEdges(Vertex vertex) {
        if (!vertices.contains(vertex)) throw new IllegalArgumentException("vertex not in graph");
        return vertex.getOutwardEdges();
    }

    public void minimize() {
        //TODO
    }

    public void NEAtoDEA() {
        //TODO
    }
}
