import java.awt.*;

public class ChessGuiPlus extends ChessGui
{

    public ChessGuiPlus(Board b)
    {
        topPanel = new InfoPanel(this);
        mainPanel = new ChessPanel(this, true);
        rightPanel = new HistoryPanel(this);
        mainPanel.getMainPanel().updateBoard();
        rightPanel.pp.setGui(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.6), (int)(screenSize.getHeight()*.85));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.1), (int) (screenSize.getHeight()*.1));
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
    }
}
