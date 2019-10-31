//Roy Grady

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
	
	Pawn pawnW;
	Pawn pawnB;
	
	Wall wallW;
	Wall wallB;
	
	Knight knightW;
	Knight knightB;
	
	Bishop bishopW;
	Bishop bishopB;
	
	Rook rookW;
	Rook rookB;
	
	Queen queenW;
	Queen queenB;
	
	King kingW;
	King kingB;
	
	List<List<Pieces>> pieceList;
	List<List<Pieces>> cardinalList;
	List<List<Pieces>> ordinalList;
	
	public Board() {                                                   //initializes all of the bitboards
		
		pieceBBList = new ArrayList<long[]>();
		pieceList = new ArrayList<List<Pieces>>();
		cardinalList = new ArrayList<List<Pieces>>();
		ordinalList = new ArrayList<List<Pieces>>();
		
		for(int i = 0; i < teamNum; i++) {
			kingBB[i] = 0L;
			queensBB[i] = 0L;
			rooksBB[i] = 0L;
			bishopsBB[i] = 0L;
			knightsBB[i] = 0L;
			pawnsBB[i] = 0L;
			teamBB[i] = 0L;
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
			
			wallsBB[i] = 0L;
			
			cardinalsBB[i] = 0L;
			ordinalsBB[i] = 0L;
		}
		
		empty = 0;
		
		kingMoved = 0x1000000000000010L;
		rookMoved = 0x8100000000000081L;
		
		teamWon = 2;
		
		//currentState();
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
		
		List<Pieces> cardinal1 = new ArrayList<Pieces>();
		List<Pieces> cardinal2 = new ArrayList<Pieces>();
		
		List<Pieces> ordinal1 = new ArrayList<Pieces>();
		List<Pieces> ordinal2 = new ArrayList<Pieces>();
		
		for(int i = 0; i < teamNum; i++) {	
			for(Pieces piece : pieceList.get(i)) {
				if(piece.name.equals("Pawn")) {
					if(i==0)
					pawnW = (Pawn) piece;
				else
					pawnB = (Pawn) piece;
					pawnsBB[i] = piece.piece;
				}
				else if(piece.name.equals("Knight")) {
					if(i==0)
						knightW = (Knight) piece;
					else
						knightB = (Knight) piece;
					knightsBB[i] = piece.piece;
				}
				else if(piece.name.equals("Bishop")) {
					if(i==0) {
						bishopW = (Bishop) piece;
						ordinal1.add(piece);
					}
					else {
						bishopB = (Bishop) piece;
						ordinal2.add(piece);
					}
					bishopsBB[i] = piece.piece;
				}
				else if(piece.name.equals("Rook")) {
					if(i==0) {
						rookW = (Rook) piece;
						cardinal1.add(piece);
					}
					else {
						rookB = (Rook) piece;
						cardinal2.add(piece);
						}
					rooksBB[i] = piece.piece;
				}
				else if(piece.name.equals("Queen")) {
					if(i==0) {
						queenW = (Queen) piece;
						cardinal1.add(piece);
						ordinal1.add(piece);
					}
					else {
						queenB = (Queen) piece;
						cardinal2.add(piece);
						ordinal2.add(piece);
						}
					queensBB[i] = piece.piece;
					
				}
				else if(piece.name.equals("King")) {
					if(i==0)
						kingW = (King) piece;
					else
						kingB = (King) piece;
					kingBB[i] = piece.piece;
				}
			}
		}
		
		cardinalList.add(cardinal1);
		cardinalList.add(cardinal2);
		
		ordinalList.add(ordinal1);
		ordinalList.add(ordinal2);
		
		pieceBBList.add(pawnsBB);
		pieceBBList.add(knightsBB);
		pieceBBList.add(bishopsBB);
		pieceBBList.add(rooksBB);
		pieceBBList.add(queensBB);
		pieceBBList.add(kingBB);
		pieceBBList.add(wallsBB);

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
		
		//check[0] = 0;
		//check[1] = 0;
		kingBB[0] = kingW.piece;
		kingBB[1] = kingB.piece;
		
		for(int i = 0; i < teamNum; i++) {
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
		}

		//teamBB[0] = queensBB[0]|rooksBB[0]|knightsBB[0]|bishopsBB[0]|pawnsBB[0]|kingBB[0]|wallsBB[0];
		//teamBB[1] = queensBB[1]|rooksBB[1]|knightsBB[1]|bishopsBB[1]|pawnsBB[1]|kingBB[1]|wallsBB[1];
		
		notTeamBB[0] = ~(teamBB[0]);
		notTeamBB[1] = ~(teamBB[1]);
		
		empty = ~(teamBB[0]|teamBB[1]);
		
		pieceMoved();
		
		getThreaten();
		
		//code for making a bitboard of all of the pinnedB and pinning pieces
		Check.getPinned(this);
		
		Check.slideThreats(this, 0);
		Check.slideThreats(this, 1);

		checkmate();
		
		if(((pawnsBB[0]&row8)|(pawnsBB[1]&row1)) != 0) {
			PawnPromote.pawnPromotion(this);
		} 
		
		displayBitboard(cardinalsBB[0]);
		System.out.println("ee");
		displayBitboard(cardinalsBB[1]);

	}
	
	public void pieceMoved() {
		
		if(kingMoved != 0 && kingMoved != ((kingBB[0]|kingBB[1])&kingMoved)) {  	//if king moves
			kingMoved &= (kingBB[0]|kingBB[1]);
		}
		if(rookMoved != 0 && rookMoved != ((rooksBB[0]|rooksBB[1])&rookMoved)) {	//if rook moves
			rookMoved &= (rooksBB[0]|rooksBB[1]);
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
			rooksBB[team] ^= coord2>>1|(rooksBB[team]&(rookMoved&~(coord1-1)));
		}
		else if(coord2 == coord1>>2) {
			rooksBB[team] ^= coord2<<1|(rooksBB[team]&(rookMoved&(coord1-1)));
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
		
		/*if(team == 0 && (coord1&teamBB[0]) != 0) {
			if((coord1&pawnsBB[0]) != 0) {
				valid = pawnW.movePiece(this, coord1, coord2, checked);
				if(valid) {
					pawnsBB[0] = pawnW.piece;
					
					if(coord2 == coord1>>16)
						epBB[0] |= coord1>>8;
					else if((coord2&epBB[1]) != 0)
						removePiece(coord2<<8); 
				}
			}
			else if((coord1&knightsBB[0]) != 0) {
				valid = knightW.movePiece(this, coord1, coord2, checked);
				if(valid)
					knightsBB[0] = knightW.piece;
			}
			else if((coord1&bishopsBB[0]) != 0) {
				valid = bishopW.movePiece(this, coord1, coord2, checked);
				if(valid)
					bishopsBB[0] = bishopW.piece;
			}
			else if((coord1&rooksBB[0]) != 0) {
				valid = rookW.movePiece(this, coord1, coord2, checked);
				if(valid)
					rooksBB[0] = rookW.piece;
			}
			else if((coord1&queensBB[0]) != 0) {
				valid = queenW.movePiece(this, coord1, coord2, checked);
				if(valid)
					queensBB[0] = queenW.piece;
			}
			else if((coord1&kingBB[0]) != 0) {
				valid = kingW.movePiece(this, coord1, coord2, checked);
				if(valid) {
					kingBB[0] = kingW.piece;
					
					if(coord2 == coord1<<2) {
						rooksBB[0] ^= coord2>>1|(rooksBB[0]&(rookMoved&~(coord1-1)));
					}
					else if(coord2 == coord1>>2) {
						rooksBB[0] ^= coord2<<1|(rooksBB[0]&(rookMoved&(coord1-1)));
					}
				}
			}
			
			if(valid)
				epBB[1] = 0;
		}
		else if(team == 1 && (coord1&teamBB[1]) != 0) {
			if((coord1&pawnsBB[1]) != 0) {
				valid = pawnB.movePiece(this, coord1, coord2, checked);
				if(valid) {
					pawnsBB[1] = pawnB.piece;
					
					if(coord2 == coord1<<16)
						epBB[1] |= coord1<<8;
					else if((coord2&epBB[0]) != 0)
						removePiece(coord2>>8);
				}
			}
			else if((coord1&knightsBB[1]) != 0) {
				valid = knightB.movePiece(this, coord1, coord2, checked);
				if(valid)
					knightsBB[1] = knightB.piece;
			}
			else if((coord1&bishopsBB[1]) != 0) {
				valid = bishopB.movePiece(this, coord1, coord2, checked);
				if(valid)
					bishopsBB[1] = bishopB.piece;
			}
			else if((coord1&rooksBB[1]) != 0) {
				valid = rookB.movePiece(this, coord1, coord2, checked);
				if(valid)
					rooksBB[1] = rookB.piece;
			}
			else if((coord1&queensBB[1]) != 0) {
				valid = queenB.movePiece(this, coord1, coord2, checked);
				if(valid)
					queensBB[1] = queenB.piece;
			}
			else if((coord1&kingBB[1]) != 0) {
				valid = kingB.movePiece(this, coord1, coord2, checked);
				if(valid) {
					kingBB[1] = kingB.piece;
					
					if(coord2 == coord1<<2) {
						rooksBB[1] ^= coord2>>1|(rooksBB[1]&(rookMoved&~(coord1-1)));
					}
					else if(coord2 == coord1>>2) {
						rooksBB[1] ^= coord2<<1|(rooksBB[1]&(rookMoved&(coord1-1)));
					}
				}
			}
			
			if(valid)
				epBB[0] = 0;
		}
		if(valid)
			currentState();
		
		return valid;*/
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
	
	/*public long showAllMoves(String type) {
		
		long moves = 0L;
		
		switch(type) {
		case "wA":
			moves |= pawnW.getAllPM(this, false);
			moves |= knightW.getAllPM(this, false);
			moves |= bishopW.getAllPM(this, false);
			moves |= rookW.getAllPM(this, false);
			moves |= queenW.getAllPM(this, false);
			moves |= kingW.getAllPM(this, false);
			break;
		case "wp":
			moves = pawnW.getAllPM(this, false);
			break;
		case "wk":
			moves = knightW.getAllPM(this, false);
			break;
		case "wb":
			moves = bishopW.getAllPM(this, false);
			break;
		case "wr":
			moves = rookW.getAllPM(this, false);
			break;
		case "wq":
			moves = queenW.getAllPM(this, false);
			break;
		case "wK":
			moves = kingW.getAllPM(this, false);
			break;
			
		case "bA":
			moves |= pawnB.getAllPM(this, false);
			moves |= knightB.getAllPM(this, false);
			moves |= bishopB.getAllPM(this, false);
			moves |= rookB.getAllPM(this, false);
			moves |= queenB.getAllPM(this, false);
			moves |= kingB.getAllPM(this, false);
			break;
		case "bp":
			moves = pawnB.getAllPM(this, false);
			break;
		case "bk":
			moves = knightB.getAllPM(this, false);
			break;
		case "bb":
			moves = bishopB.getAllPM(this, false);
			break;
		case "br":
			moves = rookB.getAllPM(this, false);
			break;
		case "bq":
			moves = queenB.getAllPM(this, false);
			break;
		case "bK":
			moves = kingB.getAllPM(this, false);
			break;
		default:
			System.out.println("Invalid syntax");
		}
		
		return moves;
	}*/
	
}
