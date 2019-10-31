
import javax.swing.*;
import java.awt.*;

//Sie panel for chess gui, include pawn promotion panel and piece history
public class HistoryPanel extends JPanel
{
    PieceHistory ph = new PieceHistory();
    MainBoardPanel mp;
    PawnPromotePanel pp;
    public HistoryPanel(ChessGui g)
    {
        setLayout(new GridLayout(2, 1));
        mp = g.getMainPanel();
        pp = new PawnPromotePanel();
        add(ph);
        add(pp);
    }
}
