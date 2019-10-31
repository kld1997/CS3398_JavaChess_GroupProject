
//Roy Grady, rook
public class Rook extends Pieces
{

	public Rook(int t) {
		super(t);
		value = 5;
		name = "Rook";
		image = Images.pieces[Math.abs(team-1)][2];
		iconNum = 2;
	}

	public long pieceType(Board board) {
		return board.rooksBB[team];
	}

	public long pseudoMoves(long occ, long coord) {
		return Moves.HV(occ, coord);
	}
}
