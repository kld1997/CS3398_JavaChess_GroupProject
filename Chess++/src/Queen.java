//Roy Grady, queen
public class Queen extends Pieces
{
	
	public Queen(int t) {
		super(t);
		value = 9;
		name = "Queen";
		image = Images.pieces[Math.abs(team-1)][1];
	}
	
	public long pieceType(Board board) {
		return board.queensBB[team];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HVDX(occ, coord);
	}
}