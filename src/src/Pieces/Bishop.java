//Roy Grady, bishop
package Pieces;

import Pieces.PieceTypes.Ordinal;
import Engine.*;
import Visuals.*;

public class Bishop extends Piece implements Ordinal
{
	
	public Bishop(int t) {
		super(t);
		value = 3;
		name = "Bishop";
		ID = 'b';
		iconNum = 4;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.DX(occ, coord);
	}
}