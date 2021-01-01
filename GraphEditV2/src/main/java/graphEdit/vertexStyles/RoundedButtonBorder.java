package graphEdit.vertexStyles;

import graphEdit.MainGUI;

import javax.swing.border.Border;
import java.awt.*;

/**
 * This class is needed to display round JButtons (by default buttons are rectangular)
 * <p>
 * I used this approach to get rounded JButtons:
 * https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
 */
public class RoundedButtonBorder implements Border {

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawOval(x, y, MainGUI.buttonRadius, MainGUI.buttonRadius);
    }

    public Insets getBorderInsets(Component c) {
        //dont have any Insets. Always display text!
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque() {
        return false;
    }
}