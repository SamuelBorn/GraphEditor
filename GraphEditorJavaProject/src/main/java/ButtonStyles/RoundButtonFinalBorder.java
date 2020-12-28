package ButtonStyles;

import java.awt.*;

public class RoundButtonFinalBorder extends RoundedButtonBorder{

    RoundButtonFinalBorder(int radius) {
        super(radius);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        double innerRadiusMuliplicator = 0.87;
        int smallRadius = (int) (innerRadiusMuliplicator * radius);
        int offset = (int) ((1 - innerRadiusMuliplicator) / 2 * radius);

        g.drawOval(x + offset, y + offset,smallRadius , smallRadius);
        g.drawOval(x, y, radius, radius);
    }
}
