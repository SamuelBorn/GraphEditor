import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.*;

public class MainGUI extends MouseAdapter implements Runnable {
    private JFrame frame;
    private JPanel contentPane;
    private Graph graph;
    private Hashtable<JButton, Vertex> buttons;

    public void mouseClicked(MouseEvent mouseEvent) {
        int xCursorPosition = mouseEvent.getX();
        int yCursorPosition = mouseEvent.getY();

        int buttonSize = 45;
        JButton button = new JButton("1");
        button.setBounds(xCursorPosition - buttonSize / 2, yCursorPosition - buttonSize / 2, buttonSize, buttonSize);
        button.setBorder(new RoundedButtonBorder(buttonSize));
        button.setContentAreaFilled(false);

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
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem connectVertices = new JRadioButtonMenuItem("Connect vertices");
        JRadioButtonMenuItem removeVertex = new JRadioButtonMenuItem("Remove vertices");
        JRadioButtonMenuItem setStart = new JRadioButtonMenuItem("Set starting vertex");
        JRadioButtonMenuItem setFinal = new JRadioButtonMenuItem("Set final vertices");

        JMenu tgiAlgorithms = new JMenu("TGI Algorithms");
        JMenuItem minimize = new JMenuItem("Minimize Graph");
        JMenuItem neaToDea = new JMenuItem("NEA into DEA");

        connectVertices.setSelected(true);
        editGraph.add(connectVertices);
        editGraph.add(removeVertex);
        editGraph.add(setFinal);
        editGraph.add(setStart);
        group.add(connectVertices);
        group.add(removeVertex);
        group.add(setFinal);
        group.add(setStart);

        tgiAlgorithms.add(minimize);
        tgiAlgorithms.add(neaToDea);

        jMenuBar.add(editGraph);
        jMenuBar.add(tgiAlgorithms);

        minimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hallo");
                //TODO
            }
        });

        neaToDea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tschüss");
                //TODO
            }
        });

        frame.setJMenuBar(jMenuBar);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new MainGUI());
    }
}