package Pieces;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Engine.*;
import Visuals.*;

//Roy Grady
public abstract class Piece
{

	public int team;
	public int enemyTeam;
	public int iconNum;
	public String name;
	public char ID;
	public int value;
	public long piece;
	public long moves;
	public List<Move> moveList;
	public long enemyPieces;
	public long notAlly;
	public int check;
	public long pinned;
	public boolean threat = false;
	public BufferedImage image;

	public Piece(int t) {
		this.team = t;
		this.enemyTeam = Math.abs(t-1);
		this.moves = 0;
		this.moveList = new ArrayList<Move>();
	}

	public void update(Board board) {

		check = board.check[team];
		enemyPieces = board.teamBB[enemyTeam];
		notAlly = board.notTeamBB[team];
		pinned = board.pinnedBB[team];
	}

	public void setImage() {
		image = Images.pieces[team][iconNum];
	}

	public long possibleMoves(Board board, long coord, boolean update) {

		if(update)
			update(board);

		moves = 0L;
		long occ = ~board.empty;

		if((piece&coord) != 0 && check < 2) {
			moves = pseudoMoves(occ, coord);

			if(!threat)
				moves &= notAlly;

			if(check == 1)
				moves &= Check.checkRestrict(board, team, coord, pinned);
			else if((coord & pinned) != 0)
				moves &= Check.pinRestrict(board, coord, team);
		}

		return moves;
	}

	public long pseudoMoves(long occ, long coord) {

		return 0L;
	}

	public boolean movePiece(Board board, long coord1, long coord2, boolean checked) {

		if(!checked)
			moves = possibleMoves(board, coord1, false);

		long change = coord1|coord2;

			board.removePiece(coord2, team, enemyTeam);
			
		piece^= change;
		return true;
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

			threats |= possibleMoves(board, coord, false);
		}
		threat = false;
		
		return threats;
	}

	public long threatPos(Board board, long pCoord) {

		update(board);

		long tPos = 0L;
		long unit = piece;
		long coord = 0L;
		int u = 0;

		while(unit != 0) {
			u = Long.numberOfTrailingZeros(unit);
			coord = 1L<<u;
			unit &= ~coord;

			if((possibleMoves(board, coord, false)&pCoord) != 0) {
				tPos |= coord;
				Check.addCheck(board, enemyTeam);
			}
		}

		return tPos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
}
