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
        //JLabel newMove = new JLabel("Placeholder", SwingConstants.CENTER);
        if(moveList.size() >= 35){ moveList.pop(); moveCoords.pop(); }
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
