//Roy Grady, prince
package Pieces;

import Pieces.PieceTypes.*;
import Engine.*;
import Visuals.*;

public class Prince extends Piece
{
	
	public Prince(int t) {
		super(t);
		value = 5;
		name = "Prince";
		ID = 'P';
		iconNum = 3;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.kingPseudoMoves(coord);
	}
	
}