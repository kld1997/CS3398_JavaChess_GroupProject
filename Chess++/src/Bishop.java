//Roy Grady, bishop
public class Bishop extends Pieces
{
	
	public Bishop(int t) {
		super(t);
		value = 3;
	}
	
	public long pieceType(Board board) {
		return board.bishopsBB[team];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.DX(occ, coord);
	}
}