//Roy Grady    Black pawn piece
public class BlackPawn implements Piece
{
	public long possibleMoves(Board board, int x, int y) {
		
		board.currentState();
		
		long coord = (long)1<<((y*8)+x);
		long moves = 0;
		
		if((board.blackPawns&coord) != 0) {	                                    //push up by 1
			if((coord&board.row1) == 0 && ((coord<<8)&board.empty) != 0) {
				moves |= coord<<8;
			}
			if((coord&board.row7) != 0 && ((coord<<8)&board.empty) != 0 && ((coord<<16)&board.empty) != 0) {
				moves |= coord<<16;
			}
			if((coord&board.colA) == 0 &&((coord<<7)&board.whitePieces) != 0) { //capture left
				moves |= coord<<7;
			}
			if((coord&board.colH) == 0 &&((coord<<9)&board.whitePieces) != 0) { //capture right
				moves |= coord<<9;
			}	
		}	
	
		return moves;
	}
	
	public void movePiece(Board board, int x1, int y1, int x2, int y2) {
		
		long coord1 = 1L<<((y1*8)+x1); 
		long coord2 = 1L<<((y2*8)+x2);
		long moves = possibleMoves(board, x1, y1);
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.whitePieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.blackPawns^= change;
		}
		
		board.currentState();
	}
}
