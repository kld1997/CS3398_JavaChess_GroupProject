package GUI;
import Engine.Board;
import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
public class PieceHistory extends JPanel
{
    private Stack<JLabel> moveList = new Stack<JLabel>();
    private Stack<GameMove> moveCoords = new Stack<GameMove>();
    private ChessGui cg;
    public PieceHistory(ChessGui g)
    {
        cg = g;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension d = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.155), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
        setPreferredSize(d);
    }
    public void addMove(String move)
    {
        JLabel newMove = new JLabel(move, SwingConstants.CENTER);
        if(moveList.size() >= 35){ moveList.pop(); moveCoords.pop();}
        moveList.push(newMove);
        update();
    }
    public void addMove(GameMove gm)
    {
        String temp = getPiece(gm.getID()) + "";
        temp += " moved from " + gm.getFrom().toString() + " to " + gm.getTo().toString();
        JLabel newMove = new JLabel(temp, SwingConstants.CENTER);
        if(moveList.size() >= 35){ moveList.pop(); moveCoords.pop(); }
        moveList.push(newMove);
        moveCoords.push(gm);
        update();
    }
    public void rewind(int num)
    {
        cg.getMainPanel().updateBoard();
        if(num > moveList.size())
        {
            num = moveList.size();
        }
        for(int i = 0; i < num; i ++)
        {
            if(moveList.pop() != null)
            {
                cg.gameBoard.rewindMove(moveCoords.pop());
                cg.getInfoPanel().switchTeam();
            }
        }
        update();
        cg.getMainPanel().updateBoard();


    }
    public static String getPiece(char id)
    {
        switch(id)
        {
            case 'a': return "Archer";
            case 'b': return "Bishop";
            case 'B': return "Builder";
            case 'K': return "King";
            case 'k': return "Knight";
            case 'p': return "Pawn";
            case 'q': return "Queen";
            case 'r': return "Rook";
            case 'W': return "Wall";
            default: return "N";
        }
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
