//Roy Grady
package Engine;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import GUI.*;
import Online.*;
import Pieces.*;
import Pieces.PieceTypes.*;
import Options.*;

public class Board {
	
	public static int teamNum = 2;
	public int teamTurn = 0;
	public int[] teamScore = new int[teamNum];
	public boolean cpuTurn = false;
	
	ObjectOutputStream output;
	
	// pieces
	public long[] kingBB = new long[teamNum];
	public long[] pawnsBB = new long[teamNum];	
	public long[] teamBB = new long[teamNum];
	
	public long[] cardinalsBB = new long[teamNum];
	public long[] ordinalsBB = new long[teamNum];
	
	//king and sliders
	public long[] pinnersBB = new long[teamNum];
	public long[] pinnedBB = new long[teamNum];
	public long[] interfereBB = new long[teamNum];
	public long[] slideThreatsBB = new long[teamNum];
	
	//misc
	public long[] notTeamBB = new long[teamNum];
	public long empty;
	
	//aggregate moves and threats
	public long[] threatenBB = new long[teamNum];
	public long[] kThreatsBB = new long[teamNum];
	
	//special position conditions
	public long kingMoved;
	public long rookMoved;
	public long[] epBB = new long[teamNum];
	
	//checks and win conditions
	public int[] check = new int[teamNum];
	public int teamWon;
	
	public static long row1 = -72057594037927936L;
	public static long row2 = 71776119061217280L;
	public static long row4 = 1095216660480L;
	public static long row5 = 4278190080L;
	public static long row7 = 65280L;
	public static long row8 = 255L;
	public static long colA = 72340172838076673L;
	public static long colB = 144680345676153346L;
	public static long colG = 4629771061636907072L;
	public static long colH = -9187201950435737472L;
	
	public static long kingMoves = 460039L;
	public static long knightMoves = 43234889994L;
	public static long archerMoves = 0x4040E1B0E0404L;
	public static long archerCaptures = 0x4040A110A0404L;
	
	public static long rowMasks[] = {
			255L, 65280L, 16711680L, 4278190080L, 1095216660480L, 280375465082880L, 71776119061217280L, -72057594037927936L
	};
	
	public static long colMasks[] = {
			72340172838076673L, 144680345676153346L, 289360691352306692L, 578721382704613384L,
			1157442765409226768L, 2314885530818453536L, 4629771061636907072L, -9187201950435737472L
	};
	
	public static long bltrMasks[] = {
		0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
		0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
		0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
	};
	
	public static long tlbrMasks[] = {
		0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
		0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
		0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
	};
	
	public List<Map<Character, Piece>> pieceList;
	public List<Set<Piece>> cardinalList;
	public List<Set<Piece>> ordinalList;
	public List<Piece> castleableList;
	public List<Set<Piece>> promoteableList;
	public List<Move> teamMoveList;
	
	public Stack<Integer> teamWonStack;
	public Stack<Long> rookMovedStack;
	public Stack<Long> kingMovedStack;
	public Stack<Long> epwStack;
	public Stack<Long> epbStack;
	public Stack<Restore> restoreStack;
	public Stack<Integer> scoreW;
	public Stack<Integer> scoreB;
	public Stack<Integer> checkW;
	public Stack<Integer> checkB;
	public Stack<Long> pinnersW;
	public Stack<Long> pinnersB;
	public Stack<Long> pinnedW;
	public Stack<Long> pinnedB;
	public Stack<Long> slideTW;
	public Stack<Long> slideTB;
	public Stack<Long> interfereW;
	public Stack<Long> interfereB;
	public Stack<Long> threatenW;
	public Stack<Long> threatenB;
	public Stack<Long> kTW;
	public Stack<Long> kTB;
	
	public List<List<Restore>> captureList;
	
	public Options options;
	
