import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

public class BulletChessGui extends ChessGui
{
    private Timer wt, bt;
    private JLabel whiteTime;
    private JLabel blackTime;
    private JPanel topPanel;
    public BulletChessGui()
    {
        super();
    }

    @Override
    public void sidesSetup()
    {
        super.sidesSetup();
        whiteTime = new JLabel();
        blackTime = new JLabel();
        topLabel.setFont(new Font("Serif", Font.BOLD, 20));
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        whiteTime.setFont(new Font("Serif", Font.BOLD, 15));
        whiteTime.setHorizontalAlignment(JLabel.LEFT);
        blackTime.setFont(new Font("Serif", Font.BOLD, 15));
        blackTime.setHorizontalAlignment(JLabel.RIGHT);

        wt = new Timer();
        wt.scheduleAtFixedRate(new ChessTimer(this, whiteTime, 120), 0, 1000);
        bt = new Timer();
        blackTime.setText("2:00");
        super.topPanel.add(whiteTime, BorderLayout.WEST);
        super.topPanel.add(blackTime, BorderLayout.EAST);

    }

    @Override
    public void boardSetUp() {
        int counter = 0;
        for(int x = 0;x < 8; x ++) //Adding tiles to board
        {
            counter = Math.abs(counter - 1);
            for (int y = 0; y < 8; y++) {
                ChessSquare newSquare = new ChessSquare(counter, x, y);
                newSquare.addActionListener(new ActionListener() {                                                                       //When tiles are clicked
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (!locked) {
                            boolean moveMade = false;
                            String moveString = "";

                            moveString += convertStrings(lastClicked[0], lastClicked[1]);      //Converts coordinates to a usable form
                            ChessSquare temp = (ChessSquare) actionEvent.getSource();
                            temp.Highlight();
                            squares[lastClicked[0]][lastClicked[1]].unHighlight();
                            int[] pieceMovedPos = new int[2];
                            pieceMovedPos[0] = lastClicked[0];
                            pieceMovedPos[1] = lastClicked[1];

                            for (int i = 0; i < highlighted.size(); i++)                //Unhighlight previously highlighted tiles
                            {
                                highlighted.get(i).unHighlight();
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

                            moveMade = gameBoard.makeMove(teamNum, moveString, true);                           //Check to see if a move has been made
                            if (moveMade) {
                                int tempSec, tempMin;
                                if (teamNum == 0) {
                                    tempMin = Integer.parseInt(blackTime.getText().substring(0, blackTime.getText().indexOf(':')));
                                    tempSec = Integer.parseInt(blackTime.getText().substring(blackTime.getText().indexOf(':') + 1)) + tempMin * 60;
                                    bt.scheduleAtFixedRate(new ChessTimer(thisGui, blackTime, tempSec), 0, 1000);
                                    wt.cancel();
                                    wt = new Timer();
                                } else {
                                    tempMin = Integer.parseInt(whiteTime.getText().substring(0, whiteTime.getText().indexOf(':')));
                                    tempSec = Integer.parseInt(whiteTime.getText().substring(whiteTime.getText().indexOf(':') + 1)) + tempMin * 60;
                                    wt.scheduleAtFixedRate(new ChessTimer(thisGui, whiteTime, tempSec), 0, 1000);
                                    bt.cancel();
                                    bt = new Timer();
                                }
                                teamNum = Math.abs(teamNum - 1);
                                String tempString = "";
                                String actionCommandString = squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
                                tempString += actionCommandString.substring(0, 5);
                                int spaceIndex = actionCommandString.substring(6).indexOf(' ') + 6;
                                tempString += " " + actionCommandString.substring(6, spaceIndex);
                                tempString += " has moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
                                BulletChessGui.super.historyPanel.addMove(tempString);
                            } else {
                                String teamString = "";
                                if (teamNum == 0) {
                                    teamString = "White";
                                } else {
                                    teamString = "Black";
                                }
                                if (squares[lastClicked[0]][lastClicked[1]].getActionCommand().substring(0, 5).equals(teamString)) {
                                    highlighted = BulletChessGui.super.displayPossibleMoves(gameBoard.showMoves(convertStrings(lastClicked[0], lastClicked[1])));     //Display the possible moves of piece that has been clicked
                                } else {
                                    highlighted = new ArrayList<ChessSquare>();
                                }
                            }
                            updateBoard(gameBoard);                             //Action Command is set to coordinates on board of where you clicked.
                        }
                    }

                    //Note: These coordinates don't correspond to the ones shown in the GUI, and instead
                });                                                         //      show their indexes in the array squares
                mainBoard.add(newSquare);
                counter = Math.abs(counter - 1);
                squares[x][y] = newSquare;
            }
        }
    }
}
