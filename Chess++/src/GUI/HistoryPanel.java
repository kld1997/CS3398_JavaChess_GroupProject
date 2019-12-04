package GUI;

import javax.swing.*;

import Engine.PawnPromote;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

//Sie panel for chess gui, include pawn promotion panel and piece history
public class HistoryPanel extends JPanel
{
    MainBoardPanel mp;
    PieceHistory ph;
    JScrollPane sp;
    PawnPromotePanel pp;
    JButton rw;
    public HistoryPanel(ChessGui g)
    {
        setLayout(new BorderLayout());
        mp = g.getMainPanel();
        ph = new PieceHistory(g);
        sp = new JScrollPane(ph);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Dimension d = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.155), 
        		(int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
        sp.setPreferredSize(d);
        pp = new PawnPromotePanel();
        pp.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.155), 
        		(int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.2)));
        if(!g.gameBoard.options.getOnline()) {
        	rewindSetup();
        	add(rw, BorderLayout.NORTH);
        }
        add(sp, BorderLayout.CENTER);
        add(pp, BorderLayout.SOUTH);
    }
    private void rewindSetup()
    {
        rw = new JButton("Rewind");
        rw.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.155), 
        		(int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.05)));
        rw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	if(!PawnPromote.promotion)
            		ph.rewind(1);
            }
        });
    }
}