	public Board(Options o) {                                                   //initializes all of the bitboards

		options = o;
		
		pieceList = new ArrayList<Map<Character, Piece>>();
		cardinalList = new ArrayList<Set<Piece>>();
		ordinalList = new ArrayList<Set<Piece>>();
		castleableList = new ArrayList<Piece>();
		promoteableList = new ArrayList<Set<Piece>>();
		teamMoveList = new ArrayList<Move>();
		captureList = new ArrayList<List<Restore>>();
		
		teamWonStack = new Stack<Integer>();
		rookMovedStack = new Stack<Long>();
		kingMovedStack = new Stack<Long>();
		epwStack = new Stack<Long>();
		epbStack = new Stack<Long>();
		restoreStack = new Stack<Restore>();
		scoreW = new Stack<Integer>();
		scoreB = new Stack<Integer>();
		checkW = new Stack<Integer>();
		checkB = new Stack<Integer>();
		pinnersW = new Stack<Long>();
		pinnersB = new Stack<Long>();
		pinnedW = new Stack<Long>();
		pinnedB = new Stack<Long>();
		slideTW = new Stack<Long>();
		slideTB = new Stack<Long>();
		interfereW = new Stack<Long>();
		interfereB = new Stack<Long>();
		threatenW = new Stack<Long>();
		threatenB = new Stack<Long>();
		kTW = new Stack<Long>();
		kTB = new Stack<Long>();
		
		for(int i = 0; i < teamNum; i++) {
			kingBB[i] = 0L;
			notTeamBB[i] = 0;
			
			pinnersBB[i] = 0;
			pinnedBB[i] = 0;
			interfereBB[i] = 0;
			slideThreatsBB[i] = 0;
			
			threatenBB[i] = 0;
			kThreatsBB[i] = 0;
			check[i] = 0;
			epBB[i] = 0;
			
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
			
			teamScore[i] = 0;
		}
		
		empty = 0;
		
		kingMoved = 0x1000000000000010L;
		rookMoved = 0x8100000000000081L;
		
		teamWon = 2;
		
		if(options.getOnline())
			output = options.getOutput();
		
		setPieces();
		
	}
	
	public void setPieces() {
		
		pieceList = ParseBoard.pieceInit(options.getBoard(), options.getPromote());
		
		Set<Piece> cardinal = new HashSet<Piece>();
		Set<Piece> ordinal = new HashSet<Piece>();
		List<Piece> castleable = new ArrayList<Piece>();
		Set<Piece> promoteable = new HashSet<Piece>();
		
		for(int i = 0; i < teamNum; i++) {	
			
			cardinal = new HashSet<Piece>();
			ordinal = new HashSet<Piece>();
			castleable = new ArrayList<Piece>();
			promoteable = new HashSet<Piece>();
			
			for(Piece piece : pieceList.get(i).values()) {
				if(piece instanceof Cardinal)
					cardinal.add(piece);
				if(piece instanceof Ordinal)
					ordinal.add(piece);
				if(piece instanceof Castleable)
					castleable.add(piece);
				if(piece instanceof Promoteable)
					promoteable.add(piece);
					
			}
			
			cardinalList.add(cardinal);
			ordinalList.add(ordinal);
			castleableList.addAll(castleable);
			promoteableList.add(promoteable);
			captureList.add(new ArrayList<Restore>());
		}

		currentState();
		
	}
	
	public long convertStringToBitboard(String Binary) {                    //converts the string of binary numbers into actual binary fit for bitboards
		
        if (Binary.charAt(0)=='0') {                                        //not negative
            return Long.parseLong(Binary, 2);
        } else {
            return Long.parseLong("1"+Binary.substring(2), 2)*2;
        }
    }
	
