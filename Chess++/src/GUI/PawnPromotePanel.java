package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Engine.Board;
import Engine.PawnPromote;
import Pieces.Piece;
import Visuals.Images;

public class PawnPromotePanel extends JPanel
{
    MainBoardPanel mp;
    char promoButtonClicked;
    ChessGui thisGui;
    public PawnPromotePanel()
    {
        setLayout(new GridLayout(2, 2));
    }
    public void showPanel(Board board, int team, long coord)
    {
        int teamIcon = Math.abs(team - 1);
        for (char c : board.options.getPromote().toCharArray()) {
        	Piece p = board.pieceList.get(team).get(c);
            JButton choiceButton = new JButton();
            choiceButton.setIcon(new ImageIcon(Images.pieces[teamIcon][p.iconNum]));
            choiceButton.setActionCommand(p.ID + "");
            choiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mp = (MainBoardPanel)thisGui.getMainPanel();
                    mp.setLocked(false);
                    JButton temp = (JButton) e.getSource();
                    promoButtonClicked = temp.getActionCommand().charAt(0);
                    PawnPromote.promotePawn(team, coord, board, promoButtonClicked);
                    mp.cpuMakeMove(thisGui);
                    temp.getParent().removeAll();
                    repaint();
                    mp.updateBoard();
                }
            });
            add(choiceButton);
        }
    }
    public void setGui(ChessGui g)
    {
        thisGui = g;
    }
}
