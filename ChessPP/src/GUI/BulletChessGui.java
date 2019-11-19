package GUI;

import javax.swing.*;
import java.awt.*;
import Engine.*;

public class BulletChessGui extends ChessGui
{
    public BulletChessGui()
    {
        topPanel = new BulletInfoPanel(this, new int[]{0, 1});
        mainPanel = new ChessPanel(this);
        rightPanel = new HistoryPanel(this);
        mainPanel.getMainPanel().updateBoard();
        rightPanel.pp.setGui(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.7), (int)(screenSize.getHeight()*.75));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.15), (int) (screenSize.getHeight()*.15));
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
    }
    public int getGuiType(){ return 1; }
}
