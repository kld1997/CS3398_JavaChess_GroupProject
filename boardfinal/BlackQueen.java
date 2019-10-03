//Roy Grady, black queen
package chessfinal;
public class BlackQueen implements Piece
{
	
	private long moves;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.blackQueens&coord) != 0) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			long horizontal = (~board.empty - coord * 2) ^ Long.reverse(Long.reverse(~board.empty) - Long.reverse(coord) * 2);
			long vertical = ((~board.empty&Board.colMasks[trail % 8]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.colMasks[trail % 8]) - (2 * Long.reverse(coord)));
			moves = (horizontal&Board.rowMasks[trail / 8] | vertical&Board.colMasks[trail % 8]) & board.notBlack;
			
			long bltr = ((~board.empty&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * Long.reverse(coord)));
	        long tlbr = ((~board.empty&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * Long.reverse(coord)));
			moves |= (bltr&Board.bltrMasks[(trail / 8) + (trail % 8)] | tlbr&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) & board.notBlack;
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
			board.blackQueens^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long queens = board.blackQueens;
		long coord = 0L;
		int q = 0;
		
		while(queens != 0) {
			q = Long.numberOfTrailingZeros(queens);
			coord = 1L<<q;
			queens &= ~coord;
			
			allMoves |= possibleMoves(board, coord);
		}
		
		return allMoves;
	}
}
