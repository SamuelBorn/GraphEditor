package graphEdit.graphStyles;

import graphEdit.MainGUI;

import java.awt.*;

/**
 * just have two circles as an button border to indicate final state
 */
public class FinalStateBorder extends RoundedButtonBorder{

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        double innerRadiusMultiplication = 0.87;
        int smallRadius = (int) (innerRadiusMultiplication * MainGUI.buttonRadius);
        int offset = (int) ((1 - innerRadiusMultiplication) / 2 * MainGUI.buttonRadius);

        g.drawOval(x + offset, y + offset,smallRadius , smallRadius);
        g.drawOval(x, y, MainGUI.buttonRadius-1, MainGUI.buttonRadius-1);
    }
}
