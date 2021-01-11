package graphEdit.graphRepresentation;

import java.util.Objects;

/**
 * a dea consists out of edges and vertices
 * this is the edge part
 * simple but effective
 * name and checking if final state
 * the graph start state isnt here saved
 * as it is unique per graph
 * + all useful getters and setters
 */
public class Vertex {
    private String name;
    private boolean finalState;

    /**
     * vertex name is used as an id!!!
     * so the graph verwalter needs to check if this name given is truly unique
     * @param name id
     */
    public Vertex(String name) {
        this.name = name;
        finalState = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return finalState == vertex.finalState && Objects.equals(name, vertex.name);
    }

    @Override
    public String toString() {
        if (finalState) {
            return  name+"(final)";
        } else {
            return name;
        }
    }
}
