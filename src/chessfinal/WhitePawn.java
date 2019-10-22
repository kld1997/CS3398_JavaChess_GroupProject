//Roy Grady, pawn without en passant or promotion
package chessfinal;
public class WhitePawn implements Piece
{
	
	private long moves;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.whitePawns&coord) != 0 && board.whiteCheck < 2) {	
			if((coord&Board.row8) == 0 && ((coord>>8)&board.empty) != 0) {     //push up by 1
				moves |= coord>>8;
			}
			if((coord&Board.row2) != 0 && ((coord>>8)&board.empty) != 0 && ((coord>>16)&board.empty) != 0) {  //push up by 2
				moves |= coord>>16;
			}
			if(((coord>>9)&board.blackPieces&~Board.colH) != 0) { //capture left
				moves |= coord>>9;
			}
			if(((coord>>7)&board.blackPieces&~Board.colA) != 0) { //capture right
				moves |= coord>>7;
			}	
		}	
		
		if(board.whiteCheck == 1) {
			if((coord & board.pinnedW) == 0)
				moves&= board.interfereW;
			else
				moves &= board.wKThreats;
		}
		
		if((coord & board.pinnedW) != 0) {
			moves &= board.pinMove(coord, board.pinnersB, board.whiteKing);
		}
	
		return moves;
	}
	
	public boolean movePiece(Board board, long coord1, long coord2, boolean checked) {
		
		if(checked == false)
			moves = possibleMoves(board, coord1);
		
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.blackPieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.whitePawns^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		
		if(board.whiteCheck < 2) {
			allMoves |= (board.whitePawns>>8)&board.empty;
			allMoves |= (board.whitePawns>>16)&board.empty&(board.empty>>8)&Board.row4;
			allMoves |= (board.whitePawns>>7)&board.blackPieces&~Board.colA;
			allMoves |= (board.whitePawns>>9)&board.blackPieces&~Board.colH;
		}
		
		if(board.whiteCheck == 1)
			allMoves &= board.bKThreats;
		
		return allMoves;
	}
	
	public long threaten(Board board) {
		
		long threatened = 0L;
		
		threatened |= (board.whitePawns>>7)&~Board.colA;
		threatened |= (board.whitePawns>>9)&~Board.colH;
		
		return threatened;
	}
	
	public long threatPos(Board board, long pCoord) {
		
		long tPos = 0L;
		long unit = board.whitePawns;
		long coord = 0L;
		int u = 0;
		
		while(unit != 0) {
			u = Long.numberOfTrailingZeros(unit);
			coord = 1L<<u;
			unit &= ~coord;
			
			if((possibleMoves(board, coord)&pCoord) != 0) {
				tPos |= coord;
				board.blackCheck++;
			}
		}
		
		return tPos;
	}
}
