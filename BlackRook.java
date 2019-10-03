//Roy Grady, black rook
public class BlackRook implements Piece
{
	
	private long moves;
	private boolean threat;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.blackRooks&coord) != 0) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			long horizontal = (~board.empty - coord * 2) ^ Long.reverse(Long.reverse(~board.empty) - Long.reverse(coord) * 2);
			long vertical = ((~board.empty&Board.colMasks[trail % 8]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.colMasks[trail % 8]) - (2 * Long.reverse(coord)));
			moves = (horizontal&Board.rowMasks[trail / 8] | vertical&Board.colMasks[trail % 8]);
		}	
		
		//if(pinned)
		
		if(!threat)
			moves &= board.notBlack;
		
		threat = false;
	
	
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
			board.blackRooks^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long rooks = board.blackRooks;
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
		long rooks = board.blackRooks;
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
}
