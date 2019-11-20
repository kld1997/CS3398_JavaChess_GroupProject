package GUI;

import javax.swing.*;
import java.util.Timer;
import java.awt.*;

public class BulletInfoPanel extends GenericInfoPanel
{
    Timer wt, bt;
    JLabel whiteTime, blackTime;
    ChessGui thisGui;
    public BulletInfoPanel(ChessGui g, int[] st)
    {
        thisGui = g;
        setLayout(new GridLayout(1, 3));
        teamText = new JLabel();
        teamText.setFont(new Font("Serif", Font.BOLD, 20));
        teamText.setHorizontalAlignment(JLabel.CENTER);
        teamText.setText("White Turn");

        whiteTime = new JLabel();
        blackTime = new JLabel();
        setUpTimers(st);
        add(whiteTime);
        add(teamText, BorderLayout.CENTER);
        add(blackTime);
    }
    public void setUpTimers(int[] st)
    {
        whiteTime.setFont(new Font("Serif", Font.BOLD, 25));
        whiteTime.setHorizontalAlignment(JLabel.LEFT);
        blackTime.setFont(new Font("Serif", Font.BOLD, 25));
        blackTime.setHorizontalAlignment(JLabel.RIGHT);
        wt = new Timer();
        wt.scheduleAtFixedRate(new ChessTimer(thisGui, whiteTime, st[0]), 0, 1000);
        bt = new Timer();
        int secs = st[1]%60;
        String ss;
        if(secs == 0)
        	ss = "00";
        else if(secs < 10)
        	ss = "0" + secs;
        else
        	ss = "" + secs;
        String bst = "" + st[1]/60 + ":" + ss;
        blackTime.setText(bst);
    }
    public void pauseAndSwitch()
    {
        int tempSec, tempMin;
        if (currTeam == 0) {
            tempMin = Integer.parseInt(blackTime.getText().substring(0, blackTime.getText().indexOf(':')));
            tempSec = Integer.parseInt(blackTime.getText().substring(blackTime.getText().indexOf(':') + 1)) + tempMin * 60;
            bt.scheduleAtFixedRate(new ChessTimer(thisGui, blackTime, tempSec), 0, 1000);
            wt.cancel();
            wt = new Timer();
        } else {
            tempMin = Integer.parseInt(whiteTime.getText().substring(0, whiteTime.getText().indexOf(':')));
            tempSec = Integer.parseInt(whiteTime.getText().substring(whiteTime.getText().indexOf(':') + 1)) + tempMin * 60;
            wt.scheduleAtFixedRate(new ChessTimer(thisGui, whiteTime, tempSec), 0, 1000);
            bt.cancel();
            bt = new Timer();
        }
    }

    @Override
    public int getType() {
        return 1;
    }
}
