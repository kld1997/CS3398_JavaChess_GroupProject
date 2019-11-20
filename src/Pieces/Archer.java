//Roy Grady, archer
package Pieces;

import Pieces.PieceTypes.*;
import Engine.*;
import Visuals.*;

public class Archer extends Piece
{
	
	public Archer(int t) {
		super(t);
		value = 4;
		name = "Archer";
		ID = 'a';
		iconNum = 1;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pseudoMoves(long occ, long coord) {
		return Moves.HVDX(occ, coord)&Moves.archerPseudoMoves(coord);
	}
	
	public void getAllPM(Board board) {

		moveList.clear();
		
		update(board);

		long allMoves;
		long captures;
		long temp = piece;
		long coord = 0L;
		int p = 0;
		int t = 0;

		while(temp != 0) {
			p = Long.numberOfTrailingZeros(temp);
			coord = 1L<<p;
			temp &= ~coord;

			allMoves = possibleMoves(board, coord, false);
			captures = allMoves&Moves.archerPseudoCaptures(coord);
			allMoves &= ~(captures|enemyPieces);
			captures &= enemyPieces;
			
			while(allMoves != 0) {
				t = Long.numberOfTrailingZeros(allMoves);
				moveList.add(new Move(p, t, 0, ID));
				allMoves &= allMoves - 1;
			}
			while(captures != 0) {
				t = Long.numberOfTrailingZeros(captures);
				moveList.add(new Move(p, t, 6, ID));
				captures &= captures - 1;
			}
		}
	}
	
	public long threaten(Board board) {
		
		update(board);
		
		long threats = 0L;
		long temp = piece;
		long coord = 0L;
		int p = 0;
		
		threat = true;
		while(temp != 0) {
			p = Long.numberOfTrailingZeros(temp);
			coord = 1L<<p;
			temp &= ~coord;

			threats |= possibleMoves(board, coord, false)&Moves.archerPseudoCaptures(coord);
		}
		threat = false;
		
		return threats;
	}
}