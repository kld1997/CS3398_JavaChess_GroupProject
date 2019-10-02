//Roy Grady, king
public class WhiteKing implements Piece
{
	
	private long moves;
	
	public long possibleMoves(Board board, long coord) {
		System.out.println("wee");
		moves = 0L;
		
		if((board.whiteKing&coord) != 0) {	
			int trail = Long.numberOfTrailingZeros(coord);
			
			if(trail == 9) {
				moves = Board.kingMoves;
			}
			else {
				if(trail > 9) {
					moves = Board.kingMoves>>>(trail - 2);
		
					moves &= ~Board.row1;
				}
				else if(trail < 9) {
					moves = Board.kingMoves<<(9 - trail);
					
					moves &= ~Board.row8;
				}
				
				if((coord&Board.colA) != 0) {
					moves &= ~Board.colH;
				}
				if((coord&Board.colH) != 0) {
					moves &= ~Board.colA;
				}
			}
			
		}	
	
		return moves;
	}
	
	public void movePiece(Board board, long coord1, long coord2, boolean checked) {
		
		if(checked == false)
			moves = possibleMoves(board, coord1);
		
		long change = coord1|coord2;
		
		if((coord2&moves) != 0) {
			if((board.blackPieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			board.whiteKing^= change;
		}
		
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		long king = board.whiteKing;
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
}
