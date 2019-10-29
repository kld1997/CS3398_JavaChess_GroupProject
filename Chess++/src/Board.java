//Roy Grady

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Board {
	
	int teamNum = 2;
	int pieceNum = 6;
	
	// pieces
	long[] kingBB = new long[teamNum];
	long[] queensBB = new long[teamNum];
	long[] rooksBB = new long[teamNum];
	long[] bishopsBB = new long[teamNum];
	long[] knightsBB = new long[teamNum];
	long[] pawnsBB = new long[teamNum];	
	long[] teamBB = new long[teamNum];
	
	long[][] pieceBBArr = {kingBB, queensBB, rooksBB, bishopsBB, knightsBB, pawnsBB};
	
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
	
	Pawn pawnW = new Pawn(0);
	Pawn pawnB = new Pawn(1);
	
	Knight knightW = new Knight(0);
	Knight knightB = new Knight(1);
	
	Bishop bishopW = new Bishop(0);
	Bishop bishopB = new Bishop(1);
	
	Rook rookW = new Rook(0);
	Rook rookB = new Rook(1);
	
	Queen queenW = new Queen(0);
	Queen queenB = new Queen(1);
	
	King kingW = new King(0);
	King kingB = new King(1);
	
	Pieces[][] pieceArr = {{pawnW, knightW, bishopW, rookW, queenW, kingW}, {pawnB, knightB, bishopB, rookB, queenB, kingB}};
	
	public Board() {                                                   //initializes all of the bitboards
		
		for(int i = 0; i < teamNum; i++) {
			this.kingBB[i] = 0;
			this.queensBB[i] = 0;
			this.rooksBB[i] = 0;
			this.bishopsBB[i] = 0;
			this.knightsBB[i] = 0;
			this.pawnsBB[i] = 0;
			this.teamBB[i] = 0;
			this.notTeamBB[i] = 0;
			
			this.pinnersBB[i] = 0;
			this.pinnedBB[i] = 0;
			this.interfereBB[i] = 0;
			this.slideThreatsBB[i] = 0;
			
			this.threatenBB[i] = 0;
			this.allPMBB[i] = 0;
			this.kThreatsBB[i] = 0;
			this.check[i] = 0;
			this.epBB[i] = 0;
			
			/*this.pieceBBArr[i][0] = this.pawnsBB[i];
			this.pieceBBArr[i][1] = this.knightsBB[i];
			this.pieceBBArr[i][2] = this.bishopsBB[i];
			this.pieceBBArr[i][3] = this.rooksBB[i];
			this.pieceBBArr[i][4] = this.queensBB[i];
			this.pieceBBArr[i][5] = this.kingBB[i];*/
		}
		
		/*pieceArr[0][0] = pawnW;
		pieceArr[0][1] = knightW;
		pieceArr[0][2] = bishopW;
		pieceArr[0][3] = rookW;
		pieceArr[0][4] = queenW;
		pieceArr[0][5] = kingW;
		
		pieceArr[1][0] = pawnB;
		pieceArr[1][1] = knightB;
		pieceArr[1][2] = bishopB;
		pieceArr[1][3] = rookB;
		pieceArr[1][4] = queenB;
		pieceArr[1][5] = kingB;*/

		this.empty = 0;
		
		this.kingMoved = 0x1000000000000010L;
		this.rookMoved = 0x8100000000000081L;
		
		this.teamWon = 2;
		
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
		
		arrayToBitboards(standardChessBoard);
	}
	
	public void arrayToBitboards(String[][] chessBoard) {                   //turns an array representation of a chessboard into bitboards for each piece
		
        String Binary;
        for (int i = 0; i < 64; i++) {
            Binary="0000000000000000000000000000000000000000000000000000000000000000";
            Binary=Binary.substring(i+1)+"1"+Binary.substring(0, i);
            switch (chessBoard[i/8][i%8]) {
                case "wp": this.pawnsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wk": this.knightsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wb": this.bishopsBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wr": this.rooksBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wq": this.queensBB[0] += convertStringToBitboard(Binary);
                    break;
                case "wK": this.kingBB[0] += convertStringToBitboard(Binary);
                    break;
                case "bp": this.pawnsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bk": this.knightsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bb": this.bishopsBB[1] += convertStringToBitboard(Binary);
                    break;
                case "br": this.rooksBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bq": this.queensBB[1] += convertStringToBitboard(Binary);
                    break;
                case "bK": this.kingBB[1] += convertStringToBitboard(Binary);
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
		
		check[0] = 0;
		check[1] = 0;
		
		this.teamBB[0] = queensBB[0]|rooksBB[0]|knightsBB[0]|bishopsBB[0]|pawnsBB[0]|kingBB[0];
		this.teamBB[1] = queensBB[1]|rooksBB[1]|knightsBB[1]|bishopsBB[1]|pawnsBB[1]|kingBB[1];
		
		this.notTeamBB[0] = ~(teamBB[0]);
		this.notTeamBB[1] = ~(teamBB[1]);
		
		this.empty = ~(teamBB[0]|teamBB[1]);
		
		pieceMoved();
		
		getThreaten();
		
		//code for making a bitboard of all of the pinnedB and pinning pieces
		Check.getPinned(this);
		
		Check.slideThreats(this, 0);
		Check.slideThreats(this, 1);

		checkmate();
		
		if(((this.pawnsBB[0]&row8)|(this.pawnsBB[1]&row1)) != 0) {
			PawnPromote.pawnPromotion(this);
		}

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
			
			for(int j = 0; j < pieceNum; j++) {
				allPMBB[i] |= pieceArr[i][j].getAllPM(this, false);
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
			
			for(int j = 0; j < pieceNum; j++) {
				currentThreat = pieceArr[i][j].getAllPM(this, true);
				threatenBB[i] |= currentThreat;
				if((currentThreat&this.kingBB[noti]) != 0) {
					kThreatsBB[noti] |= pieceArr[i][j].threatPos(this, this.kingBB[noti]);
				}
			}
		}
		/*whiteThreaten = 
				pawnW.getAllPM(this, true)|knightW.getAllPM(this, true)|bishopW.getAllPM(this, true)|
				rookW.getAllPM(this, true)|queenW.getAllPM(this, true)|kingW.getAllPM(this, true);
		
		if((whiteThreaten&this.kingBB[1]) != 0) {
			bKThreats = 0;
			if((this.kingBB[1]&pawnW.getAllPM(this, true)) != 0) {
				bKThreats |= pawnW.threatPos(this, this.kingBB[1]);
			}
			if((this.kingBB[1]&knightW.getAllPM(this, true)) != 0) {
				bKThreats |= knightW.threatPos(this, this.kingBB[1]);
			}
			if((this.kingBB[1]&bishopW.getAllPM(this, true)) != 0) {
				bKThreats |= bishopW.threatPos(this, this.kingBB[1]);
			}
			if((this.kingBB[1]&rookW.getAllPM(this, true)) != 0) {
				bKThreats |= rookW.threatPos(this, this.kingBB[1]);
			}
			if((this.kingBB[1]&queenW.getAllPM(this, true)) != 0) {
				bKThreats |= queenW.threatPos(this, this.kingBB[1]);
			}
			if((this.kingBB[1]&kingW.getAllPM(this, true)) != 0) {
				bKThreats |= kingW.threatPos(this, this.kingBB[1]);
			}
		}
		else
			blackCheck = 0;
		
		blackThreaten = 
				pawnB.getAllPM(this, true)|knightB.getAllPM(this, true)|bishopB.getAllPM(this, true)|
				rookB.getAllPM(this, true)|queenB.getAllPM(this, true)|kingB.getAllPM(this, true);

		if((blackThreaten&this.kingBB[0]) != 0) {
			wKThreats = 0;
			if((this.kingBB[0]&pawnB.getAllPM(this, true)) != 0) {
				wKThreats |= pawnB.threatPos(this, this.kingBB[0]);
			}
			if((this.kingBB[0]&knightB.getAllPM(this, true)) != 0) {
				wKThreats |= knightB.threatPos(this, this.kingBB[0]);
			}
			if((this.kingBB[0]&bishopB.getAllPM(this, true)) != 0) {
				wKThreats |= bishopB.threatPos(this, this.kingBB[0]);
			}
			if((this.kingBB[0]&rookB.getAllPM(this, true)) != 0) {
				wKThreats |= rookB.threatPos(this, this.kingBB[0]);
			}
			if((this.kingBB[0]&queenB.getAllPM(this, true)) != 0) {
				wKThreats |= queenB.threatPos(this, this.kingBB[0]);
			}
			if((this.kingBB[0]&kingB.getAllPM(this, true)) != 0) {
				wKThreats |= kingB.threatPos(this, this.kingBB[0]);
			}
		}
		else
			whiteCheck = 0;*/
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
				if(((this.kingBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wK";
				else if(((this.queensBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wq";
				else if(((this.rooksBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wr";
				else if(((this.knightsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wk";
				else if(((this.bishopsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wb";
				else if(((this.pawnsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wp";
				
				//black pieces
				else if(((this.kingBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bK";
				else if(((this.queensBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bq";
				else if(((this.rooksBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "br";
				else if(((this.knightsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bk";
				else if(((this.bishopsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bb";
				else if(((this.pawnsBB[1]>>(i*8)+j)&1) == 1)
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
				if(((this.kingBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wK";
				else if(((this.queensBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wq";
				else if(((this.rooksBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wr";
				else if(((this.knightsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wk";
				else if(((this.bishopsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wb";
				else if(((this.pawnsBB[0]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "wp";
				
				//black pieces
				else if(((this.kingBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bK";
				else if(((this.queensBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bq";
				else if(((this.rooksBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "br";
				else if(((this.knightsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bk";
				else if(((this.bishopsBB[1]>>(i*8)+j)&1) == 1)
					displayChessBoard[i][j] = "bb";
				else if(((this.pawnsBB[1]>>(i*8)+j)&1) == 1)
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
		/*if((this.teamBB[0]&coord) != 0) {
			if((this.pawnsBB[0]&coord) != 0) {
				this.pawnsBB[0] = this.pawnsBB[0]^coord;
			}
			else if((this.bishopsBB[0]&coord) != 0) {
				this.bishopsBB[0] = this.bishopsBB[0]^coord;
			}
			else if((this.knightsBB[0]&coord) != 0) {
				this.knightsBB[0] = this.knightsBB[0]^coord;
			}
			else if((this.rooksBB[0]&coord) != 0) {
				this.rooksBB[0] = this.rooksBB[0]^coord;
			}
			else if((this.queensBB[0]&coord) != 0) {
				this.queensBB[0] = this.queensBB[0]^coord;
			}
		}
		else if((this.teamBB[1]&coord) != 0) {
			if((this.pawnsBB[1]&coord) != 0) {
				this.pawnsBB[1] = this.pawnsBB[1]^coord;
			}
			else if((this.bishopsBB[1]&coord) != 0) {
				this.bishopsBB[1] = this.bishopsBB[1]^coord;
			}
			else if((this.knightsBB[1]&coord) != 0) {
				this.knightsBB[1] = this.knightsBB[1]^coord;
			}
			else if((this.rooksBB[1]&coord) != 0) {
				this.rooksBB[1] = this.rooksBB[1]^coord;
			}
			else if((this.queensBB[1]&coord) != 0) {
				this.queensBB[1] = this.queensBB[1]^coord;
			}
		}*/
		for(int i = 0; i < teamNum; i++) {
			if((this.teamBB[i]&coord) != 0) {
				for(int j = 0; j < pieceNum; j++) {
					if((pieceBBArr[j][i]&coord) != 0) {
						pieceBBArr[j][i] ^= coord;
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
	
	public boolean makeMove(int team, String pos, boolean checked) {
		
		long coord1 = convertToCoord(pos.substring(0,2));
		long coord2 = convertToCoord(pos.substring(2,4));
		
		boolean valid = false;
		
		if(team == 0 && (coord1&this.teamBB[0]) != 0) {
			if((coord1&this.pawnsBB[0]) != 0) {
				valid = pawnW.movePiece(this, coord1, coord2, checked);
				if(valid) {
					this.pawnsBB[0] = pawnW.piece;
					
					if(coord2 == coord1>>16)
						epBB[0] |= coord1>>8;
					else if((coord2&epBB[1]) != 0)
						removePiece(coord2<<8); 
				}
			}
			else if((coord1&this.knightsBB[0]) != 0) {
				valid = knightW.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.knightsBB[0] = knightW.piece;
			}
			else if((coord1&this.bishopsBB[0]) != 0) {
				valid = bishopW.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.bishopsBB[0] = bishopW.piece;
			}
			else if((coord1&this.rooksBB[0]) != 0) {
				valid = rookW.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.rooksBB[0] = rookW.piece;
			}
			else if((coord1&this.queensBB[0]) != 0) {
				valid = queenW.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.queensBB[0] = queenW.piece;
			}
			else if((coord1&this.kingBB[0]) != 0) {
				valid = kingW.movePiece(this, coord1, coord2, checked);
				if(valid) {
					this.kingBB[0] = kingW.piece;
					
					if(coord2 == coord1<<2) {
						this.rooksBB[0] ^= coord2>>1|(this.rooksBB[0]&(this.rookMoved&~(coord1-1)));
					}
					else if(coord2 == coord1>>2) {
						this.rooksBB[0] ^= coord2<<1|(this.rooksBB[0]&(this.rookMoved&(coord1-1)));
					}
				}
			}
			
			if(valid)
				epBB[1] = 0;
		}
		else if(team == 1 && (coord1&this.teamBB[1]) != 0) {
			if((coord1&this.pawnsBB[1]) != 0) {
				valid = pawnB.movePiece(this, coord1, coord2, checked);
				if(valid) {
					this.pawnsBB[1] = pawnB.piece;
					
					if(coord2 == coord1<<16)
						epBB[1] |= coord1<<8;
					else if((coord2&epBB[0]) != 0)
						removePiece(coord2>>8);
				}
			}
			else if((coord1&this.knightsBB[1]) != 0) {
				valid = knightB.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.knightsBB[1] = knightB.piece;
			}
			else if((coord1&this.bishopsBB[1]) != 0) {
				valid = bishopB.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.bishopsBB[1] = bishopB.piece;
			}
			else if((coord1&this.rooksBB[1]) != 0) {
				valid = rookB.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.rooksBB[1] = rookB.piece;
			}
			else if((coord1&this.queensBB[1]) != 0) {
				valid = queenB.movePiece(this, coord1, coord2, checked);
				if(valid)
					this.queensBB[1] = queenB.piece;
			}
			else if((coord1&this.kingBB[1]) != 0) {
				valid = kingB.movePiece(this, coord1, coord2, checked);
				if(valid) {
					this.kingBB[1] = kingB.piece;
					
					if(coord2 == coord1<<2) {
						this.rooksBB[1] ^= coord2>>1|(this.rooksBB[1]&(this.rookMoved&~(coord1-1)));
					}
					else if(coord2 == coord1>>2) {
						this.rooksBB[1] ^= coord2<<1|(this.rooksBB[1]&(this.rookMoved&(coord1-1)));
					}
				}
			}
			
			if(valid)
				epBB[0] = 0;
		}
		if(valid)
			currentState();
		
		return valid;
	}
	
	public long showMoves(String pos) {
		
		long coord = convertToCoord(pos);
		long moves = 0L;
		
		if((coord&(this.teamBB[0]|this.kingBB[0])) != 0) {
			if((coord&this.pawnsBB[0]) != 0) {
				moves = pawnW.possibleMoves(this, coord, true);
			}
			else if((coord&this.knightsBB[0]) != 0) {
				moves = knightW.possibleMoves(this, coord, true);
			}
			else if((coord&this.bishopsBB[0]) != 0) {
				moves = bishopW.possibleMoves(this, coord, true);
			}
			else if((coord&this.rooksBB[0]) != 0) {
				moves = rookW.possibleMoves(this, coord, true);
			}
			else if((coord&this.queensBB[0]) != 0) {
				moves = queenW.possibleMoves(this, coord, true);
			}
			else if((coord&this.kingBB[0]) != 0) {
				moves = kingW.possibleMoves(this, coord, true);
			}
		}
		else if((coord&(this.teamBB[1]|this.kingBB[1])) != 0) {
			if((coord&this.pawnsBB[1]) != 0) {
				moves = pawnB.possibleMoves(this, coord, true);
			}
			else if((coord&this.knightsBB[1]) != 0) {
				moves = knightB.possibleMoves(this, coord, true);
			}
			else if((coord&this.bishopsBB[1]) != 0) {
				moves = bishopB.possibleMoves(this, coord, true);
			}
			else if((coord&this.rooksBB[1]) != 0) {
				moves = rookB.possibleMoves(this, coord, true);
			}
			else if((coord&this.queensBB[1]) != 0) {
				moves = queenB.possibleMoves(this, coord, true);
			}
			else if((coord&this.kingBB[1]) != 0) {
				moves = kingB.possibleMoves(this, coord, true);
			}
		}
		
		return moves;
	}
	
	public long showAllMoves(String type) {
		
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
	}
	
}
