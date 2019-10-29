import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Vector;

//Main Grid of
public class MainBoardPanel extends JPanel
{
    Board gameBoard = new Board();
    InfoPanel top;
    HistoryPanel right;
    boolean locked = false;
    int pawnID = 0;
    private ChessSquare[][] squares = new ChessSquare[8][8];
    private int[] lastClicked = new int[2];
    private ArrayList<ChessSquare> highlightedSquares = new ArrayList<ChessSquare>();
    public static BufferedImage pieces[][] = new BufferedImage[2][6];

    ChessGui thisGui;
    public MainBoardPanel(ChessGui g)
    {
        thisGui = g;
        gameBoard.standardChess();
        setUpImages();
        setLayout(new GridLayout(8,8));
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
                            String moveString = convertStrings(lastClicked[0], lastClicked[1]);

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
                            for (int i = 6; i < temp.getActionCommand().length(); i++)       //Find coordinates for current button being pressed
                            {
                                if (temp.getActionCommand().substring(i, i + 1).equals(" ")) {
                                    lastClicked[0] = Integer.parseInt(temp.getActionCommand().substring(i + 1, i + 2));
                                    lastClicked[1] = Integer.parseInt(temp.getActionCommand().substring(i + 3));
                                    break;
                                }
                            }
                            moveString += convertStrings(lastClicked[0], lastClicked[1]);                                   //Convert current coordinates into usable form
                            int teamNum = g.getInfoPanel().getCurrTeam();
                            moveMade = gameBoard.makeMove(teamNum, moveString, true);                           //Check to see if a move has been made
                            if (moveMade)
                            {
                                g.getInfoPanel().switchTeam();

                                String tempString = "";
                                String actionCommandString = squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
                                tempString += actionCommandString.substring(0, 5);
                                int spaceIndex = actionCommandString.substring(6).indexOf(' ') + 6;
                                tempString += " " + actionCommandString.substring(6, spaceIndex);
                                tempString += " has moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
                                g.getHisotryPanel().ph.addMove(tempString);
                            }
                            else
                            {
                                String teamString = "";
                                if (teamNum == 0) {
                                    teamString = "White";
                                } else {
                                    teamString = "Black";
                                }
                                if (squares[lastClicked[0]][lastClicked[1]].getActionCommand().substring(0, 5).equals(teamString)) {
                                    highlightedSquares = displayPossibleMoves(gameBoard.showMoves(convertStrings(lastClicked[0], lastClicked[1])));     //Display the possible moves of piece that has been clicked
                                }
                                else {
                                    highlightedSquares = new ArrayList<ChessSquare>();
                                }
                            }
                            updateBoard();                             //Action Command is set to coordinates on board of where you clicked.
                        }
                    }
                });
                add(squares[i][j]);
                counter = Math.abs(counter-1);
            }
        }
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
    private void setUpImages()                                                                                      //Separates consolidated image file into different images
    {
        try
        {
            BufferedImage biPieces = ImageIO.read(new File("img/Pieces.png"));
            for(int i = 0; i < 2; i ++)
            {
                for(int j = 0; j < 6; j++)
                {
                    pieces[i][j] = biPieces.getSubimage(j*64, i*64, 64, 64);
                }
            }
        }
        catch(Exception e){}
    }
    public void updateBoard()
    {
        top = thisGui.getInfoPanel();
        right = thisGui.getHisotryPanel();
        if(PawnPromote.promotion == true) {
            if((gameBoard.pawnsBB[0]&Board.row8) != 0) {
                right.pp.showPanel(0, PawnPromote.coord);
                locked = true;
            }
            if((gameBoard.pawnsBB[1]&Board.row1) != 0) {
                right.pp.showPanel(1, PawnPromote.coord);
                locked = true;
            }
        }
        if(gameBoard.whiteCheck == 1)
        {
            top.setCheck(1);
        }
        else if(gameBoard.blackCheck == 1)
        {
            top.setCheck(0);
        }
        for(int x = 0; x < 8; x ++)
        {
            for(int y = 0; y < 8; y++)
            {
                squares[x][y].setIcon(null);
            }
        }
        for(int x = 0; x < 8; x ++)
        {
            for(int y = 0; y < 8; y ++)
            {
                //white pieces
                if(((gameBoard.kingBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][0]));         //"wK";
                    squares[x][y].setActionCommand("White King " + x + " " + y);
                }
                else if(((gameBoard.queensBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][1]));         //"wq";
                    squares[x][y].setActionCommand("White Queen " + x + " " + y);
                }
                else if(((gameBoard.rooksBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][2]));         //"wr";
                    squares[x][y].setActionCommand("White Rook " + x + " " + y);
                }
                else if(((gameBoard.knightsBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][3]));         //"wk";
                    squares[x][y].setActionCommand("White Knight " + x + " " + y);
                }
                else if(((gameBoard.bishopsBB[0]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][4]));         //"wb";
                    squares[x][y].setActionCommand("White Bishop " + x + " " + y);
                }
                else if(((gameBoard.pawnsBB[0]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][5]));         //"wp";
                    squares[x][y].setActionCommand("White Pawn " + x + " " + y);
                }
                //black pieces
                else if(((gameBoard.kingBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][0]));           //"bK";
                    squares[x][y].setActionCommand("Black King " + x + " " + y);
                }
                else if(((gameBoard.queensBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][1]));           //"bq";
                    squares[x][y].setActionCommand("Black Queen " + x + " " + y);
                }
                else if(((gameBoard.rooksBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][2]));           //"br";
                    squares[x][y].setActionCommand("Black Rook " + x + " " + y);
                }
                else if(((gameBoard.knightsBB[1]>>(x*8)+y)&1) == 1){
                    squares[x][y].setIcon(new ImageIcon(pieces[0][3]));           //"bk";
                    squares[x][y].setActionCommand("Black Knight " + x + " " + y);
                }
                else if(((gameBoard.bishopsBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][4]));           //"bb";
                    squares[x][y].setActionCommand("Black Bishop " + x + " " + y);
                }
                else if(((gameBoard.pawnsBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][5]));           //"bp";
                    squares[x][y].setActionCommand("Black Pawn " + x + " " + y);
                }
                else
                {
                    squares[x][y].setActionCommand("White  " + x + " " + y);
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
