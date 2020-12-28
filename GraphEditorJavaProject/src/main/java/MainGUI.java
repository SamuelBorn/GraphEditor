import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MainGUI extends MouseAdapter implements Runnable {
    private JPanel contentPane;

    public void mouseClicked(MouseEvent mouseEvent) {
        int xCursorPosition = mouseEvent.getX();
        int yCursorPosition = mouseEvent.getY();

        int buttonSize = 50;
        JButton button = new JButton("1");
        button.setBounds(xCursorPosition-buttonSize/2, yCursorPosition-buttonSize/2, buttonSize, buttonSize);
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
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(null);
        contentPane.addMouseListener(this);
        contentPane.setBackground(Color.WHITE);
        frame.setSize(600,400);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new MainGUI());
    }
}