package graphEdit.graphRepresentation;

import java.util.Objects;

/**
 * a dea consists out of edges and vertices
 * this is the edge part
 * simple but effective
 * start, end, and smybol
 * + all useful getters and setters
 */
public class Edge {
    private Vertex startVertex;
    private Vertex endVertex;
    private char symbol;

    public Edge(Vertex startVertex, Vertex endVertex, char symbol) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean containsVertex(Vertex vertex) {
        return vertex.equals(startVertex) || vertex.equals(endVertex);
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    @Override
    public String toString() {
        return "Edge{" +startVertex.getName() + "->" + endVertex.getName() + ", " + symbol + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return symbol == edge.symbol && Objects.equals(startVertex, edge.startVertex) && Objects.equals(endVertex, edge.endVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex, symbol);
    }
}
