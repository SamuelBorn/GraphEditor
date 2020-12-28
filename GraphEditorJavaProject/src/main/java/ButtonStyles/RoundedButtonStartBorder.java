package ButtonStyles;

import java.awt.*;

public class RoundedButtonStartBorder extends RoundedButtonBorder{

    public RoundedButtonStartBorder(int radius) {
        super(radius);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, radius, radius);
        //TODO: make an arrow appear
    }
}
