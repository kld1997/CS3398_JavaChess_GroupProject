package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

//Top Panel of ChessGui
//Has Labels for time and team.
public class InfoPanel extends GenericInfoPanel
{
    ChessGui thisGui;
    public InfoPanel(ChessGui g)
    {
        thisGui = g;
        currTeam = g.gameBoard.teamTurn;
        setLayout(new BorderLayout());
        teamText = new JLabel();
        teamText.setFont(new Font("Serif", Font.BOLD, 20));
        teamText.setHorizontalAlignment(JLabel.CENTER);
        setTeamText(currTeam);
        add(teamText, BorderLayout.CENTER);
    }

    @Override
    public int getType() {
        return 0;
    }
}
