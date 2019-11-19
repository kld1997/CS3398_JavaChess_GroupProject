package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ChessSquare extends JButton
{
    int x;
    int y;
    public ChessSquare(int color, int xCoord, int yCoord)
    {
        x = xCoord;
        y = yCoord;
        if(color == 0)
        {
            setBackground(Color.BLACK);
        }
        else
        {
            setBackground(Color.WHITE);
        }
        setVisible(true);
    }
    public void Highlight()
    {
        setBorder(BorderFactory.createLineBorder(Color.RED, 5));
    }
    public void unHighlight()
    {
        setBorder(null);
    }

    @Override
    public String toString()
    {
        return "X: " + x + ", Y: " + y;
    }
}
