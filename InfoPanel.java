import javax.swing.*;
import java.util.Observer;

//Top Panel of ChessGui
//Has Labels for time and team.
public class InfoPanel extends JPanel
{
    private int currTeam = 0;
    private JLabel teamText;
    public InfoPanel()
    {
        teamText = new JLabel();
        teamText.setText("White Turn");
        add(teamText);
    }
    public int getCurrTeam()
    {
        return currTeam;
    }
    public void setTeamText(int ct)
    {
        if(ct == 1)
        {
            teamText.setText("Black Turn");
        }
        else
        {
            teamText.setText("White Turn");
        }
    }
    public void switchTeam()
    {
        currTeam = currTeam==0?1:0;
        setTeamText(currTeam);
    }
    public void setCheck(int t)
    {
        /*
            1 means white is in check
            else black is in check
         */
        if(t == 1)
        {
            teamText.setText("Check! White Turn");
        }
        else
        {
            teamText.setText("Check! BlackTurn");
        }
    }
    public void setWinner(int t)
    {
        if(t == 1)
        {
            teamText.setText("White team wins!");
        }
        else
        {
            teamText.setText("Black team wins!");
        }
    }
}
