import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ChessSquare extends JButton
{
    public ChessSquare(int color, int xCoord, int yCoord)
    {
        //setBorder(BorderFactory.createLineBorder(Color.RED, 2));
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

}
