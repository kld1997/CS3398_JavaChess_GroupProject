package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

//Sie panel for chess gui, include pawn promotion panel and piece history
public class HistoryPanel extends JPanel
{
    MainBoardPanel mp;
    PieceHistory ph;
    PawnPromotePanel pp;
    JButton rw;
    public HistoryPanel(ChessGui g)
    {
        setLayout(new GridLayout(3, 1));
        mp = g.getMainPanel();
        ph = new PieceHistory(g);
        pp = new PawnPromotePanel();
        rewindSetup();
        add(ph);
        add(rw);
        add(pp);
    }
    private void rewindSetup()
    {
        rw = new JButton("Rewind");
        rw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ph.rewind(1);
            }
        });
    }
}