	public void currentState() {
		
		teamMoveList.clear();
		
		for(int i = 0; i < teamNum; i++) {
			if(pieceList.get(i).get('K').piece == 0 && (pieceList.get(i).containsKey('P') && pieceList.get(i).get('P').piece != 0)) {
				pieceList.get(i).get('K').piece = 1L<<Long.numberOfTrailingZeros(pieceList.get(i).get('P').piece);
				pieceList.get(i).get('P').piece ^= pieceList.get(i).get('K').piece;
			}
			kingBB[i] = pieceList.get(i).get('K').piece;
			check[i] = 0;
			teamBB[i] = 0L;
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
			
			for(Piece piece : pieceList.get(i).values()) {
				teamBB[i] |= piece.piece;
			}
			
			for(Piece piece : cardinalList.get(i)) {
				cardinalsBB[i] |= piece.piece;
			}

			for(Piece piece : ordinalList.get(i)) {
				ordinalsBB[i] |= piece.piece;
			}
			
			if(pieceList.get(i).containsKey('j')) {
				if(pieceList.get(i).get('j').hv)
					cardinalsBB[i] |= pieceList.get(i).get('j').piece;
				else
					ordinalsBB[i] |= pieceList.get(i).get('j').piece;
			}
		}
		
		notTeamBB[0] = ~(teamBB[0]);
		notTeamBB[1] = ~(teamBB[1]);
		
		empty = ~(teamBB[0]|teamBB[1]);
		
		pieceMoved();
		
		if(!options.getCaptureKing() && !(pieceList.get(teamTurn).containsKey('P') && pieceList.get(teamTurn).get('P').piece != 0)) {
			
			getThreaten();
			
			Check.getPinned(this);
			
			Check.slideThreats(this);
		}

		checkmate();
		
		if(((pieceList.get(0).get('p').piece&row8)|(pieceList.get(1).get('p').piece&row1)) != 0 && !cpuTurn) {
			PawnPromote.pawnPromotion(this);
		}
		
	}
	
	public void pieceMoved() {
		
		long rooks = 0L;
		
		for(int i = 0; i < teamNum; i++) {
			rooks |= castleableList.get(i).piece;
		}

		if(kingMoved != 0 && kingMoved != ((kingBB[0]|kingBB[1])&kingMoved)) {  	//if king moves
			kingMoved &= (kingBB[0]|kingBB[1]);
		}
		if(rookMoved != 0 && rookMoved != (rooks&rookMoved)) {	//if rook moves
			rookMoved &= rooks;
		}
	}

	public void checkmate() {
		
		for(Piece piece : pieceList.get(teamTurn).values()) {
			piece.getAllPM(this);
			teamMoveList.addAll(piece.moveList);
		}
		Collections.shuffle(teamMoveList);
		teamMoveList.sort(Comparator.comparing(Move -> Move.type, Comparator.reverseOrder()));
		if(teamMoveList.isEmpty() && teamWon == 2) {
			if(check[teamTurn] == 1) {
				teamWon = 1 - teamTurn;
				teamScore[Math.abs(teamTurn-1)] = Integer.MAX_VALUE;
			}
			else {
				teamWon = -1;
				teamScore[0] = 0;
				teamScore[1] = 0;
			}
		}
		
	}
	
	public void getThreaten() {
		
		long currentThreat;
		int noti = 0;
		
		for(int i = 0; i < teamNum; i++) {
			noti = Math.abs(i-1);
			threatenBB[i] = 0;
			kThreatsBB[noti] = 0;
			currentThreat = 0;
			
			for(Piece piece : pieceList.get(i).values()) {
				currentThreat = piece.threaten(this);
				threatenBB[i] |= currentThreat;
				if((currentThreat&kingBB[noti]) != 0) {
					kThreatsBB[noti] |= piece.threatPos(this, kingBB[noti]);
				}
			}
		}
	}
	
	public void removePiece(long coord) {
		
		for(int i = 0; i < teamNum; i++) {
			if((teamBB[i]&coord) != 0) {
				for(Piece piece : pieceList.get(i).values()) {
					if((piece.piece&coord) != 0) {
						piece.piece ^= coord;
						teamScore[Math.abs(i-1)] += piece.value;
						return;
					}
				}
			}
		}
	}
	
	public void removePiece(long coord, int team, int enemyTeam) {
		
		for(Piece piece : pieceList.get(enemyTeam).values()) {
			if((piece.piece&coord) != 0) {
				if(cpuTurn) {
					restoreStack.add(new Restore(enemyTeam, piece.ID, coord));
				}
				else
					captureList.get(team).add(new Restore(enemyTeam, piece.ID, coord));
				piece.piece ^= coord;
				teamScore[team] += piece.value;
				return;
			}
		}
	}
	
