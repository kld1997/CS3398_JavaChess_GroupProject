package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
    ImageIcon highlightMarker;
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
        if(this.getIcon() != null && this.getIcon() != highlightMarker) {
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(this.getBackground(), 1),
                      BorderFactory.createLineBorder(new Color(0xFFFFFFFF - this.getBackground().getRGB()), 4)));
        }
        else {
            highlightMarker = new ImageIcon(new CustomCircle(new Color((0xFFFFFFFF - this.getBackground().getRGB()) + 0xFF000000)).getCircle());
            this.setIcon(highlightMarker);
            this.setBorder(new EmptyBorder(1, 1, 1, 1));
        }

    }
    public void unHighlight()
    {
        setBorder(null);
        if(this.getIcon() == highlightMarker)
          this.setIcon(null);
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
