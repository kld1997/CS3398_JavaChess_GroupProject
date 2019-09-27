//Roy Grady    Black pawn piece
public class BlackPawn implements Piece
{
	public long possibleMoves(Board board, long coord) {
		
		board.currentState();
		
		long moves = 0L;
		
		if((board.blackPawns&coord) != 0) {
			if((coord&Board.row1) == 0 && ((coord<<8)&board.empty) != 0) {     //push up by 1
				moves |= coord<<8;
			}
			if((coord&Board.row7) != 0 && ((coord<<8)&board.empty) != 0 && ((coord<<16)&board.empty) != 0) {  //push up by 2
				moves |= coord<<16;
			}
			if(((coord<<7)&board.whitePieces&~Board.colA) != 0) { //capture left
				moves |= coord<<7;
			}
			if(((coord<<9)&board.whitePieces&~Board.colH) != 0) { //capture right
				moves |= coord<<9;
			}	
		}	
	
		return moves;
	}
	
	public void movePiece(Board board, long coord1, long coord2) {
		
		long moves = possibleMoves(board, coord1);
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.whitePieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.blackPawns^= change;
		}
		
		board.currentState();
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		
		allMoves |= (board.blackPawns<<8)&board.empty;
		allMoves |= (board.blackPawns<<16)&board.empty&(board.empty<<8)&Board.row5;
		allMoves |= (board.blackPawns<<7)&board.whitePieces&~Board.colH;
		allMoves |= (board.blackPawns<<9)&board.whitePieces&~Board.colA;
		
		return allMoves;
	}
}
