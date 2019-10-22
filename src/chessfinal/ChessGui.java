//Kieran Hsieh
package chessfinal;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.*;
import javax.swing.border.Border;

public class ChessGui extends JFrame
{
        public int pawnID = 0;
        public int teamNum = 0;
        public boolean locked = false;
        public Board gameBoard = new Board();
        public int promoButtonClicked = 0;
        public ChessGui thisGui = this;

        private JPanel mainPanel = new JPanel();
        private JPanel mainBoard = new JPanel();
        private JPanel leftPanel = new JPanel();
        private JPanel bottomPanel = new JPanel();
        private JLabel topLabel = new JLabel();
        private PieceHistory historyPanel = new PieceHistory();
        private JPanel rightPanel = new JPanel();
        private JPanel rightPawnChoice = new JPanel();

        ChessSquare[][] squares = new ChessSquare[8][8];
        public BufferedImage playerOneModels[] = new BufferedImage[6];
        public BufferedImage playerTwoModels[] = new BufferedImage[6];

        private int[] lastClicked = new int[2];
        ArrayList<ChessSquare> highlighted = new ArrayList<ChessSquare>();
        public ChessGui()   //Basic Setup
        {
            gameBoard.standardChess();

            setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.7), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*.15), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.15));
            setLayout(new BorderLayout());

            MenuBuilder menu = new MenuBuilder();
            setJMenuBar(menu);


            topLabel.setText("White Turn");
            topLabel.setFont(new Font("Serif", Font.BOLD, 20));
            topLabel.setHorizontalAlignment(JLabel.CENTER);

            mainPanel.setLayout(new BorderLayout());
            mainBoard.setLayout(new GridLayout(8, 8));
            leftPanel.setLayout(new GridLayout(8,0));
            rightPanel.setLayout(new GridLayout(2, 1));
            bottomPanel.setLayout(new GridLayout(0,8));
            rightPawnChoice.setLayout(new GridLayout(2, 2));

            boardSetUp();
            sidesSetup();
            updateBoard(gameBoard);

            mainPanel.add(mainBoard);
            mainPanel.add(leftPanel, BorderLayout.WEST);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
            mainPanel.add(topLabel, BorderLayout.NORTH);
            add(rightPanel, BorderLayout.EAST);
            add(mainPanel, BorderLayout.CENTER);
            setVisible(true);

            IconChangeListener boardListener = new IconChangeListener(this);
            menu.setListener(boardListener);
        }
        //BASIC SETUP HELPER METHODS
        private void sidesSetup()
        {
            //Left Side number labels
            for(int i = 8; i > 0; i--)
            {
                JLabel newLabel = new JLabel(i + "", SwingConstants.CENTER);
                newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 25));

                leftPanel.add(newLabel);
            }
            //Bottom side letters
            for(int i = 65; i < 73; i ++) //Char values for A-H
            {
                String letter = Character.toString((char)i);
                JLabel newLabel = new JLabel(letter, SwingConstants.CENTER);
                newLabel.setFont(new Font(newLabel.getFont().getName(), Font.BOLD, 20));

                bottomPanel.add(newLabel);
            }
            //Right side Panels
            //Dimension d = new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.155), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.375));
            rightPanel.add(historyPanel);
            rightPanel.add(rightPawnChoice);
        }
        public void showPawnPromotion(int team, long coord)
        {
            locked = true;
            int teamIcon = Math.abs(team-1);
            BufferedImage[] pieces = new BufferedImage[6];
            if(teamIcon == 0)
              pieces = playerOneModels;
            else
              pieces = playerTwoModels;
            for(int i = 1; i < 5; i ++)
            {
                JButton choiceButton = new JButton();
                choiceButton.setIcon(new ImageIcon(pieces[i]));
                choiceButton.setActionCommand(i + "");
                choiceButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        locked = false;
                        JButton temp = (JButton)  e.getSource();
                        promoButtonClicked = Integer.parseInt(temp.getActionCommand());
                        pawnID = promoButtonClicked;
                        pawnPromote.promotePawn(team, coord, gameBoard, pawnID);

                        temp.getParent().removeAll();
                        rightPawnChoice.repaint();
                        updateBoard(gameBoard);
                    }
                });
                rightPawnChoice.add(choiceButton);
            }
        }
        private void boardSetUp()
        {
            int counter = 0;
            for(int x = 0;x < 8; x ++) //Adding tiles to board
            {
                counter = Math.abs(counter-1);
                for(int y = 0; y < 8; y ++)
                {
                    ChessSquare newSquare = new ChessSquare(counter, x, y);
                    newSquare.addActionListener(new ActionListener()
                    {                                                                       //When tiles are clicked
                        @Override
                        public void actionPerformed(ActionEvent actionEvent)
                        {
                            if(!locked)
                            {
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
                                    int count = 0;
                                    if (temp.getActionCommand().substring(i, i + 1).equals(" ")) {
                                        lastClicked[0] = Integer.parseInt(temp.getActionCommand().substring(i + 1, i + 2));
                                        lastClicked[1] = Integer.parseInt(temp.getActionCommand().substring(i + 3));
                                        break;
                                    }
                                }

                                moveString += convertStrings(lastClicked[0], lastClicked[1]);                                   //Convert current coordinates into usable form

                                moveMade = gameBoard.makeMove(teamNum, moveString, true);                           //Check to see if a move has been made
                                if (moveMade) {
                                    teamNum = Math.abs(teamNum - 1);
                                    menu.teamChange();
                                    String tempString = "";
                                    String actionCommandString = squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
                                    tempString += actionCommandString.substring(0, 5);
                                    int spaceIndex = actionCommandString.substring(6).indexOf(' ') + 6;
                                    tempString += " " + actionCommandString.substring(6, spaceIndex);
                                    tempString += " has moved from " + moveString.substring(0, 2) + " to " + moveString.substring(2);
                                    historyPanel.addMove(tempString);
                                } else {
                                    String teamString = "";
                                    if (teamNum == 0) {
                                        teamString = "White";
                                    } else {
                                        teamString = "Black";
                                    }
                                    if (squares[lastClicked[0]][lastClicked[1]].getActionCommand().substring(0, 5).equals(teamString)) {
                                        highlighted = displayPossibleMoves(gameBoard.showMoves(convertStrings(lastClicked[0], lastClicked[1])));     //Display the possible moves of piece that has been clicked
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
        private ArrayList<ChessSquare> displayPossibleMoves(long board)                 //Highlights possible moves with board method
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
        //END BASE SETUP

        private String convertStrings(int x, int y)                                                                     //Converts coordinates to a different form
        {
            String ret = "";
            ret+= (char)(y + 97);
            ret+= 8-x;
            return ret;
        }
        //Updates images on the mainBoard JPanel
        public void updateBoard(Board board)
        {
            if(pawnPromote.promotion == true) {
                showPawnPromotion(0, pawnPromote.coord);
            }
            if(teamNum == 0)
            {
                topLabel.setText("White Turn");
            }
            else
            {
                topLabel.setText("Black Turn");
            }
            if(gameBoard.whiteCheck == 1 || gameBoard.blackCheck == 1)
            {
                topLabel.setText("Check! " + topLabel.getText());
            }
            if(gameBoard.teamWon == 0)
            {
                topLabel.setText("White team wins!");
            }
            if(gameBoard.teamWon == 1)
            {
                topLabel.setText("Black team wins!");
            }
            //Remove icons

            for(int x = 0; x < 8; x ++)
            {
                for(int y = 0; y < 8; y++)
                {
                    squares[x][y].setIcon(null);
                }
            }


            //Setting up Icons in shape of new board using Roy's code for displaying the array but setting icons instead.
            for(int x = 0; x < 8; x ++)
            {
                for(int y = 0; y < 8; y ++)
                {
                            //white pieces
                            if(((board.whiteKing>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[0]));         //"wK";
                                squares[x][y].setActionCommand("White King " + x + " " + y);
                            }
                            else if(((board.whiteQueens>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[1]));         //"wq";
                                squares[x][y].setActionCommand("White Queen " + x + " " + y);
                            }
                            else if(((board.whiteRooks>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[2]));         //"wr";
                                squares[x][y].setActionCommand("White Rook " + x + " " + y);
                            }
                            else if(((board.whiteKnights>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[3]));         //"wk";
                                squares[x][y].setActionCommand("White Knight " + x + " " + y);
                            }
                            else if(((board.whiteBishops>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[4]));         //"wb";
                                squares[x][y].setActionCommand("White Bishop " + x + " " + y);
                            }
                            else if(((board.whitePawns>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerTwoModels[5]));         //"wp";
                                squares[x][y].setActionCommand("White Pawn " + x + " " + y);
                            }

                                //black pieces
                            else if(((board.blackKing>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[0]));           //"bK";
                                squares[x][y].setActionCommand("Black King " + x + " " + y);
                            }
                            else if(((board.blackQueens>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[1]));           //"bq";
                                squares[x][y].setActionCommand("Black Queen " + x + " " + y);
                            }
                            else if(((board.blackRooks>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[2]));           //"br";
                                squares[x][y].setActionCommand("Black Rook " + x + " " + y);
                            }
                            else if(((board.blackKnights>>(x*8)+y)&1) == 1){
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[3]));           //"bk";
                                squares[x][y].setActionCommand("Black Knight " + x + " " + y);
                            }
                            else if(((board.blackBishops>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[4]));           //"bb";
                                squares[x][y].setActionCommand("Black Bishop " + x + " " + y);
                            }
                            else if(((board.blackPawns>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(playerOneModels[5]));           //"bp";
                                squares[x][y].setActionCommand("Black Pawn " + x + " " + y);
                            }
                            else
                            {
                                squares[x][y].setActionCommand("White  " + x + " " + y);
                            }
                }
            }
            mainBoard.revalidate();
            mainBoard.repaint();
        }
}
