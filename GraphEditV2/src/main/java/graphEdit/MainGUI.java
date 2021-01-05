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

        buttonPlacer.placeButton(100, 100);
        buttonPlacer.placeButton(200, 100);
        buttonPlacer.placeButton(100, 200);
        buttonPlacer.placeButton(200, 200);
        Iterator<JButton> iterator = buttonVertexBiMap.inverse().values().iterator();
        JButton b0 = iterator.next();
        JButton b1 = iterator.next();
        JButton b2 = iterator.next();
        JButton b3 = iterator.next();

        new SetStartVertexStrategy(this).editGGraph(b0);
        new SetFinalStrategy(this).editGGraph(b3);
        penultimatePressed = b0;
        new AddEdgeStrategy(this).editGGraph(b1);
        penultimatePressed = b0;
        new AddEdgeStrategy(this).editGGraph(b2);
        penultimatePressed = b2;
        new AddEdgeStrategy(this).editGGraph(b3);
        penultimatePressed = b1;
        new AddEdgeStrategy(this).editGGraph(b3);

        frame.setVisible(true);
    }


    public void initializeMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu editGraph = new JMenu("Edt Graph");
        JRadioButtonMenuItem addEdge = new JRadioButtonMenuItem("Add edge");
        JRadioButtonMenuItem removeVertex = new JRadioButtonMenuItem("Remove vertices");
        JRadioButtonMenuItem setStart = new JRadioButtonMenuItem("Set starting vertex");
        JRadioButtonMenuItem setFinal = new JRadioButtonMenuItem("Set final vertices");

        JMenu tgiAlgorithms = new JMenu("TGI Algorithms");
        JMenuItem minimize = new JMenuItem("Minimize Graph");

        addEdge.setSelected(true);
        editGraph.add(addEdge);
        editGraph.add(removeVertex);
        editGraph.add(setFinal);
        editGraph.add(setStart);

        tgiAlgorithms.add(minimize);
        jMenuBar.add(editGraph);
        jMenuBar.add(tgiAlgorithms);

        frame.setJMenuBar(jMenuBar);

        ButtonGroup editOptionGroup = new ButtonGroup();
        editOptionGroup.add(addEdge);
        editOptionGroup.add(removeVertex);
        editOptionGroup.add(setFinal);
        editOptionGroup.add(setStart);

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