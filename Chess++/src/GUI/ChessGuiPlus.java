package GUI;

import java.awt.*;
import Engine.*;

public class ChessGuiPlus extends ChessGui
{

    public ChessGuiPlus(Board b, int onlineSet)
    {
    	online = onlineSet;
        topPanel = new InfoPanel(this);
        mainPanel = new ChessPanel(this, true);
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
}
