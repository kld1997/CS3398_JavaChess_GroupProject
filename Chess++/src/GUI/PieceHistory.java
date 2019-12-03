package GUI;
import Engine.Board;
import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import Saves.*;
public class PieceHistory extends JPanel
{
    private Stack<JLabel> moveList = new Stack<JLabel>();
    private Stack<GameMove> moveCoords = new Stack<GameMove>();
    private ChessGui cg;
    public PieceHistory(ChessGui g)
    {
        cg = g;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if(g.gameBoard.options.getLoaded()) {
            moveList = SaveState.createMenu().stackLabels;
            moveCoords = SaveState.createMenu().stackCoords;
        }
        update();
    }
    public Stack<JLabel> getLabels() {
        return moveList;
    }
    public Stack<GameMove> getMoves() {
        return moveCoords;
    }
    public void addMove(String move)
    {
        JLabel newMove = new JLabel(move, SwingConstants.CENTER);
        //if(moveList.size() >= 35){ moveList.pop(); moveCoords.pop();}
        moveList.push(newMove);
        update();
    }
    public void addMove(GameMove gm)
    {
        //JLabel newMove = new JLabel("Placeholder", SwingConstants.CENTER);
        //if(moveList.size() >= 35){ moveList.pop(); moveCoords.pop(); }
        moveCoords.push(gm);
        update();
    }
    public void rewind(int num)
    {
    	cg.getMainPanel().squares[cg.getMainPanel().lastClicked[0]][cg.getMainPanel().lastClicked[1]].setBorder(null);
    	cg.getMainPanel().lastClicked[0] = 0;
    	cg.getMainPanel().lastClicked[1] = 0;
    	cg.getMainPanel().clearLastMovedVisuals();
        if(num > moveList.size())
        {
            num = moveList.size();
        }
        for(int i = 0; i < num; i ++)
        {
            if(moveList.pop() != null)
            {
                cg.gameBoard.rewindMove();
                if(cg.gameBoard.options.getCPU() == 0)
                	cg.getInfoPanel().switchTeam();
            }
        }
        update();
        cg.getMainPanel().highlightedSquares.clear();
        cg.getMainPanel().updateBoard();
    }
    public void update()
    {
        removeAll();
        for(int i = 0; i < moveList.size(); i ++)
        {
            add(moveList.get(i));
        }
        repaint();
    }

}
