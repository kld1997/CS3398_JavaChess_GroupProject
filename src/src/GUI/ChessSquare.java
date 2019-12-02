package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;

public class ChessSquare extends JButton
{
    int x;
    int y;
    ImageIcon highlightMarker;
    public ChessSquare(int color, int xCoord, int yCoord)
    {
        x = xCoord;
        y = yCoord;
        if(color == 0)
        {
            setBackground(Color.BLACK);
            setBorder(null);
        }
        else
        {
            setBackground(Color.WHITE);
            setBorder(null);
        }
        setVisible(true);
    }
    public void Highlight()
    {
        if(this.getIcon() != null && this.getIcon() != highlightMarker) {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(this.getBackground(), 1),
                      BorderFactory.createLineBorder(new Color(0xFFFFFFFF - this.getBackground().getRGB()), 4)));
        }
        else {
            highlightMarker = new ImageIcon(new CustomCircle(new Color((0xFFFFFFFF - this.getBackground().getRGB()) + 0xFF000000)).getCircle());
            this.setIcon(highlightMarker);
            this.setBorder(new EmptyBorder(1, 1, 1, 1));
        }

    }
    public void unHighlight()
    {
        setBorder(null);
        if(this.getIcon() == highlightMarker)
          this.setIcon(null);
    }

    @Override
    public String toString()
    {
        return "X: " + x + ", Y: " + y;
    }
}
