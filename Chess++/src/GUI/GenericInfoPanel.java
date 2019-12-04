package GUI;

import javax.swing.*;

public abstract class GenericInfoPanel extends JPanel
{
    public int currTeam = 0;
    public JLabel teamText;
    public GenericInfoPanel(){}
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
    public int getType(){ return -1; }
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
        if(t == 0)
        {
            teamText.setText("White team wins!");
        }
        else if(t == 1)
        {
            teamText.setText("Black team wins!");
        }
        else if(t == -1)
        {
        	teamText.setText("Stalemate!");
        }
    }
}
