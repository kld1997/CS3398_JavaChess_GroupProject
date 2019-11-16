package Engine;

import Pieces.Move;

public class MoveScore {

	public final int score;
	public final Move move;
	
	public MoveScore(int s, Move m) {
		
		this.score = s;
		this.move = m;
	}
}
