//Roy Grady, knight
public class WhiteKnight implements Piece
{
	
	private long moves;
	
	public long possibleMoves(Board board, long coord) {
		
		moves = 0L;
		
		if((board.whiteKnights&coord) != 0) {	
			if((coord&(Board.row7|Board.row8)) == 0) {
				if((coord&Board.colA) == 0 && ((coord>>17)&(board.empty|board.blackPieces)) != 0) {     //up left
					moves |= coord>>17;
				}
				if((coord&Board.colH) == 0 && ((coord>>15)&(board.empty|board.blackPieces)) != 0) {     //up right
					moves |= coord>>15;
				}
			}
			if((coord&(Board.row1|Board.row2)) == 0) {
				if((coord&Board.colA) == 0 && ((coord<<15)&(board.empty|board.blackPieces)) != 0) {     //down left
					moves |= coord<<15;
				}
				if((coord&Board.colH) == 0 && ((coord<<17)&(board.empty|board.blackPieces)) != 0) {     //down right
					moves |= coord<<17;
				}
			}
			if((coord&(Board.colA|Board.colB)) == 0) {
				if((coord&Board.row8) == 0 && ((coord>>10)&(board.empty|board.blackPieces)) != 0) {     //left up
					moves |= coord>>10;
				}
				if((coord&Board.row1) == 0 && ((coord<<6)&(board.empty|board.blackPieces)) != 0) {     //left down
					moves |= coord<<6;
				}
			}
			if((coord&(Board.colG|Board.colH)) == 0) {
				if((coord&Board.row8) == 0 && ((coord>>6)&(board.empty|board.blackPieces)) != 0) {     //right up
					moves |= coord>>6;
				}
				if((coord&Board.row1) == 0 && ((coord<<10)&(board.empty|board.blackPieces)) != 0) {     //right down
					moves |= coord<<10;
				}
			}
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
			board.whiteKnights^= change;
			return true;
		}
		return false;
	}
	
	public long getAllPM(Board board) {
		
		long allMoves = 0L;
		
		allMoves |= (board.whiteKnights>>17)&(board.empty|board.blackPieces)&~(Board.row1|Board.row2)&~Board.colH;
		allMoves |= (board.whiteKnights>>15)&(board.empty|board.blackPieces)&~(Board.row1|Board.row2)&~Board.colA;
		allMoves |= (board.whiteKnights<<15)&(board.empty|board.blackPieces)&~(Board.row7|Board.row8)&~Board.colH;
		allMoves |= (board.whiteKnights<<17)&(board.empty|board.blackPieces)&~(Board.row7|Board.row8)&~Board.colA;
		allMoves |= (board.whiteKnights>>10)&(board.empty|board.blackPieces)&~(Board.colG|Board.colH)&~Board.row1;
		allMoves |= (board.whiteKnights<<6)&(board.empty|board.blackPieces)&~(Board.colG|Board.colH)&~Board.row8;
		allMoves |= (board.whiteKnights>>6)&(board.empty|board.blackPieces)&~(Board.colA|Board.colB)&~Board.row1;
		allMoves |= (board.whiteKnights<<10)&(board.empty|board.blackPieces)&~(Board.colA|Board.colB)&~Board.row8;
		
		return allMoves;
	}
}
