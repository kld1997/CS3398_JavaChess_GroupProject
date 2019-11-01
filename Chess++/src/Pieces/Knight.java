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
		
		int trail = Long.numberOfTrailingZeros(coord);	
		long pm = 0L;
		
		if(trail == 18) {
			pm = Board.knightMoves;
		}
		else {
			if(trail > 18) {
				pm = Board.knightMoves<<(trail - 18);
			}
			else if(trail < 18) {
				pm = Board.knightMoves>>(18 - trail);
			}
			
			if((coord&(Board.colA|Board.colB)) != 0) {
				pm &= ~(Board.colG|Board.colH);
			}
			if((coord&(Board.colG|Board.colH)) != 0) {
				pm &= ~(Board.colA|Board.colB);
			}
		}
		
		return pm;
	}
}