import java.util.*;
import java.util.Timer;
import javax.swing.*;
public class ChessTimer extends TimerTask
{
    JLabel timerLabel;
    BulletChessGui cg;
    int startingTime;
    int secondsPassed = 0;
    public ChessTimer(BulletChessGui g, JLabel timer, int st)
    {
        cg = g;
        startingTime = st;
        timerLabel = timer;
    }
    @Override
    public void run()
    {
        int min, sec;
        int currTime = startingTime - secondsPassed;
        if(currTime == 0)
        {
            cg.getMainPanel().setLocked(true);
            if(cg.getInfoPanel().getCurrTeam() == 0) { cg.getInfoPanel().setWinner(1); }
            else { cg.getInfoPanel().setWinner(0); }

            cancel();
        }
        min = currTime/60;
        sec = currTime - min*60;
        timerLabel.setText(min + ":" + sec);
        secondsPassed++;
    }
}
