package graphEdit.graphRepresentation;

import java.util.Objects;

public class Vertex {
    private String name;
    private boolean finalState;

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
        return "Vertex{" +
                "name='" + name + '\'' +
                ", finalState=" + finalState +
                '}';
    }
}
