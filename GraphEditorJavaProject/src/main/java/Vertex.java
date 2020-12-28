import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private Set<Vertex> outwardEdges;
    private VertexState vertexState;
    private String name;

    public Vertex() {
        outwardEdges = new HashSet<>();
    }

    public Vertex(String name) {
        outwardEdges = new HashSet<>();
        this.name = name;
    }


    public void addNext(Vertex vertex) {
        outwardEdges.add(vertex);
    }

    public Set<Vertex> getOutwardEdges() {
        return outwardEdges;
    }

    public void removeAllOutwardEdges() {
        outwardEdges = new HashSet<>();
    }

    public void removeOutwardEdge(Vertex vertex) {
        outwardEdges.remove(vertex);
    }

    public VertexState getVertexState() {
        return this.vertexState;
    }

    public void setVertexState(VertexState vertexState) {
        this.vertexState = vertexState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
