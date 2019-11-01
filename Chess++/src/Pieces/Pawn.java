//Roy Grady, pawn
package Pieces;

import Engine.*;
import Pieces.PieceTypes.Promoteable;
import Visuals.*;

public class Pawn extends Piece implements Promoteable
{
	
	public Pawn(int t) {
		super(t);
		value = 1;
		name = "Pawn";
		ID = 'p';
		iconNum = 5;
		image = Images.pieces[Math.abs(team-1)][iconNum];
	}
	
	public long possibleMoves(Board board, long coord, boolean update) {
		
		if(update)
			update(board);
		
		moves = 0L;
		long occ = ~board.empty;
		long EP = 0L;
		
		if(team == 0)
			EP = board.epBB[1];
		else
			EP = board.epBB[0];
		
		if((piece&coord) != 0 && check < 2) {	
			moves = pseudoMoves(occ, coord, EP);
		}	
		
		if(check == 1)
			moves &= Check.checkRestrict(board, team, coord, pinned)|EP;
		else if((coord & pinned) != 0)
			moves &= Check.pinRestrict(board, coord, team);
		
		if((moves&EP) != 0) {
			if(!EPpass(board, coord, EP))
				moves &= ~EP;
		}
	
		return moves;
	}
	
	public long pseudoMoves(long occ, long coord, long EP) {
		
		long pm = 0L;
		
		if(team == 0) {
			if(((coord>>8)&~occ) != 0) {     //push up by 1
				pm |= coord>>8;
				
				if((coord&Board.row2) != 0 && ((coord>>16)&~occ) != 0) {  //push up by 2
					pm |= coord>>16;
				}
			}
			if(((coord>>9)&(enemyPieces|EP)&~Board.colH) != 0) { //capture left
				pm |= coord>>9;
			}
			if(((coord>>7)&(enemyPieces|EP)&~Board.colA) != 0) { //capture right
				pm |= coord>>7;
			}
		}
		else {
			if(((coord<<8)&~occ) != 0) {     //push up by 1
				pm |= coord<<8;
				
				if((coord&Board.row7) != 0 && ((coord<<16)&~occ) != 0) {  //push up by 2
					pm |= coord<<16;
				}
			}
			if(((coord<<7)&(enemyPieces|EP)&~Board.colH) != 0) { //capture left
				pm |= coord<<7;
			}
			if(((coord<<9)&(enemyPieces|EP)&~Board.colA) != 0) { //capture right
				pm |= coord<<9;
			}
		}
		
		return pm;
	}
	
	public long getAllPM(Board board, boolean threaten) {
		
		update(board);
		
		long allMoves = 0L;
		long kThreats= 0L;
		long EP = 0L;
		
		if(!threaten) {
			if(team == 0) {
				EP = board.epBB[1];
				kThreats = board.kThreatsBB[1];
				if(check < 2) {
					allMoves |= (piece>>8)&board.empty;
					allMoves |= (piece>>16)&board.empty&(board.empty>>8)&Board.row4;
					allMoves |= (piece>>7)&(enemyPieces|EP)&~Board.colA;
					allMoves |= (piece>>9)&(enemyPieces|EP)&~Board.colH;
				}
			}
			else {
				EP = board.epBB[0];
				kThreats = board.kThreatsBB[0];
				if(check < 2) {
					allMoves |= (piece<<8)&board.empty;
					allMoves |= (piece<<16)&board.empty&(board.empty<<8)&Board.row5;
					allMoves |= (piece<<7)&(enemyPieces|EP)&~Board.colH;
					allMoves |= (piece<<9)&(enemyPieces|EP)&~Board.colA;
				}
			}	
			if(!EPpass(board, allMoves, EP))
				allMoves &= ~EP;
			if(check == 1)
				allMoves &= kThreats|board.interfereBB[team];
		}
		else {
			if(team == 0) {
				allMoves |= (piece>>7)&~Board.colA;
				allMoves |= (piece>>9)&~Board.colH;
			}
			else {
				allMoves |= (piece<<7)&~Board.colH;
				allMoves |= (piece<<9)&~Board.colA;
			}
		}
		
		return allMoves;
	}
	
	public boolean EPpass(Board board, long coord, long EP) {				//checks if en passant leaves king open
		
		long EPpawn = 0L;
		long king = 0L;
		long cardinal = 0L;
		
		if(team == 0) 
			EPpawn = (moves&EP)<<8;
		else 
			EPpawn = (moves&EP)>>8;
		
		king = board.kingBB[team];
		for(Piece piece : board.cardinalList.get(team)) {
			cardinal |= piece.piece;
		}

		long temp = coord|EPpawn;
		
		if((Moves.horizontal(~board.empty^temp, king)&cardinal) != 0)
			return false;
		else
			return true;
	}
}