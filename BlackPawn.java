//Roy Grady    Black pawn piece without en passant or promotion
public class BlackPawn implements Piece
{
	
	private long moves;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.blackPawns&coord) != 0 && board.blackCheck < 2) {
			if((coord&Board.row1) == 0 && ((coord<<8)&board.empty) != 0) {     //push up by 1
				moves |= coord<<8;
			}
			if((coord&Board.row7) != 0 && ((coord<<8)&board.empty) != 0 && ((coord<<16)&board.empty) != 0) {  //push up by 2
				moves |= coord<<16;
			}
			if(((coord<<7)&board.whitePieces&~Board.colH) != 0) { //capture left
				moves |= coord<<7;
			}
			if(((coord<<9)&board.whitePieces&~Board.colA) != 0) { //capture right
				moves |= coord<<9;
			}	
		}	
		
		if(board.blackCheck == 1) {
			moves &= board.bKThreats;
		}
	
		return moves;
	}
	
	public boolean movePiece(Board board, long coord1, long coord2, boolean checked) {
		
		if(checked == false)
			moves = possibleMoves(board, coord1);
		
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.whitePieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.blackPawns^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		
		allMoves |= (board.blackPawns<<8)&board.empty;
		allMoves |= (board.blackPawns<<16)&board.empty&(board.empty<<8)&Board.row5;
		allMoves |= (board.blackPawns<<7)&board.whitePieces&~Board.colH;
		allMoves |= (board.blackPawns<<9)&board.whitePieces&~Board.colA;
		
		return allMoves;
	}
	
	public long threaten(Board board) {
		
		long threatened = 0L;

		threatened |= (board.blackPawns<<7)&~Board.colA;
		threatened |= (board.blackPawns>>9)&~Board.colH;
		
		return threatened;
	}
	
	public long threatPos(Board board, long pCoord) {
		
		long tPos = 0L;
		long unit = board.blackPawns;
		long coord = 0L;
		int u = 0;
		
		while(unit != 0) {
			u = Long.numberOfTrailingZeros(unit);
			coord = 1L<<u;
			unit &= ~coord;
			
			if((possibleMoves(board, coord)&pCoord) != 0) {
				tPos |= coord;
				board.whiteCheck++;
			}
		}
		
		return tPos;
	}
}
