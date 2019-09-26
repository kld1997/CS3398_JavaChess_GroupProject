//Roy Grady, preliminary pawn version
public class Pawn implements Piece
{
public void movePiece(Board board, int x, int y) {
		
		board.currentState();
		
		int action = 0;
		long change = 0;
		long coord = (long)1<<((y*8)+x);
		
		if((board.whitePawns&coord) == coord) {	
			if(action == 0) {
				change = coord|(coord>>8);
				board.whitePawns = board.whitePawns^change;
			}
			else if(action == 1) {
				if(((coord>>9)&board.blackPieces) == (coord>>9)) {
					change = coord|(coord>>9);
					board.removePiece(coord>>9);	
					board.whitePawns = board.whitePawns^change;
				}
			}
			else if(action == 2) {
				if(((coord>>7)&board.blackPieces) == (coord>>7)) {
					change = coord|(coord>>7);
					board.removePiece(coord>>7);
					board.whitePawns = board.whitePawns^change;
				}
			}
		}	
		else if((board.blackPawns&coord) == coord) {
			if(action == 0) {
				change = coord|(coord<<8);
				board.blackPawns = board.blackPawns^change;
			}
			else if(action == 1) {
				if(((coord<<9)&board.whitePieces) == (coord<<9)) {
					change = coord|(coord<<9);
					board.removePiece(coord<<9);
					board.blackPawns = board.blackPawns^change;
				}
			}
			else if(action == 2) {
				if(((coord<<7)&board.whitePieces) == (coord<<7)) {
					change = coord|(coord<<7);
					board.removePiece(coord<<7);
					board.blackPawns = board.blackPawns^change;
				}
			}
		}
}
