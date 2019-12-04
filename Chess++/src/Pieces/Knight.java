//Roy Grady, knight
package Pieces;

import Engine.*;
import Visuals.*;

public class Knight extends Piece
{
	
	public Knight(int t) {
		super(t);
		value = 3;
		name = "Knight";
		ID = 'k';
		iconNum = 3;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.knightPseudoMoves(coord);
	}
}