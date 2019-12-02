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
		long EP = board.epBB[enemyTeam];
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
	
	public void getAllPM(Board board) {
		
		moveList.clear();
		
		update(board);
		
		long allMoves;
		long EP;
		long cr;
		long caps;
		long temp = piece&pinned;
		piece &= ~temp;
		int p ,t = 0;
		
		if(check == 1) {
			cr = board.kThreatsBB[team]|board.interfereBB[team];
		}
		else {
			cr = 0xFFFFFFFFFFFFFFFFL;
			if(temp != 0) {
				long temp2 = temp;
				long coord, captures;
				while(temp2 != 0) {
					p = Long.numberOfTrailingZeros(temp2);
					coord = 1L<<p;
					temp2 &= ~coord;
	
					allMoves = possibleMoves(board, coord, false);
					captures = allMoves&enemyPieces;
					allMoves &= ~captures;
					
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
				}
			}
		}
		
		EP = board.epBB[enemyTeam];
		if(check < 2) {
			if(team == 0) {
				allMoves = (piece>>8)&board.empty&cr;
				addMoves(allMoves&Board.row8, 8, 3);
				addMoves(allMoves&~Board.row8, 8, 0);
				allMoves = (piece>>16)&board.empty&(board.empty>>8)&Board.row4&cr;
				addMoves(allMoves, 16, 0);
				allMoves = (piece>>7)&~Board.colA&cr;
				caps = allMoves&enemyPieces;
				addMoves(caps&Board.row8, 7, 4);
				addMoves(caps&~Board.row8, 7, 1);
				if((allMoves&EP) != 0) addEPs(board, allMoves&EP, 7, EP);
				allMoves = (piece>>9)&~Board.colH&cr;
				caps = allMoves&enemyPieces;
				addMoves(caps&Board.row8, 9, 4);
				addMoves(caps&~Board.row8, 9, 1);
				if((allMoves&EP) != 0) addEPs(board, allMoves&EP, 9, EP);
			}
			else {
				allMoves = (piece<<8)&board.empty&cr;
				addMoves(allMoves&Board.row1, -8, 3);
				addMoves(allMoves&~Board.row1, -8, 0);
				allMoves = (piece<<16)&board.empty&(board.empty<<8)&Board.row5&cr;
				addMoves(allMoves, -16, 0);
				allMoves = (piece<<7)&~Board.colH&cr;
				caps = allMoves&enemyPieces;
				addMoves(caps&Board.row1, -7, 4);
				addMoves(caps&~Board.row1, -7, 1);
				if((allMoves&EP) != 0) addEPs(board, allMoves&EP, -7, EP);
				allMoves = (piece<<9)&~Board.colA&cr;
				caps = allMoves&enemyPieces;
				addMoves(caps&Board.row1, -9, 4);
				addMoves(caps&~Board.row1, -9, 1);
				if((allMoves&EP) != 0) addEPs(board, allMoves&EP, -9, EP);
			}
		}
		piece |= temp;
	}
	
	public long threaten(Board board) {
		
		update(board);
		
		long threats = 0L;
		
		if(team == 0) {
			threats |= (piece>>7)&~Board.colA;
			threats |= (piece>>9)&~Board.colH;
		}
		else {
			threats |= (piece<<7)&~Board.colH;
			threats |= (piece<<9)&~Board.colA;
		}
		
		return threats;
		
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
		for(Piece piece : board.cardinalList.get(enemyTeam)) {
			cardinal |= piece.piece;
		}
		
		long temp = coord|EPpawn;

		if((Moves.horizontal(~board.empty^temp, king)&cardinal) != 0)
			return false;
		else
			return true;
	}
	
	public void addMoves(long all, int pos, int type) {
		
		long allMoves = all;
		int p;
		int t;
		while(allMoves != 0) {
			t = Long.numberOfTrailingZeros(allMoves);
			p = t + pos;
			moveList.add(new Move(p, t, type, ID));
			allMoves &= allMoves - 1;
		}
	}
	
	public void addEPs(Board board, long all, int pos, long EP) {
		
		long allMoves = all;
		int p;
		int t;
		while(allMoves != 0) {
			t = Long.numberOfTrailingZeros(allMoves);
			p = t + pos;
			if(EPpass(board, 1L<<t, EP))
				moveList.add(new Move(p, t, 2, ID));
			allMoves &= allMoves - 1;
		}
	}
}