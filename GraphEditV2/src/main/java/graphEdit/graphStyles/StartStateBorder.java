package graphEdit.graphStyles;

import graphEdit.MainGUI;

import java.awt.*;

public class StartStateBorder extends RoundedButtonBorder {

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, MainGUI.buttonRadius-1, MainGUI.buttonRadius-1);
        int newX = x + 3;
        int newY = x + 3;
        int pointSize = 10;
        g.fillOval(newX, newY, pointSize, pointSize);
        g.drawLine(x, y, newX, newY);
    }
}
