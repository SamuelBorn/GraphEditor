import java.util.HashSet;
import java.util.Set;

public class Vertex {
    private Set<Vertex> outwardEdges;


    public Vertex(){
        outwardEdges = new HashSet<>();
    }

    public void addNext(Vertex vertex){
        outwardEdges.add(vertex);
    }

    public Set<Vertex> getOutwardEdges(){
        return outwardEdges;
    }

    public void removeAllOutwardEdges(){
        outwardEdges = new HashSet<>();
    }

    public void removeOutwardEdge(Vertex vertex){
        outwardEdges.remove(vertex);
    }
}
