package GUI;
import java.awt.event.*;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import Engine.*;

public class ButtonListener extends MouseAdapter {

    private static MainBoardPanel boardP;
    private static ChessSquare lastPressed;
    private static ChessSquare lastReleased;
    private static ChessSquare lastDragged;
    private static String moveString;
    public static boolean moveMade;
    private static int[] pieceMovedPos;
    public static boolean dragEvent = false;


    public static void setBoardPanel(MainBoardPanel m) {
        boardP = m;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      if(!boardP.locked) {
          dragEvent = false;
          moveMade = false;
          moveString = boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1]);

          ChessSquare temp = (ChessSquare) e.getSource();
          boardP.squares[boardP.lastClicked[0]][boardP.lastClicked[1]].unHighlight();
          pieceMovedPos = new int[2];
          pieceMovedPos[0] = boardP.lastClicked[0];      //Saves previously clicked square
          pieceMovedPos[1] = boardP.lastClicked[1];
          for(ChessSquare c: boardP.highlightedSquares)
          {
              c.unHighlight();
          }
          for (int i = 6; i < temp.getActionCommand().length(); i++)       //Find coordinates for current button being pressed
          {
              if (temp.getActionCommand().substring(i, i + 1).equals(" ")) {
                  boardP.lastClicked[0] = Integer.parseInt(temp.getActionCommand().substring(i + 1, i + 2));
                  boardP.lastClicked[1] = Integer.parseInt(temp.getActionCommand().substring(i + 3));
                  break;
              }
          }
          if(!boardP.gameBoard.options.getOnline() || boardP.gameBoard.teamTurn == boardP.yourTurn) {
              moveString += boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1]);                                   //Convert current coordinates into usable form

              moveMade = boardP.gameBoard.makeMove(moveString);                           //Check to see if a move has been made
          }
          if(!moveMade)
          {
              String teamString = "";
              if (boardP.gameBoard.teamTurn == 0) {
                  teamString = "White";
              } else {
                  teamString = "Black";
              }
              if (boardP.squares[boardP.lastClicked[0]][boardP.lastClicked[1]].getActionCommand().substring(0, 5).equals(teamString) && temp.getIcon() != null) {
                  temp.Highlight();
                  boardP.highlightedSquares = boardP.displayPossibleMoves(boardP.gameBoard.showMoves(boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1])));     //Display the possible moves of piece that has been clicked
              }
              else {
                  boardP.highlightedSquares = new ArrayList<ChessSquare>();
                  temp.unHighlight();
              }
          }
      }
    }


    @Override
    public void mouseReleased(MouseEvent e) {
      if(!boardP.locked) {
        if(!dragEvent) {
          if (moveMade)
          {
              boardP.moveMade(boardP.thisGui);

              String tempString = "";
              String actionCommandString = boardP.squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
              tempString += actionCommandString.substring(0, 5);
              int spaceIndex = actionCommandString.substring(6).indexOf(' ') + 6;
              tempString += " " + actionCommandString.substring(6, spaceIndex);
              tempString += " moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
              boardP.thisGui.getHisotryPanel().ph.addMove(tempString);
          }

          boardP.updateBoard();                             //Action Command is set to coordinates on board of where you clicked.

          if(moveMade && !PawnPromote.promotion) {
            boardP.cpuMakeMove(boardP.thisGui);
          }

        } else {
          moveMade = false;
          moveString = boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1]);

          ChessSquare temp = (ChessSquare) e.getSource();
          boardP.squares[boardP.lastClicked[0]][boardP.lastClicked[1]].unHighlight();
          pieceMovedPos = new int[2];
          pieceMovedPos[0] = boardP.lastClicked[0];      //Saves previously clicked square
          pieceMovedPos[1] = boardP.lastClicked[1];
          for(ChessSquare c: boardP.highlightedSquares)
          {
              c.unHighlight();
          }
          for (int i = 6; i < temp.getActionCommand().length(); i++)       //Find coordinates for current button being pressed
          {
              if (temp.getActionCommand().substring(i, i + 1).equals(" ")) {
                  boardP.lastClicked[0] = Integer.parseInt(temp.getActionCommand().substring(i + 1, i + 2));
                  boardP.lastClicked[1] = Integer.parseInt(temp.getActionCommand().substring(i + 3));
                  break;
              }
          }
          if(!boardP.gameBoard.options.getOnline() || boardP.gameBoard.teamTurn == boardP.yourTurn) {
              moveString += boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1]);                                   //Convert current coordinates into usable form

              moveMade = boardP.gameBoard.makeMove(moveString);                           //Check to see if a move has been made
          }
          if(!moveMade)
          {
              String teamString = "";
              if (boardP.gameBoard.teamTurn == 0) {
                  teamString = "White";
              } else {
                  teamString = "Black";
              }
              if (boardP.squares[boardP.lastClicked[0]][boardP.lastClicked[1]].getActionCommand().substring(0, 5).equals(teamString) && temp.getIcon() != null) {
                  temp.Highlight();
                  boardP.highlightedSquares = boardP.displayPossibleMoves(boardP.gameBoard.showMoves(boardP.convertStrings(boardP.lastClicked[0], boardP.lastClicked[1])));     //Display the possible moves of piece that has been clicked
              }
              else {
                  boardP.highlightedSquares = new ArrayList<ChessSquare>();
                  temp.unHighlight();
              }
          }
          if (moveMade)
          {
              boardP.moveMade(boardP.thisGui);

              String tempString = "";
              String actionCommandString = boardP.squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
              tempString += actionCommandString.substring(0, 5);
              int spaceIndex = actionCommandString.substring(6).indexOf(' ') + 6;
              tempString += " " + actionCommandString.substring(6, spaceIndex);
              tempString += " moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
              boardP.thisGui.getHisotryPanel().ph.addMove(tempString);
              boardP.highlightedSquares.clear();
          }

          boardP.updateBoard();                             //Action Command is set to coordinates on board of where you clicked.

          if(moveMade && !PawnPromote.promotion) {
            boardP.cpuMakeMove(boardP.thisGui);
          }
        }
      }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
      if(!boardP.locked) {
        if(!dragEvent) {
          dragEvent = true;
        }
      }
    }

}
