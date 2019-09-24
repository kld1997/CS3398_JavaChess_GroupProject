//Roy Grady

import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Board {
	
	//squares of the board
	long square;
	
	//white pieces
	long whiteKing;
	long whiteQueens;
	long whiteRooks;
	long whiteBishops;
	long whiteKnights;
	long whitePawns;	
	long whitePieces;
	
	//black pieces
	long blackKing;
	long blackQueens;
	long blackRooks;
	long blackBishops;
	long blackKnights;
	long blackPawns;	
	long blackPieces;
	
	private void init() {                                                   //initializes all of the bitboards
		
		this.square = 0;
		
		this.whiteKing = 0;
		this.whiteQueens = 0;
		this.whiteRooks = 0;
		this.whiteBishops = 0;
		this.whiteKnights = 0;
		this.whitePawns = 0;
		this.whitePieces = 0;
		
		this.blackKing = 0;
		this.blackQueens = 0;
		this.blackRooks = 0;
		this.blackBishops = 0;
		this.blackKnights = 0;
		this.blackPawns = 0;
		this.blackPieces = 0;
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
				{"wr","wk","ww","wq","wK","ww","wk","wr"}};
		
		arrayToBitboards(standardChessBoard);
	}
	
	public void arrayToBitboards(String[][] chessBoard) {                   //turns an array representation of a chessboard into bitboards for each piece
		
        String Binary;
        for (int i = 0; i < 64; i++) {
            Binary="0000000000000000000000000000000000000000000000000000000000000000";
            Binary=Binary.substring(i+1)+"1"+Binary.substring(0, i);
            switch (chessBoard[i/8][i%8]) {
                case "wp": this.whitePawns += convertStringToBitboard(Binary);
                    break;
                case "wk": this.whiteKnights += convertStringToBitboard(Binary);
                    break;
                case "wb": this.whiteBishops += convertStringToBitboard(Binary);
                    break;
                case "wr": this.whiteRooks += convertStringToBitboard(Binary);
                    break;
                case "wq": this.whiteQueens += convertStringToBitboard(Binary);
                    break;
                case "wK": this.whiteKing += convertStringToBitboard(Binary);
                    break;
                case "bp": this.blackPawns += convertStringToBitboard(Binary);
                    break;
                case "bk": this.blackKnights += convertStringToBitboard(Binary);
                    break;
                case "bb": this.blackBishops += convertStringToBitboard(Binary);
                    break;
                case "br": this.blackRooks += convertStringToBitboard(Binary);
                    break;
                case "bq": this.blackQueens += convertStringToBitboard(Binary);
                    break;
                case "bK": this.blackKing += convertStringToBitboard(Binary);
                    break;
            }
        }
        
    }
	
	public long convertStringToBitboard(String Binary) {                    //converts the string of binary numbers into actual binary fit for bitboards
		
        if (Binary.charAt(0)=='0') {                                        //not negative
            return Long.parseLong(Binary, 2);
        } else {
            return Long.parseLong("1"+Binary.substring(2), 2)*2;
        }
    }
	
}