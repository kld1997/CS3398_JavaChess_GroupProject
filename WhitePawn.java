//Roy Grady, preliminary pawn version
public class WhitePawn implements Piece
{
	public long possibleMoves(Board board, long coord) {
		
		board.currentState();
		
		long moves = 0L;
		
		if((board.whitePawns&coord) != 0) {	
			if((coord&Board.row8) == 0 && ((coord>>8)&board.empty) != 0) {     //push up by 1
				moves |= coord>>8;
			}
			if((coord&Board.row2) != 0 && ((coord>>8)&board.empty) != 0 && ((coord>>16)&board.empty) != 0) {  //push up by 2
				moves |= coord>>16;
			}
			if(((coord>>9)&board.blackPieces&~Board.colA) != 0) { //capture left
				moves |= coord>>9;
			}
			if(((coord>>7)&board.blackPieces&~Board.colH) != 0) { //capture right
				moves |= coord>>7;
			}	
		}	
	
		return moves;
	}
	
	public void movePiece(Board board, long coord1, long coord2) {
		
		long moves = possibleMoves(board, coord1);
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.blackPieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.whitePawns^= change;
		}
		
		board.currentState();
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		
		allMoves |= (board.whitePawns>>8)&board.empty;
		allMoves |= (board.whitePawns>>16)&board.empty&(board.empty>>8)&Board.row4;
		allMoves |= (board.whitePawns>>7)&board.blackPieces&~Board.colA;
		allMoves |= (board.whitePawns>>9)&board.blackPieces&~Board.colH;
		
		return allMoves;
	}
}
