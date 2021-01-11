package graphEdit.graphStyles;

import java.awt.*;

/**
 * this class is used as a vertex could be start and final
 */
public class StartAndFinalStateBorder extends RoundedButtonBorder{
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        new FinalStateBorder().paintBorder(c,g,x,y,width,height);
        new StartStateBorder().paintBorder(c,g,x,y,width,height);
    }
}
