//Roy Grady, rook
package chessfinal;
public class WhiteRook implements Piece
{
	
	private long moves;
	private boolean threat = false;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		long occ = ~board.empty;

		if((board.whiteRooks&coord) != 0 && board.whiteCheck < 2) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			long horizontal = (occ - coord * 2) ^ Long.reverse(Long.reverse(occ) - Long.reverse(coord) * 2);
			long vertical = ((occ&Board.colMasks[trail % 8]) - (2 * coord)) ^ Long.reverse(Long.reverse(occ&Board.colMasks[trail % 8]) - (2 * Long.reverse(coord)));
			moves = (horizontal&Board.rowMasks[trail / 8] | vertical&Board.colMasks[trail % 8]);
		}	
	
		if(!threat)
			moves &= board.notWhite;
		
		threat = false;
	
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
			board.whiteRooks^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long rooks = board.whiteRooks;
		long coord = 0L;
		int r = 0;
		
		while(rooks != 0) {
			r = Long.numberOfTrailingZeros(rooks);
			coord = 1L<<r;
			rooks &= ~coord;
			
			allMoves |= possibleMoves(board, coord);
		}
		
		return allMoves;
	}
	
	public long threaten(Board board) {
		
		long threatened = 0L;
		long rooks = board.whiteRooks;
		long coord = 0L;
		int r = 0;
		
		while(rooks != 0) {
			threat = true;
			r = Long.numberOfTrailingZeros(rooks);
			coord = 1L<<r;
			rooks &= ~coord;
			
			threatened |= possibleMoves(board, coord);
		}
		
		return threatened;
	}
	
	public long threatPos(Board board, long pCoord) {
		
		long tPos = 0L;
		long unit = board.whiteRooks;
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
