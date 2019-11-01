package Engine;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import Pieces.*;

public class ParseBoard {
	
	static public Piece identify(String id) {
		
		int team = 0;

		if(id.charAt(0) == 'w')
			team = 0;
		if(id.charAt(0) == 'b')
			team = 1;
		
		switch(id.charAt(1)) {
		case 'p': return new Pawn(team);
		case 'k': return new Knight(team);
		case 'b': return new Bishop(team);
		case 'r': return new Rook(team);
		case 'q': return new Queen(team);
		case 'K': return new King(team);
		case 'W': return new Wall(team);
		default: return new Wall(-1);
		}
		
	}
	
	static public String[][] bitboardToArray(List<List<Piece>> pieceList) {
		String chessBoard[][] = {
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "}};
		
		char teamID;
        long temp;
        int tempx;
        int tempy;
        String fullID;
		
		for(int i = 0; i < Board.teamNum; i++) {
    		if(i == 0)
    			teamID = 'w';
    		else
    			teamID = 'b';
    		
    		for(Piece piece : pieceList.get(i)) {
    			temp = piece.piece;
    			fullID = "" + teamID + piece.ID;
    			
    			while(temp != 0) {
    				tempx = Long.numberOfTrailingZeros(temp)/8;
    				tempy = Long.numberOfTrailingZeros(temp)%8;

    				chessBoard[tempx][tempy] = fullID;
    				
    				temp &= temp - 1;
    			}
    		}
        }
		
		return chessBoard;
	}
	
	static public List<List<Piece>> pieceInit(String[][] pieceArr) {
		
		ArrayList<String> types1 = new ArrayList<String>();
		ArrayList<String> types2 = new ArrayList<String>();
		List<List<Piece>> pieceList = new ArrayList<List<Piece>>();
		List<Piece> list1 = new ArrayList<Piece>();
		List<Piece> list2 = new ArrayList<Piece>();
		Piece newPiece;
		String Binary;
		int pos;
		
		for(int i = 0; i < pieceArr.length; i++) {
			for(int j = 0; j < pieceArr[i].length; j++) {
				Binary="0000000000000000000000000000000000000000000000000000000000000000";
	            Binary=Binary.substring((i*8)+(j%8)+1)+"1"+Binary.substring(0, (i*8)+(j%8));
	            
				if(!types1.contains(pieceArr[i][j]) && !types2.contains(pieceArr[i][j]) && pieceArr[i][j] != "  ") {
					newPiece = identify(pieceArr[i][j]);
					if(newPiece.team != -1) {
						newPiece.piece = convertStringToBitboard(Binary);
						if(newPiece.team == 0) {
							list1.add(newPiece);
							types1.add(pieceArr[i][j]);
						}
						else {
							list2.add(newPiece);
							types2.add(pieceArr[i][j]);
						}
					}				
				}
				else {
					if(pieceArr[i][j].charAt(0) == 'w') {
						pos = types1.indexOf(pieceArr[i][j]);
						list1.get(pos).piece += convertStringToBitboard(Binary);
					}
					else if(pieceArr[i][j].charAt(0) == 'b') {
						pos = types2.indexOf(pieceArr[i][j]);
						list2.get(pos).piece += convertStringToBitboard(Binary);
					}
				}
			}
		}
		
		pieceList.add(list1);
		pieceList.add(list2);
		
		return pieceList;
	}
	
	static public long convertStringToBitboard(String Binary) {                    //converts the string of binary numbers into actual binary fit for bitboards
		
        if (Binary.charAt(0)=='0') {                                       		   //not negative
            return Long.parseLong(Binary, 2);
        } else {
            return Long.parseLong("1"+Binary.substring(2), 2)*2;
        }
    }
}