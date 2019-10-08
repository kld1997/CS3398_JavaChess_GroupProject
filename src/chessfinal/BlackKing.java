//Roy Grady, black king
package chessfinal;
public class BlackKing implements Piece
{
	
	private long moves;
	private boolean threat = false;
	
	public long possibleMoves(Board board, long coord) {
		moves = 0L;
		
		if((board.blackKing&coord) != 0) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			if(trail == 9) {
				moves = Board.kingMoves;
			}
			else {
				if(trail > 9) {
					moves = Board.kingMoves<<(trail - 9);
				}
				else if(trail < 9) {
					moves = Board.kingMoves>>(9 - trail);
				}
				
				if((coord&Board.colA) != 0) {
					moves &= ~Board.colH;
				}
				if((coord&Board.colH) != 0) {
					moves &= ~Board.colA;
				}
			}
			
		}	
		
		if(!threat)
			moves &= board.notBlack;
		
		threat = false;
		
		moves &= ~board.whiteThreaten;
		
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
			board.blackKing^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long king = board.blackKing;
		long coord = 0L;
		int k = 0;
		
		while(king != 0) {
			k = Long.numberOfTrailingZeros(king);
			coord = 1L<<k;
			king &= ~coord;
			
			allMoves |= possibleMoves(board, coord);
		}
		
		return allMoves;
	}
	
	public long threaten(Board board) {
		
		long threatened = 0L;
		long king = board.blackKing;
		long coord = 0L;
		int k = 0;
		
		while(king != 0) {
			threat = true;
			k = Long.numberOfTrailingZeros(king);
			coord = 1L<<k;
			king &= ~coord;
			
			threatened |= possibleMoves(board, coord);
		}
		
		return threatened;
	}
}
