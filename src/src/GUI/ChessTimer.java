package GUI;

import java.util.*;
import java.util.Timer;
import javax.swing.*;
public class ChessTimer extends TimerTask
{
    JLabel timerLabel;
    ChessGui cg;
    int startingTime;
    int secondsPassed = 0;
    public ChessTimer(ChessGui g, JLabel timer, int st)
    {
        cg = g;
        startingTime = st;
        timerLabel = timer;
    }
    @Override
    public void run()
    {
        int min, secs;
        int currTime = startingTime - secondsPassed;
        if(currTime == 0)
        {
            cg.getMainPanel().setLocked(true);
            if(cg.getInfoPanel().getCurrTeam() == 0) { cg.getInfoPanel().setWinner(1); }
            else { cg.getInfoPanel().setWinner(0); }

            cancel();
        }
        min = currTime/60;
        secs = currTime%60;
        String ss;
        if(secs == 0)
        	ss = "00";
        else if(secs < 10)
        	ss = "0" + secs;
        else
        	ss = "" + secs;
        
        timerLabel.setText(min + ":" + ss);
        secondsPassed++;
    }
}
