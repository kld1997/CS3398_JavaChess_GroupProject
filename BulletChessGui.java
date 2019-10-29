import javax.swing.*;
import java.awt.*;

public class BulletChessGui extends ChessGui
{
    public BulletChessGui(Board b)
    {
        topPanel = new BulletInfoPanel(this);
        mainPanel = new ChessPanel(this);
        rightPanel = new HistoryPanel(this);
        mainPanel.getMainPanel().updateBoard();
        rightPanel.pp.setGui(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.7), (int)(screenSize.getHeight()*.85));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.1), (int) (screenSize.getHeight()*.1));
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
    }
    public int getGuiType(){ return 1; }
}
