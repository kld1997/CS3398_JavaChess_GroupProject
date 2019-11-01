//Roy Grady, queen
public class Queen extends Pieces implements Cardinal, Ordinal
{
	
	public Queen(int t) {
		super(t);
		value = 9;
		name = "Queen";
		iconNum = 1;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pieceType(Board board) {
		return board.queensBB[team];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HVDX(occ, coord);
	}
}