	public void removePromote(long coord, int team, int value) {
		
		if(cpuTurn) {
			restoreStack.add(new Restore(team, 'p', coord));
		}
		pieceList.get(team).get('p').piece ^= coord;
		teamScore[team] += value - 1;
	}
	
	static public void displayBitboard(long bitBoard) {
		
		String display = Long.toBinaryString(bitBoard);
		
		while(display.length() <= 64) {
			display = "0" + display;
		}
		
		for(int i = 7; i >= 0; i--) {
			for(int j = 8; j > 0; j--) {
				System.out.print(display.charAt(8*i+j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static long convertToCoord(String pos) {
		
		try {
			int x = pos.charAt(0) - 97;
			int y = 8 - Integer.parseInt(pos.substring(1,2));
			long coord = 1L<<((y*8)+x);
			
			return coord;
		} catch(NullPointerException e) {
			return -1L;
		}
	}
	
	public static long convertToCoord(int x, int y) {

		long coord = 1L<<((y*8)+x);
		
		return coord;
	}
	
	public void enPassant(long coord1, long coord2, int team) {
		
		if(team == 0) {
			if(coord2 == coord1>>16)
				epBB[0] |= coord1>>8;
			else if((coord2&epBB[1]) != 0)
				removePiece(coord2<<8, team, Math.abs(team-1));
		}
		else {
			if(coord2 == coord1<<16)
				epBB[1] |= coord1<<8;
			else if((coord2&epBB[0]) != 0)
				removePiece(coord2>>8, team, Math.abs(team-1));
		}
	}
	
	public void castle(long coord1, long coord2, int team) {
		
		if(coord2 == coord1<<2) {
			castleableList.get(team).piece ^= coord2>>1|(castleableList.get(team).piece&(rookMoved&~(coord1-1)));
		}
		else if(coord2 == coord1>>2) {
			castleableList.get(team).piece ^= coord2<<1|(castleableList.get(team).piece&(rookMoved&(coord1-1)));
		}
	}
	
	public boolean makeMove(int team, Move move) {
		
		boolean valid = false;
		boolean cpuTemp = cpuTurn;
		
		if(cpuTurn)
			currentState();
		
		if(teamMoveList.contains(move)) {
			move = teamMoveList.get(teamMoveList.indexOf(move));
			valid = true;
			long coord1 = 1L<<move.from;
			long coord2 = 1L<<move.to;
			cpuTurn = false;
			if(move.type == 6) {
				removePiece(coord2, team, Math.abs(team-1));
			}
			else
				pieceList.get(team).get(move.pid).movePiece(this, coord1, coord2, true);
			
			cpuTurn = cpuTemp;
			
			if(move.pid == 'p')
				enPassant(coord1, coord2, team);
			else if(move.pid == 'K')
				castle(coord1, coord2, team);
			
			epBB[Math.abs(team-1)] = 0;
			
			if((move.type == 3 || move.type == 4) && cpuTurn) {
				PawnPromote.promotePawn(teamTurn, coord2, this, 1);
			}

			if(cpuTurn || (move.type != 3 && move.type != 4))
				switchTeamTurn();
			
			currentState();
		}
		
		return valid;
	}
	
	public void switchTeamTurn() {
		
		teamTurn = Math.abs(teamTurn-1);
	}
	
	public void rewindMove(GameMove move)
	{
		long coord1 = convertToCoord(move.getFrom().toString());
		long coord2 = convertToCoord(move.getTo().toString());
		//Move m = new Move(Long.numberOfTrailingZeros(coord1), Long.numberOfTrailingZeros(coord2));
		switchTeamTurn();

		pieceList.get(teamTurn).get(move.getID()).movePiece(this, coord1, coord2, true);
		if(move.getCapture())
		{
			pieceList.get(Math.abs(teamTurn-1)).get(move.getCaptureID()).piece |= coord2;
		}
		currentState();

	}
	
	public boolean makeMove(String pos) {
		
		long coord1 = convertToCoord(pos.substring(0,2));
		long coord2 = convertToCoord(pos.substring(2,4));
		
		Move move = new Move(Long.numberOfTrailingZeros(coord1), Long.numberOfTrailingZeros(coord2));
		
		boolean valid = makeMove(teamTurn, move);
		return valid;
	}
	
	public boolean cpuStart() {
		
		if(options.getCPU() > 0) {
			currentState();
			cpuTurn = true;
			makeMove(1, alphaBeta(options.getCPU(), Integer.MIN_VALUE, Integer.MAX_VALUE).move);
			cpuTurn = false;
			return true;
		}
		else
			return false;
	}
	
	public void cpuMove(Move move) {
		
		long coord1 = 1L<<move.from;
		long coord2 = 1L<<move.to;
		
		if((coord2&kingBB[Math.abs(teamTurn-1)]) != 0) {
			
			ParseBoard.displayBitboards(pieceList);
		}

		teamWonStack.add(teamWon);
		rookMovedStack.add(rookMoved);
		kingMovedStack.add(kingMoved);
		epwStack.add(epBB[0]);
		epbStack.add(epBB[1]);
		scoreW.add(teamScore[0]);
		scoreB.add(teamScore[1]);
		checkW.add(check[0]);
		checkB.add(check[1]);
		pinnersW.add(pinnersBB[0]);
		pinnersB.add(pinnersBB[1]);
		pinnedW.add(pinnedBB[0]);
		pinnedB.add(pinnedBB[1]);
		slideTW.add(slideThreatsBB[0]);
		slideTB.add(slideThreatsBB[1]);
		interfereW.add(interfereBB[0]);
		interfereB.add(interfereBB[1]);
		threatenW.add(threatenBB[0]);
		threatenB.add(threatenBB[1]);
		kTW.add(kThreatsBB[0]);
		kTB.add(kThreatsBB[1]);
		
		if(move.type == 6) {
			removePiece(coord2, teamTurn, Math.abs(teamTurn-1));
		}
		else
			pieceList.get(teamTurn).get(move.pid).movePiece(this, coord1, coord2, true);
		
		if(move.type == 3 || move.type == 4) {
			PawnPromote.promotePawn(teamTurn, coord2, this, 1);
		}
		
		if(move.type == 2)
			enPassant(coord1, coord2, teamTurn);
		else if(move.type == 5)
			castle(coord1, coord2, teamTurn);
		
		epBB[Math.abs(teamTurn-1)] = 0;

		teamTurn = Math.abs(teamTurn-1);
		
		currentState();
		
	}
	
	public void undoMove(Move move) {
		
		teamTurn = Math.abs(teamTurn-1);
		
		long coord1 = 1L<<move.from;
		long coord2 = 1L<<move.to;
		
		if(move.type < 3) {
			long redo = coord1|coord2;
			pieceList.get(teamTurn).get(move.pid).piece ^= redo;
			if(move.pid == 'j')
				pieceList.get(teamTurn).get(move.pid).hv ^= true;
			if(move.type > 0) {
				Restore rst = restoreStack.pop();
				if(rst.pid == 'K') {
					pieceList.get(rst.team).get('P').piece |= pieceList.get(rst.team).get('K').piece;
					pieceList.get(rst.team).get('K').piece = 0;
				}
				pieceList.get(rst.team).get(rst.pid).piece |= rst.coord;
			}
		}
		else if(move.type == 3) {
			Restore rst = restoreStack.pop();
			removePiece(rst.coord);
			pieceList.get(rst.team).get('p').piece |= coord1;
		}
		else if(move.type == 4) {
			Restore promoteR = restoreStack.pop();
			removePiece(promoteR.coord);
			Restore enemyR = restoreStack.pop();
			if(enemyR.pid == 'K') {
				pieceList.get(enemyR.team).get('P').piece |= pieceList.get(enemyR.team).get('K').piece;
				pieceList.get(enemyR.team).get('K').piece = 0;
			}
			pieceList.get(enemyR.team).get(enemyR.pid).piece |= enemyR.coord;
			pieceList.get(promoteR.team).get('p').piece |= coord1;
		}
		else if(move.type == 5) {
			if(coord1<<2 == coord2)
				castleableList.get(teamTurn).piece ^= coord2>>1|coord2<<1;
			else
				castleableList.get(teamTurn).piece ^= coord2<<1|coord2>>2;
			
			long redo = coord1|coord2;
			pieceList.get(teamTurn).get(move.pid).piece ^= redo;
		}
		else if(move.type == 6) {
			Restore rst = restoreStack.pop();
			if(rst.pid == 'K') {
				pieceList.get(rst.team).get('P').piece |= pieceList.get(rst.team).get('K').piece;
				pieceList.get(rst.team).get('K').piece = 0;
			}
			pieceList.get(rst.team).get(rst.pid).piece |= rst.coord;
		}
		
		teamWon = teamWonStack.pop();
		rookMoved = rookMovedStack.pop();
		kingMoved = kingMovedStack.pop();
		epBB[0] = epwStack.pop();
		epBB[1] = epbStack.pop();
		teamScore[0] = scoreW.pop();
		teamScore[1] = scoreB.pop();
		check[0] = checkW.pop();
		check[1] = checkB.pop();
		pinnersBB[0] = pinnersW.pop();
		pinnersBB[1] = pinnersB.pop();
		pinnedBB[0] = pinnedW.pop();
		pinnedBB[1] = pinnedB.pop();
		slideThreatsBB[0] = slideTW.pop();
		slideThreatsBB[1] = slideTB.pop();
		interfereBB[0] = interfereW.pop();
		interfereBB[1] = interfereB.pop();
		threatenBB[0] = threatenW.pop();
		threatenBB[1] = threatenB.pop();
		kThreatsBB[0] = kTW.pop();
		kThreatsBB[1] = kTB.pop();
		
		for(int i = 0; i < teamNum; i++) {
			kingBB[i] = pieceList.get(i).get('K').piece;
			teamBB[i] = 0L;
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
			
			for(Piece piece : pieceList.get(i).values()) {
				teamBB[i] |= piece.piece;
			}
			
			for(Piece piece : cardinalList.get(i)) {
				cardinalsBB[i] |= piece.piece;
			}

			for(Piece piece : ordinalList.get(i)) {
				ordinalsBB[i] |= piece.piece;
			}
			
		}
		
		notTeamBB[0] = ~(teamBB[0]);
		notTeamBB[1] = ~(teamBB[1]);
		
		empty = ~(teamBB[0]|teamBB[1]);
	}
	
	public long showMoves(String pos) {
		
		long coord = convertToCoord(pos);
		
		for(int i = 0; i < teamNum; i++) {
			if((coord&teamBB[i]) != 0) {
				for(Piece piece : pieceList.get(i).values()) {
					if((coord&piece.piece) != 0)
						return piece.possibleMoves(this, coord, true);
				}
			}
		}
		return 0L;
	}
	
	private MoveScore alphaBeta(int depth, int alpha, int beta) {

		Move best = null;
		MoveScore ms;
		
	    if(depth == 0 || teamWon != 2)
	        return new MoveScore(teamScore[1] - teamScore[0], best);
	    else {
	    	List<Move> tmp = new ArrayList<Move>(teamMoveList);
	        if(teamTurn == 1) {
	            for(Move move : tmp) {
	                cpuMove(move);
	                ms = alphaBeta(depth - 1, alpha, beta);
	                undoMove(move);
	                if(ms.score > alpha) {
	                	if(alpha == Integer.MIN_VALUE || ThreadLocalRandom.current().nextInt(0, 100) <= options.getCPUMissChance()) {
	                		alpha = ms.score;
		                    best = move;
	                	}
	                    if(alpha >= beta)
	                        break;
	                }
	            }
	            return new MoveScore(alpha, best);
	        }
	        else {
	            for(Move move : tmp) {
	                cpuMove(move);
	                ms = alphaBeta(depth - 1, alpha, beta);
	                undoMove(move);
	                if(ms.score < beta) {
	                	if(beta == Integer.MAX_VALUE || ThreadLocalRandom.current().nextInt(0, 100) <= options.getCPUMissChance()) {
	                		beta = ms.score;
	                		best = move;
	                	}
	                    if(alpha >= beta)
	                        break;
	                }
	            }
	            return new MoveScore(beta, best);
	        }
	    }
	}
	
}
