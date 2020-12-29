package graphEdit.graphRepresentation;

public class Edge {
    private Vertex startVertex;
    private Vertex endVertex;

    public Edge(Vertex startVertex, Vertex endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
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
        return "Edge{" +
                "startVertex=" + startVertex +
                ", endVertex=" + endVertex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return startVertex.equals(edge.startVertex) && endVertex.equals(edge.endVertex);
    }
}
