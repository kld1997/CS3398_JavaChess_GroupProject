//Roy Grady, rook
package Pieces;

import Engine.*;
import Visuals.*;
import Pieces.PieceTypes.*;

public class Rook extends Piece implements Cardinal, Castleable
{
	
	public Rook(int t) {
		super(t);
		value = 5;
		name = "Rook";
		ID = 'r';
		iconNum = 2;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HV(occ, coord);
	}
}