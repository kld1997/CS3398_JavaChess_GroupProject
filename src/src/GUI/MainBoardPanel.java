package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;
import Engine.*;
import Saves.*;
import Visuals.*;
import Pieces.*;
import Options.Options;

//Main Grid of
public class MainBoardPanel extends JPanel
{
    public Board gameBoard;
    GenericInfoPanel top;
    HistoryPanel right;
    boolean locked = false;
    int pawnID = 0;
    public ChessSquare[][] squares = new ChessSquare[8][8];
    public int[] lastClicked = new int[2];
    public ArrayList<ChessSquare> highlightedSquares = new ArrayList<ChessSquare>();
    int yourTurn;

    ChessGui thisGui;
    MyGlassPane glass;
    public MainBoardPanel(ChessGui g)
    {
        thisGui = g;
        gameBoard = g.gameBoard;
        if(gameBoard.options.getOnline()) {
        	yourTurn = gameBoard.options.getTurn();
        }
        thisGui.menu = new MenuBuilder(this);
        setLayout(new GridLayout(8,8));
        ButtonListener.setBoardPanel(this);
        boardSet();
        updateBoard();
        glass = new MyGlassPane(thisGui.getContentPane(), thisGui.menu);
        thisGui.menu.menu.addMenuListener(glass);
        thisGui.menu.cMenu.frame.addWindowFocusListener(glass);
        SaveState.createMenu().saveMenu.addWindowFocusListener(glass);
        thisGui.setGlassPane(glass);
        glass.setVisible(true);
    }
    public void boardSet() {

    	int counter = 0;
        for(int i = 0; i < 8; i ++)
        {
            counter = Math.abs(counter-1);
            for(int j = 0; j < 8; j ++)
            {
                ChessSquare newSquare = new ChessSquare(counter, i, j);
                squares[i][j] = newSquare;
                MouseListener[] temp = newSquare.getMouseListeners();
                newSquare.removeMouseListener(temp[0]);
                newSquare.setFocusable(false);
                newSquare.addMouseListener(new ButtonListener());
                newSquare.addMouseMotionListener(new ButtonListener());
                add(squares[i][j]);
                counter = Math.abs(counter-1);
            }
        }
    }

    public void cpuMakeMove(ChessGui g) {
    	if(gameBoard.cpuStart()) {
    		moveMade(g);
    		updateBoard();
    	}
    }

    public void moveMade(ChessGui g) {
    	if(g.getInfoPanel().getType() == 1)
        {
            ((BulletInfoPanel)g.getInfoPanel()).pauseAndSwitch();
        }
        g.getInfoPanel().switchTeam();
        g.menu.teamChange();
    }

    public void setLocked(boolean val)
    {
        locked = val;
    }
    public ArrayList<ChessSquare> displayPossibleMoves(long board)                 //Highlights possible moves with board method
    {
        ArrayList<ChessSquare> retList = new ArrayList<ChessSquare>();
        for(int x = 0; x < 8; x ++)
        {
            for(int y = 0; y < 8;y ++)
            {
                if(((board>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].Highlight();
                    retList.add(squares[x][y]);
                }
            }
        }
        return retList;
    }
    public String convertStrings(int x, int y)                                                                     //Converts coordinates to a different form
    {
        String ret = "";
        ret+= (char)(y + 97);
        ret+= 8-x;
        return ret;
    }

    public void updateBoard()
    {
        top = thisGui.getInfoPanel();
        right = thisGui.getHisotryPanel();

        if(PawnPromote.promotion == true) {
            right.pp.showPanel(gameBoard.teamTurn, PawnPromote.coord);
            locked = true;
        }
        if(gameBoard.check[0] == 1)
        {
            top.setCheck(1);
        }
        else if(gameBoard.check[1] == 1)
        {
            top.setCheck(0);
        }
        if(gameBoard.teamWon != 2){ top.setWinner(gameBoard.teamWon); }
        for(int x = 0; x < 8; x ++)
        {
            for(int y = 0; y < 8; y++)
            {
              if(!highlightedSquares.contains(squares[x][y])) {
                squares[x][y].setIcon(null);
                squares[x][y].setActionCommand("White  " + x + " " + y);
              }
            }
        }

        String teamName;
        long temp;
        int tempx;
        int tempy;

        for(int i = 0; i < Board.teamNum; i++) {
    		if(i == 0)
    			teamName = "White";
    		else
    			teamName = "Black";

    		for(Piece piece : gameBoard.pieceList.get(i).values()) {
    			temp = piece.piece;

    			while(temp != 0) {
    				tempx = Long.numberOfTrailingZeros(temp)/8;
    				tempy = Long.numberOfTrailingZeros(temp)%8;

    				squares[tempx][tempy].setIcon(new ImageIcon(piece.image));
    				squares[tempx][tempy].setActionCommand(teamName + " " + piece.name + " " + tempx + " " + tempy);

    				temp &= temp - 1;
    			}
    		}
        }
        repaint();
    }
    /*
    @Override
    public String toString() {
        return gameBoard.displayArray();
    }
     */
    public ChessSquare[][] getSquares()
    {
        return squares;
    }

}
