//Roy Grady, king
package Pieces;

import Engine.*;
import Visuals.*;

public class King extends Piece
{
	
	public King(int t) {
		super(t);
		name = "King";
		ID = 'K';
		iconNum = 0;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long pieceType(Board board) {
		return board.kingBB[team];
	}
	
	public long possibleMoves(Board board, long coord, boolean update) {

		if(update)
			update(board);
		
		moves = 0L;
		long slideThreats;
		long threaten;
		long rooks;
		
		slideThreats = board.slideThreatsBB[team];
		threaten = board.threatenBB[enemyTeam];
		rooks = board.castleableList.get(team).piece;
		
		if((piece&coord) != 0)
			moves = pseudoMoves(coord);
		
		if((board.kingMoved&piece) != 0 && check == 0) {	//castling
			rooks &= board.rookMoved&board.castleableList.get(team).piece;
			long checkSpaces = 0L;
			while(rooks != 0) {
				long rookPos = Long.numberOfTrailingZeros(rooks);
				coord = 1L<<rookPos;
				
				checkSpaces = Check.obstruct(piece, coord)&~((piece|rooks)^board.empty);

				if(coord-1>piece-1 && checkSpaces == 0) {
					moves |= piece<<2;
				}
				else if(coord-1<piece-1 && checkSpaces == 0) {
					moves |= piece>>2;
				}
				
				rooks &= rooks - 1;
			}
		}
		
		moves &= ~slideThreats;
		
		if(!threat)
			moves &= notAlly&~threaten;
		
		return moves;
	}
	
	public void getAllPM(Board board) {

		moveList.clear();
		
		update(board);

		long allMoves;
		long captures;
		long castles = 0L;
		long temp = piece;
		long coord = 0L;
		int p = 0;
		int t = 0;

		while(temp != 0) {
			p = Long.numberOfTrailingZeros(temp);
			coord = 1L<<p;
			temp &= ~coord;

			allMoves = possibleMoves(board, coord, false);
			captures = allMoves&enemyPieces;
			if(coord == board.kingMoved)
				castles = allMoves&(coord>>2|coord<<2);
			allMoves &= ~(captures|castles);
			
			while(allMoves != 0) {
				t = Long.numberOfTrailingZeros(allMoves);
				moveList.add(new Move(p, t, 0, ID));
				allMoves &= allMoves - 1;
			}
			while(captures != 0) {
				t = Long.numberOfTrailingZeros(captures);
				moveList.add(new Move(p, t, 1, ID));
				captures &= captures - 1;
			}
			while(castles != 0) {
				t = Long.numberOfTrailingZeros(castles);
				moveList.add(new Move(p, t, 5, ID));
				castles &= castles - 1;
			}
		}
	}
	
	public long pseudoMoves(long coord) {
		
		int trail = Long.numberOfTrailingZeros(coord);
		long pm = 0L;
		
		if(trail == 9) {
			pm = Board.kingMoves;
		}
		else {
			if(trail > 9) {
				pm = Board.kingMoves<<(trail - 9);
			}
			else if(trail < 9) {
				pm = Board.kingMoves>>(9 - trail);
			}
			
			if((coord&Board.colA) != 0) {
				pm &= ~Board.colH;
			}
			if((coord&Board.colH) != 0) {
				pm &= ~Board.colA;
			}
		}
		
		return pm;
	}
	
}