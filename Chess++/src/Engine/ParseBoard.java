package Engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieces.*;
import Pieces.PieceTypes.*;

public class ParseBoard {
	
	static public Piece identify(char id, int team) {
		
		switch(id) {
		case 'p': return new Pawn(team);
		case 'k': return new Knight(team);
		case 'b': return new Bishop(team);
		case 'r': return new Rook(team);
		case 'q': return new Queen(team);
		case 'K': return new King(team);
		case 'W': return new Wall(team);
		case 'a': return new Archer(team);
		case 'j': return new Jester(team);
		case 'P': return new Prince(team);
		default: return null;
		}	
		
	}
	
	static public String[][] bitboardToArray(List<Map<Character, Piece>> pieceList) {
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
    		
    		for(Piece piece : pieceList.get(i).values()) {
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
	
	static public void displayBitboards(List<Map<Character, Piece>> pieceList) {
		String[][] b = bitboardToArray(pieceList);
		for(String[] r : b) {
			for(String c : r)
				System.out.print("[" + c + "] ");
			System.out.println();
		}
		System.out.println();
	}
	
	static public List<Map<Character, Piece>> pieceInit(String[][] pieceArr, String[] promoteTo) {
		
		Map<Character, Piece> pieceMap1 = new HashMap<Character, Piece>();
		Map<Character, Piece> pieceMap2 = new HashMap<Character, Piece>();
		List<Map<Character, Piece>> pieceList = new ArrayList<Map<Character, Piece>>();
		Piece newPiece;
		String binary;
		
		for(int i = 0; i < pieceArr.length; i++) {
			for(int j = 0; j < pieceArr[i].length; j++) {
				binary = "0000000000000000000000000000000000000000000000000000000000000000";
	            binary = binary.substring((i*8)+(j%8)+1) + "1" + binary.substring(0, (i*8)+(j%8));
	            
				if(pieceArr[i][j].charAt(0) == 'w') {
					newPiece = identify(pieceArr[i][j].charAt(1), 0);
					if(pieceMap1.containsKey(newPiece.ID)) {
						pieceMap1.get(newPiece.ID).piece |= convertStringToBitboard(binary);
					}
					else {
						newPiece.piece = convertStringToBitboard(binary);
						pieceMap1.put(newPiece.ID, newPiece);
					}
				}
				else if(pieceArr[i][j].charAt(0) == 'b') {
					newPiece = identify(pieceArr[i][j].charAt(1), 1);
					if(pieceMap2.containsKey(newPiece.ID)) {
						pieceMap2.get(newPiece.ID).piece |= convertStringToBitboard(binary);
					}
					else {
						newPiece.piece = convertStringToBitboard(binary);
						pieceMap2.put(newPiece.ID, newPiece);
					}
				}
			}
		}
		
		pieceList.add(pieceMap1);
		pieceList.add(pieceMap2);
		
		for(int i = 0; i < Board.teamNum; i++) {
			
			if(hasPromoteable(pieceList.get(i).values())) {
				pieceList.get(i).putAll(addMissingPromote(pieceList.get(i), promoteTo[i], i));
			}
		}
		
		return pieceList;
		
	}
	
	static public long convertStringToBitboard(String Binary) {                    //converts the string of binary numbers into actual binary fit for bitboards
		
        if (Binary.charAt(0)=='0') {                                       		   //not negative
            return Long.parseLong(Binary, 2);
        } else {
            return Long.parseLong("1"+Binary.substring(2), 2)*2;
        }
	}
	
    static public boolean hasPromoteable(Collection<Piece> pieces) {
        	
        	for(Piece piece : pieces) {
        		if(piece instanceof Promoteable)
        			return true;
        	}
        	return false;
    }
    
    static public Map<Character, Piece> addMissingPromote(Map<Character, Piece> pieces, String promote, int team) {
    	
    	Map<Character, Piece> newPieces = new HashMap<Character, Piece>();
    	String promoteList = promote;
    	
    	for(Piece piece : pieces.values()) {
    		if(promoteList.contains(piece.ID + ""))
    			promoteList = promoteList.replace(piece.ID + "", "");
    	}
    	
    	for(int i = 0; i < promoteList.length(); i++) {
    		newPieces.put(promoteList.charAt(i), identify(promoteList.charAt(i), team));
    	}
    	
    	return newPieces;
    }
}