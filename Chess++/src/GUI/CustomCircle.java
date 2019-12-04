package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class CustomCircle {

    private static final int IMG_WIDTH = 50;
    private static final int GAP = 4;
    public BufferedImage circleImg;

    public CustomCircle(Color c) {
        Color SHAPE_COLOR = Color.RED;
        circleImg = new BufferedImage(IMG_WIDTH, IMG_WIDTH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c);
        int x = GAP;
        int y = x;
        int width = IMG_WIDTH - 2 * x;
        int height = IMG_WIDTH - 2 * y;
        g2.fillOval(x, y, width, height);
    }

    public BufferedImage getCircle() {
        return circleImg;
    }
}
