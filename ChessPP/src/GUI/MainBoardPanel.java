package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;
import Engine.*;
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
    public MainBoardPanel(ChessGui g)
    {
        thisGui = g;
        gameBoard = g.gameBoard;
        if(gameBoard.options.getOnline()) {
        	yourTurn = gameBoard.options.getTurn();
        }
        thisGui.menu = new MenuBuilder(this);
        setLayout(new GridLayout(8,8));
        boardSet(g);
        updateBoard();
    }
    public void boardSet(ChessGui g) {

    	int counter = 0;
        for(int i = 0; i < 8; i ++)
        {
            counter = Math.abs(counter-1);
            for(int j = 0; j < 8; j ++)
            {
                ChessSquare newSquare = new ChessSquare(counter, i, j);
                squares[i][j] = newSquare;
                newSquare.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        if(!locked)
                        {
                            boolean moveMade = false;
                            String moveString = squares[lastClicked[0]][lastClicked[1]].getPositionCommand().toString();

                            ChessSquare temp = (ChessSquare) actionEvent.getSource();
                            temp.Highlight();
                            squares[lastClicked[0]][lastClicked[1]].unHighlight();
                            int[] pieceMovedPos = new int[2];
                            pieceMovedPos[0] = lastClicked[0];      //Saves previously clicked square
                            pieceMovedPos[1] = lastClicked[1];
                            for(ChessSquare c: highlightedSquares)
                            {
                                c.unHighlight();
                            }
                            //Get Move Command
                            lastClicked[0] = temp.getPositionCommand().getIntCoordx();
                            lastClicked[1] = temp.getPositionCommand().getIntCoordy();
                            if(!gameBoard.options.getOnline() || gameBoard.teamTurn == yourTurn) {
                                moveString += temp.getPositionCommand();
                                moveMade = gameBoard.makeMove(moveString);                           //Check to see if a move has been made
                            }
                            if (moveMade)
                            {
                                moveMade(g);
                                ChessSquare movedPiece = squares[pieceMovedPos[0]][pieceMovedPos[1]];
                                String tempString = "";
                                tempString += movedPiece.getActionCommand() + " ";

                                tempString += " moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
                                char tempID = squares[lastClicked[0]][lastClicked[1]].getIDCommand();
                                boolean tempBool = false;
                                if(tempID != 'N')
                                {
                                    tempBool = true;
                                    tempID = squares[pieceMovedPos[0]][pieceMovedPos[1]].getIDCommand();

                                }
                                if(tempID == 'N')
                                {
                                    tempID = squares[pieceMovedPos[0]][pieceMovedPos[1]].getIDCommand();
                                }
                                char capID = tempBool?temp.getIDCommand():'N';
                                GameMove gameMove = new GameMove(moveString, tempID, tempBool, capID);
                                g.getHisotryPanel().ph.addMove(tempString);
                                g.getHisotryPanel().ph.addMove(gameMove);
                            }
                            else
                            {
                                String teamString = "";
                                if (gameBoard.teamTurn == 0) {
                                    teamString = "White";
                                } else {
                                    teamString = "Black";
                                }
                                ChessSquare clickedSquare = squares[lastClicked[0]][lastClicked[1]];
                                if (clickedSquare.getActionCommand().substring(0, 5).equals(teamString)) {
                                    highlightedSquares = displayPossibleMoves(gameBoard.showMoves(clickedSquare.getPositionCommand().toString()));
                                    //Display the possible moves of piece that has been clicked
                                }
                                else {
                                    highlightedSquares = new ArrayList<ChessSquare>();
                                }
                            }
                            updateBoard();                             //Action Command is set to coordinates on board of where you clicked.
                            
                            if(moveMade && !PawnPromote.promotion) {
                            	cpuMakeMove(g);
                            }
                            
                        }
                    }
                });
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
    public static String convertStrings(int x, int y)                                                                     //Converts coordinates to a different form
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
                squares[x][y].setIcon(null);
                squares[x][y].setActionCommand("White  " + x + " " + y);
                squares[x][y].setPositionCommand(new Coordinate(x, y));
                squares[x][y].setIDCommand('N');
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
    				squares[tempx][tempy].setActionCommand(teamName + " " + piece.name);
    				squares[tempx][tempy].setIDCommand(piece.ID);
    				squares[tempx][tempy].setTeam(i);
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
