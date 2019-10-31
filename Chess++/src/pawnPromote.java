class PawnPromote {
	
	public static boolean promotion = false;
	public static long coord = 0L;
	
	public static void pawnPromotion(Board board) {
		
		int p = 0;
		long promote = (board.pawnsBB[0]&Board.row8)|(board.pawnsBB[1]&Board.row1);
		p = Long.numberOfTrailingZeros(promote);
		coord = 1L<<p;
		
		promotion = true;
	}
	
	public static void promotePawn(int team, long coord, Board board, int pawnID) {
		
		int newP = pawnID;

		switch(newP) {
		  case 3: //knight
			board.removePiece(coord);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).stream().filter(p -> p instanceof Knight).findFirst().get().piece |= coord;
		    break;
		  case 4: //bishop
			board.removePiece(coord);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).stream().filter(p -> p instanceof Bishop).findFirst().get().piece |= coord;
		    break;
		  case 2: //rook
			board.removePiece(coord);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).stream().filter(p -> p instanceof Rook).findFirst().get().piece |= coord;
		    break;
		  case 1: //queen
			board.removePiece(coord);
			board.pawnsBB[team] &= ~coord;
			board.pieceList.get(team).stream().filter(p -> p instanceof Queen).findFirst().get().piece |= coord;
		    break;
		  default:
		    System.out.println("error");
		}

		promotion = false;
		board.currentState();
	}	
}