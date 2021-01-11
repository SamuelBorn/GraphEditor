package graphEdit;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import graphEdit.editStrategies.*;
import graphEdit.graphStyles.Arrow;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Graph;
import graphEdit.graphRepresentation.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class MainGUI implements Runnable {
    public final static int buttonRadius = 47;
    public JFrame frame;
    public JPanel contentPane;
    public Graph graph = new Graph();
    public ButtonPlacer buttonPlacer;
    public ButtonClicker buttonClicker;
    public JButton penultimatePressed = null;
    public EditStrategy editOption = new AddEdgeStrategy(this);
    public BiMap<JButton, Vertex> buttonVertexBiMap = HashBiMap.create();   //maps buttons to vertices and vice versa
    public BiMap<Arrow, Edge> arrowEdgeBiMap = HashBiMap.create();       //maps arrows to edges and vice versa

    public static void main(String[] args) {
        EventQueue.invokeLater(new MainGUI());
    }

    public void run() {
        showGui();
    }

    private void showGui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        initializeMenuBar();
        buttonClicker = new ButtonClicker(this);
        buttonPlacer = new ButtonPlacer(this);
        contentPane.addMouseListener(buttonPlacer);
        frame.setSize(650, 450);
        frame.setLocationByPlatform(true);



        frame.setVisible(true);
    }


    public void initializeMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu editGraph = new JMenu("Edit Graph");
        JRadioButtonMenuItem addEdge = new JRadioButtonMenuItem("Add edge");
        JRadioButtonMenuItem removeVertex = new JRadioButtonMenuItem("Remove vertices");
        JRadioButtonMenuItem setStart = new JRadioButtonMenuItem("Set starting vertex");
        JRadioButtonMenuItem setFinal = new JRadioButtonMenuItem("Set final vertices");

        JMenu tgiAlgorithms = new JMenu("TGI Algorithms");
        JMenuItem minimize = new JMenuItem("Minimize Graph");
        JMenuItem loadTestGraph = new JMenuItem("Load test graph");

        addEdge.setSelected(true);
        editGraph.add(addEdge);
        editGraph.add(removeVertex);
        editGraph.add(setFinal);
        editGraph.add(setStart);

        tgiAlgorithms.add(minimize);
        tgiAlgorithms.add(loadTestGraph);
        jMenuBar.add(editGraph);
        jMenuBar.add(tgiAlgorithms);

        frame.setJMenuBar(jMenuBar);

        ButtonGroup editOptionGroup = new ButtonGroup();
        editOptionGroup.add(addEdge);
        editOptionGroup.add(removeVertex);
        editOptionGroup.add(setFinal);
        editOptionGroup.add(setStart);

        loadTestGraph.addActionListener(e -> {
            buttonPlacer.placeButton(100, 100);
            buttonPlacer.placeButton(200, 100);
            buttonPlacer.placeButton(200, 200);
            buttonPlacer.placeButton(300, 100);
            buttonPlacer.placeButton(400, 100);
            buttonPlacer.placeButton(500, 200);
            buttonPlacer.placeButton(300, 200);
            buttonPlacer.placeButton(500, 100);

            Vertex[] testVertices = new Vertex[10];
            int i = 0;
            for (Vertex value : buttonVertexBiMap.values()) {
                testVertices[i] = value;
                i++;
            }

            new SetStartVertexStrategy(this).editGGraph(buttonVertexBiMap.inverse().get(testVertices[0]));
            new SetFinalStrategy(this).editGGraph(buttonVertexBiMap.inverse().get(testVertices[6]));
            new SetFinalStrategy(this).editGGraph(buttonVertexBiMap.inverse().get(testVertices[7]));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[0],testVertices[1],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[0],testVertices[2],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[1],testVertices[2],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[1],testVertices[3],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[2],testVertices[2],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[2],testVertices[2],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[3],testVertices[6],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[3],testVertices[4],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[4],testVertices[4],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[4],testVertices[7],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[5],testVertices[5],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[5],testVertices[4],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[6],testVertices[4],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[6],testVertices[5],'/'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[7],testVertices[4],'*'));
            new AddEdgeStrategy(this).placeEdge(new Edge(testVertices[7],testVertices[5],'/'));

            contentPane.revalidate();
            contentPane.repaint();
        });

        minimize.addActionListener(e -> {
            new TGIAlgorithms(this).minimize();
        });

        //depending on which radio button is active select a different EditOption
        setFinal.addActionListener(e -> {
            editOption = new SetFinalStrategy(this);
        });
        setStart.addActionListener(e -> {
            editOption = new SetStartVertexStrategy(this);
        });
        addEdge.addActionListener(e -> {
            editOption = new AddEdgeStrategy(this);
        });
        removeVertex.addActionListener(e -> {
            editOption = new RemoveVertexStrategy(this);
        });
    }
}