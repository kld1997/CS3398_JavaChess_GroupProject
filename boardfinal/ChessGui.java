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
        public int teamNum = 0;
        public Board gameBoard = new Board();

        private JPanel mainPanel = new JPanel();
        private JPanel mainBoard = new JPanel();
        private JPanel leftPanel = new JPanel();
        private JPanel bottomPanel = new JPanel();
        private JLabel topLabel = new JLabel();
        public JMenuBar menuBar = new JMenuBar();
        private PieceHistory rightPanel = new PieceHistory();

        ChessSquare[][] squares = new ChessSquare[8][8];
        public static BufferedImage pieces[][] = new BufferedImage[2][6];
        public static BufferedImage colors[][] = new BufferedImage[3][6];

        private int[] lastClicked = new int[2];
        ArrayList<ChessSquare> highlighted = new ArrayList<ChessSquare>();

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                  ChessGui frame = new ChessGui();
                  frame.setVisible(true);
         }
     });
 }

        public ChessGui()   //Basic Setup
        {
            gameBoard.standardChess();

            setUpImages();
            setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.7), (int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.75));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*.15), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*.15));
            setLayout(new BorderLayout());
            mainPanel.setLayout(new BorderLayout());
            topLabel.setText("White Turn");
            topLabel.setFont(new Font("Serif", Font.BOLD, 20));
            topLabel.setHorizontalAlignment(JLabel.CENTER);
            mainBoard.setLayout(new GridLayout(8, 8));
            leftPanel.setLayout(new GridLayout(8,0));
            bottomPanel.setLayout(new GridLayout(0,8));


            boardSetUp();
            sidesSetup();
            buildMenuColors();
            updateBoard(gameBoard);
            mainPanel.add(mainBoard);
            mainPanel.add(leftPanel, BorderLayout.WEST);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
            mainPanel.add(topLabel, BorderLayout.NORTH);
            add(rightPanel, BorderLayout.EAST);
            add(mainPanel, BorderLayout.CENTER);
        }
        //BASIC SETUP HELPER METHODS
        public void buildMenuColors()
        {
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Options");
            menuBar.add(menu);

            JMenu chooseColor = new JMenu("Board Colors");
            JCheckBoxMenuItem color = new JCheckBoxMenuItem("Red", new ImageIcon(colors[0][0]), false);      
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Black", new ImageIcon(colors[0][1]), true);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Dark Blue", new ImageIcon(colors[0][2]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Cyan", new ImageIcon(colors[0][3]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Dark Gray", new ImageIcon(colors[0][4]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Light Gray", new ImageIcon(colors[0][5]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Dark Green", new ImageIcon(colors[1][0]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Lime Green", new ImageIcon(colors[1][1]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Magenta", new ImageIcon(colors[1][2]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Orange", new ImageIcon(colors[1][3]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Pink", new ImageIcon(colors[1][4]), false);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("White", new ImageIcon(colors[1][5]), true);
            chooseColor.add(color);
            color = new JCheckBoxMenuItem("Yellow", new ImageIcon(colors[2][0]), false);
            chooseColor.add(color);
            menu.add(chooseColor);

            setJMenuBar(menuBar);
        }

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
                            boolean moveMade = false;
                            String moveString = "";

                            moveString+=convertStrings(lastClicked[0],lastClicked[1]);      //Converts coordinates to a usable form
                            ChessSquare temp = (ChessSquare) actionEvent.getSource();
                            temp.Highlight();
                            squares[lastClicked[0]][lastClicked[1]].unHighlight();
                            int[] pieceMovedPos = new int[2];
                            pieceMovedPos[0] = lastClicked[0];
                            pieceMovedPos[1] = lastClicked[1];

                            for(int i = 0; i < highlighted.size(); i ++)                //Unhighlight previously highlighted tiles
                            {
                                highlighted.get(i).unHighlight();
                            }
                            for(int i = 6 ; i < temp.getActionCommand().length(); i ++)       //Find coordinates for current button being pressed
                            {
                                int count = 0;
                                if(temp.getActionCommand().substring(i, i+1).equals(" "))
                                {
                                    lastClicked[0] = Integer.parseInt(temp.getActionCommand().substring(i+1,i+2));
                                    lastClicked[1] = Integer.parseInt(temp.getActionCommand().substring(i+3));
                                    break;
                                }
                            }

                            moveString+=convertStrings(lastClicked[0],lastClicked[1]);                                   //Convert current coordinates into usable form

                            moveMade = gameBoard.makeMove(teamNum, moveString, false);                           //Check to see if a move has been made

                            if(moveMade)
                            {
                                teamNum = Math.abs(teamNum - 1);
                                String tempString = "";
                                String actionCommandString = squares[pieceMovedPos[0]][pieceMovedPos[1]].getActionCommand();
                                tempString+= actionCommandString.substring(0,5);
                                int spaceIndex = 0;
                                for(int i = 6; i < actionCommandString.length(); i ++)                                          //Find index of space after white/black
                                {
                                    if(actionCommandString.charAt(i) == ' ')
                                    {
                                        spaceIndex = i;
                                        break;
                                    }
                                }
                                tempString+= " " + actionCommandString.substring(6, spaceIndex) ;

                                tempString+= " has moved from " + moveString.substring(0,2) + " to " + moveString.substring(2);
                                rightPanel.addMove(tempString);
                            }
                            else
                            {
                                highlighted = displayPossibleMoves(gameBoard.showMoves(convertStrings(lastClicked[0],lastClicked[1])));     //Display the possible moves of piece that has been clicked
                            }
                            updateBoard(gameBoard);                             //Action Command is set to coordinates on board of where you clicked.
                        }                                                       //Note: These coordinates don't correspond to the ones shown in the GUI, and instead
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
        private void setUpImages()                                                                                      //Separates consolidated image file into different images
        {
            try
            {
                BufferedImage biPieces = ImageIO.read(new File("img/Pieces.png"));
                BufferedImage biColors = ImageIO.read(new File("colors/colors-1.jpg"));
                for(int i = 0; i < 2; i ++)
                {
                    for(int j = 0; j < 6; j++)
                    {
                        pieces[i][j] = biPieces.getSubimage(j*64, i*64, 64, 64);
                    }
                }

                for(int i = 0; i < 3; i++)
                {
                    for(int j = 0; j < 6; j++)
                    {
                        colors[i][j] = biColors.getSubimage((j*212)+25, (i*204)+23, 20, 20);
                        if(i == 2 && j == 0)
                          break;
                    }
                }
            }
            catch(Exception e){}
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
            if(teamNum == 0)
            {
                topLabel.setText("White Turn");
            }
            else
            {
                topLabel.setText("Gold Turn");
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
                                squares[x][y].setIcon(new ImageIcon(pieces[1][0]));         //"wK";
                                squares[x][y].setActionCommand("White King " + x + " " + y);
                            }
                            else if(((board.whiteQueens>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(pieces[1][1]));         //"wq";
                                squares[x][y].setActionCommand("White Queen " + x + " " + y);
                            }
                            else if(((board.whiteRooks>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(pieces[1][2]));         //"wr";
                                squares[x][y].setActionCommand("White Rook " + x + " " + y);
                            }
                            else if(((board.whiteKnights>>(x*8)+y)&1) == 1)
                            {
                                squares[x][y].setIcon(new ImageIcon(pieces[1][3]));         //"wk";
                                squares[x][y].setActionCommand("White Knight " + x + " " + y);
                            }
                            else if(((board.whiteBishops>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[1][4]));         //"wb";
                                squares[x][y].setActionCommand("White Bishop " + x + " " + y);
                            }
                            else if(((board.whitePawns>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[1][5]));         //"wp";
                                squares[x][y].setActionCommand("White Pawn " + x + " " + y);
                            }

                                //black pieces
                            else if(((board.blackKing>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[0][0]));           //"bK";
                                squares[x][y].setActionCommand("Black King " + x + " " + y);
                            }
                            else if(((board.blackQueens>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[0][1]));           //"bq";
                                squares[x][y].setActionCommand("Black Queen " + x + " " + y);
                            }
                            else if(((board.blackRooks>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[0][2]));           //"br";
                                squares[x][y].setActionCommand("Black Rook " + x + " " + y);
                            }
                            else if(((board.blackKnights>>(x*8)+y)&1) == 1){
                                squares[x][y].setIcon(new ImageIcon(pieces[0][3]));           //"bk";
                                squares[x][y].setActionCommand("Black Knight " + x + " " + y);
                            }
                            else if(((board.blackBishops>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[0][4]));           //"bb";
                                squares[x][y].setActionCommand("Black Bishop " + x + " " + y);
                            }
                            else if(((board.blackPawns>>(x*8)+y)&1) == 1) {
                                squares[x][y].setIcon(new ImageIcon(pieces[0][5]));           //"bp";
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
