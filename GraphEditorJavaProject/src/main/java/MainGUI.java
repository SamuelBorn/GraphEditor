import ButtonStyles.RoundedButtonBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.*;

public class MainGUI extends MouseAdapter implements Runnable{
    private JFrame frame;
    private JPanel contentPane;
    private final Graph graph = new Graph();
    private final Hashtable<JButton, Vertex> buttons = new Hashtable<>();

    JRadioButtonMenuItem connectVertices;
    JRadioButtonMenuItem removeVertex ;
    JRadioButtonMenuItem setStart;
    JRadioButtonMenuItem setFinal;

    public void mouseClicked(MouseEvent mouseEvent) {
        //create the button
        int xCursorPosition = mouseEvent.getX();
        int yCursorPosition = mouseEvent.getY();
        int buttonSize = 45;
        JButton button = new JButton("q"+graph.getSize());
        button.setBounds(xCursorPosition - buttonSize / 2, yCursorPosition - buttonSize / 2, buttonSize, buttonSize);
        button.setBorder(new RoundedButtonBorder(buttonSize));
        button.setContentAreaFilled(false);

        //create the vertex
        Vertex vertex = new Vertex("q"+graph.getSize());
        graph.addVertex(vertex);
        graph.printGraph();

        //store button and vertex
        buttons.put(button, vertex);

        contentPane.add(button);
        contentPane.revalidate();
        contentPane.repaint();
    }

    @Override
    public void run() {
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Graph Editor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(null);
        contentPane.addMouseListener(this);
        contentPane.setBackground(Color.WHITE);

        initializeMenuBar();

        frame.setSize(600, 400);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void initializeMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu editGraph = new JMenu("Edit Graph");
        ButtonGroup editOptionGroup = new ButtonGroup();
        connectVertices = new JRadioButtonMenuItem("Connect vertices");
        removeVertex = new JRadioButtonMenuItem("Remove vertices");
        setStart = new JRadioButtonMenuItem("Set starting vertex");
        setFinal = new JRadioButtonMenuItem("Set final vertices");

        JMenu tgiAlgorithms = new JMenu("TGI Algorithms");
        JMenuItem minimize = new JMenuItem("Minimize Graph");
        JMenuItem neaToDea = new JMenuItem("NEA into DEA");

        connectVertices.setSelected(true);
        editGraph.add(connectVertices);
        editGraph.add(removeVertex);
        editGraph.add(setFinal);
        editGraph.add(setStart);
        editOptionGroup.add(connectVertices);
        editOptionGroup.add(removeVertex);
        editOptionGroup.add(setFinal);
        editOptionGroup.add(setStart);

        tgiAlgorithms.add(minimize);
        tgiAlgorithms.add(neaToDea);

        jMenuBar.add(editGraph);
        jMenuBar.add(tgiAlgorithms);

        minimize.addActionListener(e -> {
            System.out.println("Hallo");
            //TODO
        });

        neaToDea.addActionListener(e -> {
            System.out.println("Tschüss");
            //TODO
        });
        frame.setJMenuBar(jMenuBar);
    }

    public EditOptionSelected getSelectedEditOption(){
        if (connectVertices.isSelected()) return EditOptionSelected.CONNECT_VERTICES;
        if (removeVertex.isSelected()) return EditOptionSelected.REMOVE_VERTEX;
        if (setStart.isSelected())return EditOptionSelected.SET_START;
        if (setFinal.isSelected())return EditOptionSelected.SET_FINAL;
        throw new RuntimeException("no valid graph editing selection");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new MainGUI());
    }
}