import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class ChessGui extends JFrame
{
        private JPanel mainBoard = new JPanel();
        private JPanel leftPanel = new JPanel();
        private JPanel bottomPanel = new JPanel();
        private JPanel topPanel = new JPanel();
        ChessSquare[][] squares = new ChessSquare[8][8];
        public ChessGui()
        {
            setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.75), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*.15), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.15));
            setLayout(new BorderLayout());
            topPanel.setLayout(new BorderLayout());
            mainBoard.setLayout(new GridLayout(8, 8));
            leftPanel.setLayout(new GridLayout(8,0));
            bottomPanel.setLayout(new GridLayout(0,8));

            boardSetUp();
            sidesSetup();

            add(mainBoard);
            add(leftPanel, BorderLayout.WEST);
            add(bottomPanel, BorderLayout.SOUTH);
            setVisible(true);
        }
        private void sidesSetup()
        {
            for(int i = 8; i > 0; i--)
            {
                JLabel newLabel = new JLabel(i + "", SwingConstants.CENTER);
                newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 25));

                leftPanel.add(newLabel);
            }
            for(int i = 65; i < 73; i ++) //Char values for A-H
            {
                String letter = Character.toString((char)i);
                JLabel newLabel = new JLabel(letter, SwingConstants.CENTER);
                newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 20));

                bottomPanel.add(newLabel);
            }

        }
        private void boardSetUp()
        {
            int counter = 0;
            for(int x = 0;x < 8; x ++) //Adding tiles to board
            {
                counter = Math.abs(counter-1);
                for(int y = 0; y < 8; y ++)
                {
                    ChessSquare newSquare = new ChessSquare(counter, x, y);
                    newSquare.setActionCommand(x + " " + y);
                    newSquare.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent)
                        {
                            ChessSquare temp = (ChessSquare) actionEvent.getSource();
                            temp.Highlight();
                            System.out.println(actionEvent.getActionCommand()); //Action Command is set to coordinates on board of where you clicked.
                        }                                                       //Note: These coordinates don't correspond to the ones shown in the GUI, and instead
                    });                                                         //      show their indexes in the array squares
                    mainBoard.add(newSquare);
                    counter = Math.abs(counter - 1);
                    squares[x][y] = newSquare;

                }
            }
        }
}
