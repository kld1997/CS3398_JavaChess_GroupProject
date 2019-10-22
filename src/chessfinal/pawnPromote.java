package chessfinal;
class pawnPromote {
	
	public static boolean promotion = false;
	public static long coord = 0L;
	
	public static void pawnPromotion(int team, Board board) {
		
		int p = 0;
		long promote = 0L;
		promote = board.whitePawns&Board.row8;
		
		p = Long.numberOfTrailingZeros(promote);
		coord = 1L<<p;
		
		promotion = true;
	}
	
	public static void promotePawn(int team, long coord, Board board, int pawnID) {
		
		int newP = pawnID;
		
		
		switch(newP) {
		  case 3: //knight
			board.removePiece(coord);
			board.whiteKnights |= coord;
		    break;
		  case 4: //bishop
			board.removePiece(coord);
			board.whiteBishops |= coord;
		    break;
		  case 2: //rook
			board.removePiece(coord);
			board.whiteRooks |= coord;
		    break;
		  case 1: //queen
			board.removePiece(coord);
			board.whiteQueens |= coord;
		    break;
		  default:
		    System.out.println("error");
		}
		promotion = false;
		board.currentState();
	}	
}