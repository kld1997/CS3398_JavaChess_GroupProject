//Roy Grady

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Board {
	
	int teamNum = 2;
	int pieceNum = 7;
	
	// pieces
	long[] kingBB = new long[teamNum];
	long[] queensBB = new long[teamNum];
	long[] rooksBB = new long[teamNum];
	long[] bishopsBB = new long[teamNum];
	long[] knightsBB = new long[teamNum];
	long[] pawnsBB = new long[teamNum];	
	long[] teamBB = new long[teamNum];
	
	long[] wallsBB = new long[teamNum];
	
	long[] cardinalsBB = new long[teamNum];
	long[] ordinalsBB = new long[teamNum];
	
	List<long[]> pieceBBList;
	
	//king and sliders
	long[] pinnersBB = new long[teamNum];
	long[] pinnedBB = new long[teamNum];
	long[] interfereBB = new long[teamNum];
	long[] slideThreatsBB = new long[teamNum];
	
	//misc
	long[] notTeamBB = new long[teamNum];
	long empty;
	
	//aggregate moves and threats
	long[] threatenBB = new long[teamNum];
	long[] kThreatsBB = new long[teamNum];
	long[] allPMBB = new long[teamNum];
	
	//special position conditions
	long kingMoved;
	long rookMoved;
	long[] epBB = new long[teamNum];
	
	//checks and win conditions
	int[] check = new int[teamNum];
	int teamWon;
	
	static long row1 = -72057594037927936L;
	static long row2 = 71776119061217280L;
	static long row4 = 1095216660480L;
	static long row5 = 4278190080L;
	static long row7 = 65280L;
	static long row8 = 255L;
	static long colA = 72340172838076673L;
	static long colB = 144680345676153346L;
	static long colG = 4629771061636907072L;
	static long colH = -9187201950435737472L;
	
	static long kingMoves = 460039L;
	static long knightMoves = 43234889994L;
	
	static long rowMasks[] = {
			255L, 65280L, 16711680L, 4278190080L, 1095216660480L, 280375465082880L, 71776119061217280L, -72057594037927936L
	};
	
	static long colMasks[] = {
			72340172838076673L, 144680345676153346L, 289360691352306692L, 578721382704613384L,
			1157442765409226768L, 2314885530818453536L, 4629771061636907072L, -9187201950435737472L
	};
	
	static long bltrMasks[] = {
		0x1L, 0x102L, 0x10204L, 0x1020408L, 0x102040810L, 0x10204081020L, 0x1020408102040L,
		0x102040810204080L, 0x204081020408000L, 0x408102040800000L, 0x810204080000000L,
		0x1020408000000000L, 0x2040800000000000L, 0x4080000000000000L, 0x8000000000000000L
	};
	    static long tlbrMasks[] = {
		0x80L, 0x8040L, 0x804020L, 0x80402010L, 0x8040201008L, 0x804020100804L, 0x80402010080402L,
		0x8040201008040201L, 0x4020100804020100L, 0x2010080402010000L, 0x1008040201000000L,
		0x804020100000000L, 0x402010000000000L, 0x201000000000000L, 0x100000000000000L
	};
	
	List<List<Pieces>> pieceList;
	List<List<Pieces>> cardinalList;
	List<List<Pieces>> ordinalList;
	List<Pieces> castleableList;
	List<List<Pieces>> promoteableList;
	List<Pieces> kingList;
	
	public Board() {                                                   //initializes all of the bitboards
		
		pieceBBList = new ArrayList<long[]>();
		pieceList = new ArrayList<List<Pieces>>();
		cardinalList = new ArrayList<List<Pieces>>();
		ordinalList = new ArrayList<List<Pieces>>();
		castleableList = new ArrayList<Pieces>();
		promoteableList = new ArrayList<List<Pieces>>();
		kingList = new ArrayList<Pieces>();
		
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
		
	}
	
	public void standardChess() {                                           //sets up the positions of a standard chess game
		
		String standardChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","bk","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wq","wK","wb","wk","wr"}};
		
		//arrayToBitboards(standardChessBoard);
		setStuff(standardChessBoard);
	}
	
	public void chessPlusPlus() {
		String standardChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","bk","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wW","  ","wW","  ","wW","  ","wW","  "},
				{"  ","bW","  ","bW","  ","bW","  ","bW"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wq","wK","wb","wk","wr"}};

		//arrayToBitboards(standardChessBoard);
	}
	
	public void setStuff(String[][] chessBoard) {
		
		pieceList = PieceInit.pieceInit(chessBoard);
		
		List<Pieces> cardinal;
		List<Pieces> ordinal;
		List<Pieces> castleable;
		List<Pieces> promoteable;
		List<Pieces> kings;
		
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
	
	public void arrayToBitboards(String[][] chessBoard) {                   //turns an array representation of a chessboard into bitboards for each piece
		
        String Binary;
        for (int i = 0; i < 64; i++) {
            Binary="0000000000000000000000000000000000000000000000000000000000000000";
            Binary=Binary.substring(i+1)+"1"+Binary.substring(0, i);
            switch (chessBoard[i/8][i%8]) {
                case "wp": pawnsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wk": knightsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wb": bishopsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wr": rooksBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wq": queensBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wK": kingBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wW": wallsBB[0] += convertStringToBitboard(Binary);
            		break;
                case "bp": pawnsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bk": knightsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bb": bishopsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "br": rooksBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bq": queensBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bK": kingBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bW": wallsBB[1] += convertStringToBitboard(Binary);
                	break;
            }
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
			
			for(Pieces piece : pieceList.get(i)) {
				teamBB[i] |= piece.piece;
			}
			
			for(Pieces piece : cardinalList.get(i)) {
				cardinalsBB[i] |= piece.piece;
			}

			for(Pieces piece : ordinalList.get(i)) {
				ordinalsBB[i] |= piece.piece;
			}
			
			for(Pieces piece : promoteableList.get(i)) {
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
			
			for(Pieces piece : pieceList.get(i)) {
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
			
			for(Pieces piece : pieceList.get(i)) {
				currentThreat = piece.getAllPM(this, true);
				threatenBB[i] |= currentThreat;
				if((currentThreat&kingBB[noti]) != 0) {
					kThreatsBB[noti] |= piece.threatPos(this, kingBB[noti]);
				}
			}
		}
	}
	
	public void displayArray() {
		
		String displayChessBoard[][] = {
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "}};
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//white pieces
				if(((kingBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wK";
				else if(((queensBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wq";
				else if(((rooksBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wr";
				else if(((knightsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wk";
				else if(((bishopsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wb";
				else if(((pawnsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wp";
				
				//black pieces
				else if(((kingBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bK";
				else if(((queensBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bq";
				else if(((rooksBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "br";
				else if(((knightsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bk";
				else if(((bishopsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bb";
				else if(((pawnsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bp";
			}
		}
		
		int rows = 9;
		
		for(int i = 0; i < 8; i++) {
			System.out.print(--rows + " ");
			for(int j = 0; j < 8; j++) {
				System.out.print("[" + displayChessBoard[i][j] + "]");
			}
			System.out.println();
		}
		System.out.println("   a   b   c   d   e   f   g   h");
		System.out.println();
	}
	
	public void displayArray(long bitboard) {
		
		String displayChessBoard[][] = {
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "}};
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//white pieces
				if(((kingBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wK";
				else if(((queensBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wq";
				else if(((rooksBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wr";
				else if(((knightsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wk";
				else if(((bishopsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wb";
				else if(((pawnsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wp";
				
				//black pieces
				else if(((kingBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bK";
				else if(((queensBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bq";
				else if(((rooksBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "br";
				else if(((knightsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bk";
				else if(((bishopsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bb";
				else if(((pawnsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bp";
			}
		}
		
		int rows = 9;
		
		for(int i = 0; i < 8; i++) {
			System.out.print(--rows + " ");
			for(int j = 0; j < 8; j++) {
				if(((bitboard>>(i*8)+j)&1) == 0)
					System.out.print("[" + displayChessBoard[i][j] + "]");
				else
					System.out.print("<" + displayChessBoard[i][j] + ">");
			}
			System.out.println();
		}
		System.out.println("   a   b   c   d   e   f   g   h");
		System.out.println();
	}
	
	public void removePiece(long coord) {
		
		for(int i = 0; i < teamNum; i++) {
			if((teamBB[i]&coord) != 0) {
				for(Pieces piece : pieceList.get(i)) {
					if((piece.piece&coord) != 0) {
						piece.piece &= ~coord;
						return;
					}
				}
			}
		}
	}
	
	public void displayBitboard(long bitBoard) {
		
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
			for(Pieces piece : pieceList.get(team)) {	
				if((coord1&piece.piece) != 0) {
					valid = piece.movePiece(this, coord1, coord2, checked);
					
					if(valid) {
						if(piece.name == "Pawn") {
							enPassant(coord1, coord2, team);
						}
						else if(piece.name == "King") {
							castle(coord1, coord2, team);
						}
						
						epBB[Math.abs(team-1)] = 0;
						currentState();
						break;
					}
				}
			}
		}
		
		return valid;
	}
	
	public long showMoves(String pos) {
		
		long coord = convertToCoord(pos);
		long moves = 0L;
		
		for(int i = 0; i < teamNum; i++) {
			if((coord&teamBB[i]) != 0) {
				for(Pieces piece : pieceList.get(i)) {
					if((coord&piece.piece) != 0)
						return piece.possibleMoves(this, coord, true);
				}
			}
		}
		return 0L;
	}
	
}
