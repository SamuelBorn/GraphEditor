package graphEdit.graphStyles;

import java.awt.*;

public class StartAndFinalStateBorder extends RoundedButtonBorder{
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        new FinalStateBorder().paintBorder(c,g,x,y,width,height);
        new StartStateBorder().paintBorder(c,g,x,y,width,height);
    }
}
