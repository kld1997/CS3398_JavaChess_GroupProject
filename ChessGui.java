import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class ChessGui extends JFrame
{
        JPanel mainLayout = new JPanel();
        ChessSquare[][] squares = new ChessSquare[8][8];
        public ChessGui()
        {

            setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.75), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));


            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*.15), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.15));

            setLayout(new GridLayout(8, 8));

            int counter = 0;
            for(int x = 0;x < 8; x ++)
            {
                counter = Math.abs(counter-1);
                for(int y = 0; y < 8; y ++)
                {
                    ChessSquare newSquare = new ChessSquare(counter);
                    add(newSquare);
                    counter = Math.abs(counter - 1);
                    squares[x][y] = newSquare;
                }
            }
            setVisible(true);
        }

}
