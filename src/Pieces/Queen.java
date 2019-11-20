//Roy Grady, queen
package Pieces;

import Pieces.PieceTypes.*;
import Engine.*;
import Visuals.*;

public class Queen extends Piece implements Cardinal, Ordinal
{
	
	public Queen(int t) {
		super(t);
		value = 9;
		name = "Queen";
		ID = 'q';
		iconNum = 1;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HVDX(occ, coord);
	}
}