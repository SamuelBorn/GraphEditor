package graphEdit.graphStyles;

import graphEdit.MainGUI;

import java.awt.*;

public class FinalStateBorder extends RoundedButtonBorder{

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        double innerRadiusMuliplicator = 0.87;
        int smallRadius = (int) (innerRadiusMuliplicator * MainGUI.buttonRadius);
        int offset = (int) ((1 - innerRadiusMuliplicator) / 2 * MainGUI.buttonRadius);

        g.drawOval(x + offset, y + offset,smallRadius , smallRadius);
        g.drawOval(x, y, MainGUI.buttonRadius, MainGUI.buttonRadius);
    }
}
