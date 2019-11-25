package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ChessSquare extends JButton
{
    private int x;
    private int y;
    private String team;
    private char ID;
    private Coordinate positionCommand;
    public ChessSquare(int color, int xCoord, int yCoord)
    {
        ID = 'N';
        team = "None";
        x = xCoord;
        y = yCoord;
        if(color == 0)
        {
            setBackground(Color.BLACK);
        }
        else
        {
            setBackground(Color.WHITE);
        }
        setVisible(true);
    }
    public void Highlight()
    {
        setBorder(BorderFactory.createLineBorder(Color.RED, 5));
    }
    public void unHighlight()
    {
        setBorder(null);
    }
    public void setPositionCommand(Coordinate g){ positionCommand = g;}
    public Coordinate getPositionCommand(){ return positionCommand; }
    public void setIDCommand(char i){ ID = i; }
    public char getIDCommand(){ return ID; }
    public void setTeam(int t)
    {
        if(t == 0){ team = "White"; }
        else if(t == 1){ team = "Black"; }
    }
    public int getTeam()
    {
        return team.equals("White")?0:1;
    }
    @Override
    public String toString()
    {
        return "X: " + x + ", Y: " + y;
    }
}
