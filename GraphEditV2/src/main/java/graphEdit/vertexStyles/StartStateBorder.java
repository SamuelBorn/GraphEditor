package graphEdit.vertexStyles;

import java.awt.*;

public class StartStateBorder extends RoundedButtonBorder{

    public StartStateBorder(int radius) {
        super(radius);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, radius, radius);
        //TODO: make an arrow appear
    }
}
