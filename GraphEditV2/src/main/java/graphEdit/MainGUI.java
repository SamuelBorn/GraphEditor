package graphEdit;

import graphEdit.editStrategies.*;
import graphEdit.graphRepresentation.Edge;
import graphEdit.graphRepresentation.Vertex;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.awt.*;
import javax.swing.*;

public class MainGUI implements Runnable {
    public JFrame frame;
    public JPanel contentPane;
    public ButtonPlacer buttonPlacer;
    public ButtonClicker buttonClicker;
    public EditStrategy editOption = new AddEdgeStrategy(this);
    public BiMap<JButton, Vertex> buttonVertexBiMap = HashBiMap.create();
    public BiMap<JButton, Edge> buttonEdgeBiMap = HashBiMap.create();

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
        frame.setSize(400, 450);
        frame.setLocationByPlatform(true);
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
        JMenuItem neaToDea = new JMenuItem("NEA into DEA");

        addEdge.setSelected(true);
        editGraph.add(addEdge);
        editGraph.add(removeVertex);
        editGraph.add(setFinal);
        editGraph.add(setStart);

        tgiAlgorithms.add(minimize);
        tgiAlgorithms.add(neaToDea);
        jMenuBar.add(editGraph);
        jMenuBar.add(tgiAlgorithms);

        frame.setJMenuBar(jMenuBar);

        ButtonGroup editOptionGroup = new ButtonGroup();
        editOptionGroup.add(addEdge);
        editOptionGroup.add(removeVertex);
        editOptionGroup.add(setFinal);
        editOptionGroup.add(setStart);

        minimize.addActionListener(e -> {
            System.out.println("Hallo");
        });
        neaToDea.addActionListener(e -> {
            System.out.println("Hallo");
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