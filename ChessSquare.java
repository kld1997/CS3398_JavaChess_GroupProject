import javax.swing.*;
import java.awt.*;

public class ChessSquare extends JButton
{
    public ChessSquare(int color)
    {
        //setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        if(color == 0)
        {
            setBackground(Color.BLACK);
        }
        setVisible(true);
    }
    public void Highlight()
    {
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
    }
}
