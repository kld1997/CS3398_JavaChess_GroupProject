package Engine;

import Pieces.*;

public class PawnPromote {
	
	public static boolean promotion = false;
	public static long coord = 0L;
	
	public static void pawnPromotion(Board board) {
		
		int p = 0;
		long promote = (board.pieceList.get(0).get('p').piece&Board.row8)|(board.pieceList.get(1).get('p').piece&Board.row1);
		p = Long.numberOfTrailingZeros(promote);
		coord = 1L<<p;

		promotion = true;
	}
	
	public static void promotePawn(int team, long coord, Board board, int pawnID) {
		
		int newP = pawnID;
		System.out.println(team);

		switch(newP) {
		  case 3: //knight
			board.removePromote(coord, team, 3);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).get(board.options.getPromote().charAt(0)).piece |= coord;
		    break;
		  case 4: //bishop
			  board.removePromote(coord, team, 3);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).get(board.options.getPromote().charAt(1)).piece |= coord;
		    break;
		  case 2: //rook
			  board.removePromote(coord, team, 5);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).get(board.options.getPromote().charAt(2)).piece |= coord;
		    break;
		  case 1: //queen
			  board.removePromote(coord, team, 9);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).get(board.options.getPromote().charAt(3)).piece |= coord;
		    break;
		  default:
		    System.out.println("error");
		}

		promotion = false;
		if(!board.cpuTurn) {
			board.currentState();
			board.switchTeamTurn();
		}
	}	
}