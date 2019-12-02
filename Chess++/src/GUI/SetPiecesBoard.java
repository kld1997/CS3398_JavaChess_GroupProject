package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Engine.PawnPromote;
import Pieces.*;

public class SetPiecesBoard extends JPanel{
	
	public ChessSquare[][] squares = new ChessSquare[8][8];
	public char selectedPiece = '0';
	public String[][] startBoard = new String[8][8];
	List<Map<Character, Piece>> allPieces;
	int teamSet;
	int teamTurn;
	int[] lastClicked = new int[2];

	public SetPiecesBoard() {
		
		setLayout(new GridLayout(8,8));
		pieceSetup();
		boardSet();
		for(int x = 0; x < 8; x ++)
        {
            for(int y = 0; y < 8; y++) {
            	squares[x][y].setPositionCommand(new Coordinate(x, y));
                squares[x][y].setIDCommand('N');
                startBoard[x][y] = "  ";
            }
        }
		startBoard[7][4] = "wK";
		startBoard[0][4] = "bK";
		update();
	}
	
	public void pieceSetup() {
		allPieces = new ArrayList<Map<Character, Piece>>();
		for(int i = 0; i < 2; i++) {
		Map<Character, Piece> teamPieces = new HashMap<Character, Piece>();
	        teamPieces.put('W', new Wall(i));
	        teamPieces.put('p', new Pawn(i));
	        teamPieces.put('k', new Knight(i));
	        teamPieces.put('b', new Bishop(i));
	        teamPieces.put('a', new Archer(i));
	        teamPieces.put('r', new Rook(i));
	        teamPieces.put('j', new Jester(i));
	        teamPieces.put('q', new Queen(i));
	        teamPieces.put('P', new Prince(i));
	        teamPieces.put('K', new King(i));
	        allPieces.add(teamPieces);
		}
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
                
                newSquare.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                    	ChessSquare temp = (ChessSquare) actionEvent.getSource();
                    	int xcoord = temp.getPositionCommand().getIntCoordx();
                    	int ycoord = temp.getPositionCommand().getIntCoordy();
                    	if(lastClicked[0] >= 0)
                    		squares[lastClicked[0]][lastClicked[1]].unHighlight();
                    	char team;
                    	if(lastClicked[0] >= 0 && startBoard[lastClicked[0]][lastClicked[1]] != "  ") {
                    		startBoard[xcoord][ycoord] = startBoard[lastClicked[0]][lastClicked[1]];
                    		startBoard[lastClicked[0]][lastClicked[1]] = "  ";
                    		
                    		lastClicked[0] = -1;
                        	lastClicked[1] = -1;
                        	selectedPiece = '0';
                    	}
                    	else if(selectedPiece != '0' && startBoard[xcoord][ycoord] == "  ") {
                        	if(teamSet == 0)
                        		team = 'w';
                        	else
                        		team = 'b';
                        	startBoard[xcoord][ycoord] = "" + team + selectedPiece;
                        	//selectedPiece = '0';
                        	lastClicked[0] = -1;
                        	lastClicked[1] = -1;
                        }
                    	else {
                    		lastClicked[0] = xcoord;
                    		lastClicked[1] = ycoord;
                    		temp.Highlight();
                    	}
                    	update();
                    }
                });
                newSquare.addMouseListener(new MouseListener()
        		{

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if(SwingUtilities.isRightMouseButton(e)) {
							ChessSquare temp = (ChessSquare) e.getSource();
	                    	int xcoord = temp.getPositionCommand().getIntCoordx();
	                    	int ycoord = temp.getPositionCommand().getIntCoordy();
	                    	
	                    	if(startBoard[xcoord][ycoord] != "  ") {
	                    		if(startBoard[xcoord][ycoord].charAt(1) == 'K')
	                    			JOptionPane.showMessageDialog(null, "You can't delete a King!", "Error", JOptionPane.INFORMATION_MESSAGE);
	                    		else
	                    			startBoard[xcoord][ycoord] = "  ";
	                    		update();
	                    	}
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}
                	
        		});
                add(squares[i][j]);
                counter = Math.abs(counter-1);
            }
        }
    }
	
	public void update() {
		
		int team;
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(startBoard[x][y] != "  ") {
					if(startBoard[x][y].charAt(0) == 'w')
						team = 0;
					else
						team = 1;
					squares[x][y].setIcon(new ImageIcon(allPieces.get(team).get(startBoard[x][y].charAt(1)).image));
					squares[x][y].setIDCommand(startBoard[x][y].charAt(1));
				}
				else {
					squares[x][y].setIcon(null);
					squares[x][y].setIDCommand(' ');
				}
			}
		}
		
		repaint();
	}
}
