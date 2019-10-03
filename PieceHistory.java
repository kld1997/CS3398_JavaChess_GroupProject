import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
public class PieceHistory extends JPanel
{
    private Stack<JLabel> moveList = new Stack<JLabel>();
    private ArrayList<JLabel> oldMoves = new ArrayList<JLabel>();
    public PieceHistory()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension d = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.1), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
        setMinimumSize(d);
    }
    public void addMove(String move)
    {
        JLabel newMove = new JLabel(move, SwingConstants.CENTER);
        if(moveList.size() >= 35){ moveList.pop(); }
        moveList.push(newMove);
        update();
    }
    public void rewind()
    {
        while(!moveList.empty())
        {
            oldMoves.add(moveList.pop());
        }
    }
    public void update()
    {
        removeAll();
        for(int i = 0; i < moveList.size(); i ++)
        {
            System.out.println("Adding");
            add(moveList.get(i));
        }
        repaint();
    }

}
