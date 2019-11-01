//Roy Grady, rook
public class Rook extends Pieces implements Cardinal, Castleable
{
	
	public Rook(int t) {
		super(t);
		value = 5;
		name = "Rook";
		ID = 'r';
		iconNum = 2;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pieceType(Board board) {
		return board.rooksBB[team];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HV(occ, coord);
	}
}