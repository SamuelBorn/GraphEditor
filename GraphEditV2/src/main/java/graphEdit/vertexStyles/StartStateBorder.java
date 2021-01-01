package graphEdit.vertexStyles;

import graphEdit.MainGUI;

import java.awt.*;

public class StartStateBorder extends RoundedButtonBorder{

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, MainGUI.buttonRadius, MainGUI.buttonRadius);
        //TODO: make an arrow appear
    }
}
