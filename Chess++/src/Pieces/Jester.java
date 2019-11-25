//Roy Grady, jester
package Pieces;

import Pieces.PieceTypes.*;
import Engine.*;
import Visuals.*;

public class Jester extends Piece
{
	
	public Jester(int t) {
		super(t);
		value = 7;
		name = "Jester";
		ID = 'j';
		iconNum = 0;
		image = Images.pieces[Math.abs(team-1)][iconNum];
		hv = true;
	}
	
	public long pseudoMoves(long occ, long coord) {
		if(hv)
			return Moves.HV(occ, coord);
		else
			return Moves.DX(occ, coord);
	}
	
	public boolean movePiece(Board board, long coord1, long coord2, boolean checked) {

		if(!checked)
			moves = possibleMoves(board, coord1, false);

		long change = coord1|coord2;

			board.removePiece(coord2, team, enemyTeam);
			
		piece^= change;
		hv = !hv;
		return true;
	}
}