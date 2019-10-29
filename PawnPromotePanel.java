import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PawnPromotePanel extends JPanel
{
    MainBoardPanel mp;
    int promoButtonClicked = 0;
    ChessGui thisGui;
    public PawnPromotePanel()
    {
        setLayout(new GridLayout(2, 2));
    }
    public void showPanel(int team, long coord)
    {
        int teamIcon = Math.abs(team - 1);
        for (int i = 1; i < 5; i++) {
            JButton choiceButton = new JButton();
            choiceButton.setIcon(new ImageIcon(mp.pieces[teamIcon][i]));
            choiceButton.setActionCommand(i + "");
            choiceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mp = (MainBoardPanel)thisGui.getMainPanel();
                    mp.setLocked(false);
                    JButton temp = (JButton) e.getSource();
                    promoButtonClicked = Integer.parseInt(temp.getActionCommand());
                    mp.pawnID = promoButtonClicked;
                    PawnPromote.promotePawn(team, coord, mp.gameBoard, mp.pawnID);

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
