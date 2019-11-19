//Roy Grady
package Engine;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import GUI.*;
import Online.*;
import Pieces.*;
import Pieces.PieceTypes.*;
import Options.*;

public class Board {
	
	public static int teamNum = 2;
	public int teamTurn = 0;
	
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
	public long[] allPMBB = new long[teamNum];
	
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
	
	public List<List<Piece>> pieceList;
	public List<List<Piece>> cardinalList;
	public List<List<Piece>> ordinalList;
	public List<Piece> castleableList;
	public List<List<Piece>> promoteableList;
	public List<Piece> kingList;
	
	public Options options;
	
	public Board(Options o) {                                                   //initializes all of the bitboards

		options = o;
		
		pieceList = new ArrayList<List<Piece>>();
		cardinalList = new ArrayList<List<Piece>>();
		ordinalList = new ArrayList<List<Piece>>();
		castleableList = new ArrayList<Piece>();
		promoteableList = new ArrayList<List<Piece>>();
		kingList = new ArrayList<Piece>();
		
		for(int i = 0; i < teamNum; i++) {
			kingBB[i] = 0L;
			notTeamBB[i] = 0;
			
			pinnersBB[i] = 0;
			pinnedBB[i] = 0;
			interfereBB[i] = 0;
			slideThreatsBB[i] = 0;
			
			threatenBB[i] = 0;
			allPMBB[i] = 0;
			kThreatsBB[i] = 0;
			check[i] = 0;
			epBB[i] = 0;
			
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
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
		
		List<Piece> cardinal;
		List<Piece> ordinal;
		List<Piece> castleable;
		List<Piece> promoteable;
		List<Piece> kings;
		
		for(int i = 0; i < teamNum; i++) {	
			cardinal = pieceList.get(i)
					.stream()
					.filter(p -> p instanceof Cardinal)
					.collect(Collectors.toList());
			
			ordinal = pieceList.get(i)
					.stream()
					.filter(p -> p instanceof Ordinal)
					.collect(Collectors.toList());
			
			castleable = pieceList.get(i)
					.stream()
					.filter(p -> p instanceof Castleable)
					.collect(Collectors.toList());
			
			promoteable = pieceList.get(i)
					.stream()
					.filter(p -> p instanceof Promoteable)
					.collect(Collectors.toList());
			
			kings = pieceList.get(i)
					.stream()
					.filter(p -> p instanceof King)
					.collect(Collectors.toList());
			cardinalList.add(cardinal);
			ordinalList.add(ordinal);
			castleableList.addAll(castleable);
			promoteableList.add(promoteable);
			kingList.addAll(kings);
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
		
		for(int i = 0; i < teamNum; i++) {
			kingBB[i] = kingList.get(i).piece;
			check[i] = 0;
			teamBB[i] = 0L;
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
			
			for(Piece piece : pieceList.get(i)) {
				teamBB[i] |= piece.piece;
			}
			
			for(Piece piece : cardinalList.get(i)) {
				cardinalsBB[i] |= piece.piece;
			}

			for(Piece piece : ordinalList.get(i)) {
				ordinalsBB[i] |= piece.piece;
			}
			
			for(Piece piece : promoteableList.get(i)) {
				pawnsBB[i] |= piece.piece;
			}
		}
		
		notTeamBB[0] = ~(teamBB[0]);
		notTeamBB[1] = ~(teamBB[1]);
		
		empty = ~(teamBB[0]|teamBB[1]);
		
		pieceMoved();
		
		getThreaten();
		
		//code for making a bitboard of all of the pinnedB and pinning pieces
		Check.getPinned(this);
		
		Check.slideThreats(this);

		checkmate();
		
		if(((pawnsBB[0]&row8)|(pawnsBB[1]&row1)) != 0) {
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
		
		for(int i = 0; i < teamNum; i++) {
			allPMBB[i] = 0;
			
			for(Piece piece : pieceList.get(i)) {
				allPMBB[i] |= piece.getAllPM(this, false);
			}
			
			if(allPMBB[i] == 0) {
				if(check[i] == 1)
					teamWon = 1 - i;
				else
					teamWon = -1;
			}
		}
	}
	
	public void getThreaten() {
		
		long currentThreat;
		int noti = 0;
		
		for(int i = 0; i < teamNum; i++) {
			threatenBB[i] = 0;
			kThreatsBB[i] = 0;
			currentThreat = 0;
			noti = Math.abs(i-1);
			
			for(Piece piece : pieceList.get(i)) {
				currentThreat = piece.getAllPM(this, true);
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
				for(Piece piece : pieceList.get(i)) {
					if((piece.piece&coord) != 0) {
						piece.piece &= ~coord;
						return;
					}
				}
			}
		}
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
				removePiece(coord2<<8);
		}
		else {
			if(coord2 == coord1<<16)
				epBB[1] |= coord1<<8;
			else if((coord2&epBB[0]) != 0)
				removePiece(coord2>>8);
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
	
	public boolean makeMove(int team, String pos, boolean checked) {
		
		long coord1 = convertToCoord(pos.substring(0,2));
		long coord2 = convertToCoord(pos.substring(2,4));
		
		boolean valid = false;
		
		if((coord1&teamBB[team]) != 0) {
			for(Piece piece : pieceList.get(team)) {	
				if((coord1&piece.piece) != 0) {

					if(piece.name.equals("Builder"))
					{
						valid = (Boolean)piece.buildWall(this, coord1, coord2, checked)[0];
						for(Piece p: pieceList.get(team)) {
							if(p.name.equals("Wall")){p.addPiece(coord2);}
						}
					}
					else
					{
						valid = piece.movePiece(this, coord1, coord2, checked);
					}
					
					if(valid) {
						if(piece.name == "Pawn") {
							enPassant(coord1, coord2, team);
						}
						else if(piece.name == "King") {
							castle(coord1, coord2, team);
						}
						
						epBB[Math.abs(team-1)] = 0;
						currentState();
						if(options.getOnline() && teamTurn == options.getTurn()) {
	                        try {
	                			output.writeObject(pos);
	                			output.flush();
	                		} catch(IOException ioException) {
	                			System.out.println("issue");
	                		}
			}
						break;
					}
				}
			}
		}
		
		return valid;
	}
	
	public boolean makeMove(String pos, boolean checked) {
		
		boolean valid = makeMove(teamTurn, pos, checked);
		
		if(valid)
			teamTurn = Math.abs(teamTurn-1);
		
		return valid;
	}
	
	public long showMoves(String pos) {
		
		long coord = convertToCoord(pos);
		long moves = 0L;
		
		for(int i = 0; i < teamNum; i++) {
			if((coord&teamBB[i]) != 0) {
				for(Piece piece : pieceList.get(i)) {
					if((coord&piece.piece) != 0)
						return piece.possibleMoves(this, coord, true);
				}
			}
		}
		return 0L;
	}
	
}
