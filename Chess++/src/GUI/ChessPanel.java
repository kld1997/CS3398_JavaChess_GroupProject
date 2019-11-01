package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChessPanel extends JPanel
{
    MainBoardPanel mbp;
    JPanel leftPanel, botPanel;
    public static BufferedImage colors[][] = new BufferedImage[3][6];

    public ChessPanel(ChessGui g, boolean plusPlus)
    {
        mbp = new MainBoardPanel(g, plusPlus);
        leftPanel = new JPanel();
        botPanel = new JPanel();
        setLayout(new BorderLayout());
        setupSides();
        add(mbp, BorderLayout.CENTER);
    }
    public void setupSides()
    {
        leftPanel.setLayout(new GridLayout(8, 1));
        botPanel.setLayout(new GridLayout(1, 8));
        for(int i = 8; i > 0; i--)
        {
            JLabel newLabel = new JLabel(i + "", SwingConstants.CENTER);
            newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 25));

            leftPanel.add(newLabel);
        }
        //Bottom side letters
        for(int i = 65; i < 73; i ++) //Char values for A-H
        {
            String letter = Character.toString((char)i);
            JLabel newLabel = new JLabel(letter, SwingConstants.CENTER);
            newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 20));

            botPanel.add(newLabel);
        }
        add(leftPanel, BorderLayout.WEST);
        add(botPanel, BorderLayout.SOUTH);
    }
    public MainBoardPanel getMainPanel()
    {
        return mbp;
    }
}
