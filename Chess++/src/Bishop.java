//Roy Grady, bishop
public class Bishop extends Pieces implements Ordinal
{
	
	public Bishop(int t) {
		super(t);
		value = 3;
		name = "Bishop";
		ID = 'b';
		iconNum = 4;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pieceType(Board board) {
		return board.bishopsBB[team];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.DX(occ, coord);
	}
}