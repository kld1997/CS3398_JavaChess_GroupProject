import javax.swing.*;
import java.awt.*;

public class ChessGui extends JFrame
{
    public GenericInfoPanel topPanel;
    public HistoryPanel rightPanel;
    public ChessPanel mainPanel;
    JPanel centerPanel = new JPanel();
    public ChessGui(int gameMode, boolean noHighlight)
    {
        switch(gameMode)
        {
            case 0: //Default
                topPanel = new InfoPanel(this);
                mainPanel = new ChessPanel(this, false, noHighlight);
                break;
            case 1: //Bullet
                topPanel = new BulletInfoPanel(this);
                mainPanel = new ChessPanel(this, false, noHighlight);
                break;
            case 2: //Plus plus
                topPanel = new InfoPanel(this);
                mainPanel = new ChessPanel(this, true, noHighlight);
                break;
        }
        guiBuild();
    }
    public void guiBuild()
    {
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
    public void addPanels()
    {
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(mainPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    public MainBoardPanel getMainPanel()
    {
        return mainPanel.getMainPanel();
    }
    public HistoryPanel getHisotryPanel()
    {
        return rightPanel;
    }
    public GenericInfoPanel getInfoPanel()
    {
        return topPanel;
    }
}
