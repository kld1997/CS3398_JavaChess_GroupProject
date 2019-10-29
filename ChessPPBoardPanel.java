import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChessPPBoardPanel extends GenericBoardPanel
{
    ChessGuiPlus parentGui;
    Board useBoard = new ChessPPBoard();
    GenericInfoPanel top;
    HistoryPanel right;
    public int[] lastClicked = new int[2];
    public ChessPPBoardPanel(ChessGuiPlus g)
    {
        parentGui = g;
        useBoard.standardChess();
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
                            moveMade = useBoard.makeMove(teamNum, moveString, true);                           //Check to see if a move has been made
                            if (moveMade)
                            {
                                if(g.getInfoPanel().getType() == 1)
                                {
                                    ((BulletInfoPanel)g.getInfoPanel()).pauseAndSwitch();
                                }
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
                                    highlightedSquares = displayPossibleMoves(useBoard.showMoves(convertStrings(lastClicked[0], lastClicked[1])));     //Display the possible moves of piece that has been clicked
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
    public void updateBoard()
    {
        top = parentGui.getInfoPanel();
        right = parentGui.getHisotryPanel();
        if(PawnPromote.promotion == true) {
            if((useBoard.pawnsBB[0]&Board.row8) != 0) {
                right.pp.showPanel(0, PawnPromote.coord);
                locked = true;
            }
            if((useBoard.pawnsBB[1]&Board.row1) != 0) {
                right.pp.showPanel(1, PawnPromote.coord);
                locked = true;
            }
        }
        if(useBoard.whiteCheck == 1)
        {
            top.setCheck(1);
        }
        else if(useBoard.blackCheck == 1)
        {
            top.setCheck(0);
        }
        if(useBoard.teamWon == 0){ top.setWinner(1); }
        else if(useBoard.teamWon == 1){ top.setWinner(0); }
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
                if(((useBoard.kingBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][0]));         //"wK";
                    squares[x][y].setActionCommand("White King " + x + " " + y);
                }
                else if(((useBoard.queensBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][1]));         //"wq";
                    squares[x][y].setActionCommand("White Queen " + x + " " + y);
                }
                else if(((useBoard.rooksBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][2]));         //"wr";
                    squares[x][y].setActionCommand("White Rook " + x + " " + y);
                }
                else if(((useBoard.knightsBB[0]>>(x*8)+y)&1) == 1)
                {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][3]));         //"wk";
                    squares[x][y].setActionCommand("White Knight " + x + " " + y);
                }
                else if(((useBoard.bishopsBB[0]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][4]));         //"wb";
                    squares[x][y].setActionCommand("White Bishop " + x + " " + y);
                }
                else if(((useBoard.pawnsBB[0]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][5]));         //"wp";
                    squares[x][y].setActionCommand("White Pawn " + x + " " + y);
                }
                else if(((((ChessPPBoard)useBoard).wallsBB[0]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[1][4]));
                    squares[x][y].setActionCommand("White Wall " + x + " " + y);
                }
                //black pieces
                else if(((useBoard.kingBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][0]));           //"bK";
                    squares[x][y].setActionCommand("Black King " + x + " " + y);
                }
                else if(((useBoard.queensBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][1]));           //"bq";
                    squares[x][y].setActionCommand("Black Queen " + x + " " + y);
                }
                else if(((useBoard.rooksBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][2]));           //"br";
                    squares[x][y].setActionCommand("Black Rook " + x + " " + y);
                }
                else if(((useBoard.knightsBB[1]>>(x*8)+y)&1) == 1){
                    squares[x][y].setIcon(new ImageIcon(pieces[0][3]));           //"bk";
                    squares[x][y].setActionCommand("Black Knight " + x + " " + y);
                }
                else if(((useBoard.bishopsBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][4]));           //"bb";
                    squares[x][y].setActionCommand("Black Bishop " + x + " " + y);
                }
                else if(((useBoard.pawnsBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][5]));           //"bp";
                    squares[x][y].setActionCommand("Black Pawn " + x + " " + y);
                }
                else if(((((ChessPPBoard)useBoard).wallsBB[1]>>(x*8)+y)&1) == 1) {
                    squares[x][y].setIcon(new ImageIcon(pieces[0][4]));
                    squares[x][y].setActionCommand("Black Wall " + x + " " + y);
                }
                else
                {
                    squares[x][y].setActionCommand("White  " + x + " " + y);
                }
            }
        }
        repaint();
    }
}
