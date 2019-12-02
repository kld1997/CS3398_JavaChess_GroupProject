package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

//Top Panel of ChessGui
//Has Labels for time and team.
public class InfoPanel extends GenericInfoPanel
{

    public InfoPanel(int turn)
    {
    	currTeam = turn;
        setLayout(new BorderLayout());
        teamText = new JLabel();
        teamText.setFont(new Font("Serif", Font.BOLD, 20));
        teamText.setHorizontalAlignment(JLabel.CENTER);
        if(turn == 0)
        	teamText.setText("White Turn");
        else
        	teamText.setText("Black Turn");
        add(teamText, BorderLayout.CENTER);
    }

    @Override
    public int getType() {
        return 0;
    }
}
