//Roy Grady, black bishop
public class BlackBishop implements Piece
{
	
	private long moves;
	private boolean threat = false;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.blackBishops&coord) != 0 && board.blackCheck < 2) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			long bltr = ((~board.empty&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * Long.reverse(coord)));
	        long tlbr = ((~board.empty&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(~board.empty&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * Long.reverse(coord)));
			moves = (bltr&Board.bltrMasks[(trail / 8) + (trail % 8)] | tlbr&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]);
		}	
	
		if(!threat)
			moves &= board.notBlack;
		
		threat = false;
		
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
			board.blackBishops^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long bishops = board.blackBishops;
		long coord = 0L;
		int b = 0;
		
		while(bishops != 0) {
			b = Long.numberOfTrailingZeros(bishops);
			coord = 1L<<b;
			bishops &= ~coord;
			
			allMoves |= possibleMoves(board, coord);
		}
		
		return allMoves;
	}
	
	public long threaten(Board board) {
		
		long threatened = 0L;
		long bishops = board.blackBishops;
		long coord = 0L;
		int b = 0;
		
		while(bishops != 0) {
			threat = true;
			b = Long.numberOfTrailingZeros(bishops);
			coord = 1L<<b;
			bishops &= ~coord;
			
			threatened |= possibleMoves(board, coord);
		}
		
		return threatened;
	}
}
