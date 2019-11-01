package GUI;

import javax.swing.*;
import java.util.Timer;
import java.awt.*;

public class BulletInfoPanel extends GenericInfoPanel
{
    Timer wt, bt;
    JLabel whiteTime, blackTime;
    BulletChessGui thisGui;
    public BulletInfoPanel(BulletChessGui g)
    {
        thisGui = g;
        setLayout(new GridLayout(1, 3));
        teamText = new JLabel();
        teamText.setFont(new Font("Serif", Font.BOLD, 20));
        teamText.setHorizontalAlignment(JLabel.CENTER);
        teamText.setText("White Turn");

        whiteTime = new JLabel();
        blackTime = new JLabel();
        setUpTimers();
        add(whiteTime);
        add(teamText, BorderLayout.CENTER);
        add(blackTime);
    }
    public void setUpTimers()
    {
        whiteTime.setFont(new Font("Serif", Font.BOLD, 25));
        whiteTime.setHorizontalAlignment(JLabel.LEFT);
        blackTime.setFont(new Font("Serif", Font.BOLD, 25));
        blackTime.setHorizontalAlignment(JLabel.RIGHT);
        wt = new Timer();
        wt.scheduleAtFixedRate(new ChessTimer(thisGui, whiteTime, 120), 0, 1000);
        bt = new Timer();
        blackTime.setText("2:00");
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
