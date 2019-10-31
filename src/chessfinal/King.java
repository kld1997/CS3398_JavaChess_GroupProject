
//Roy Grady, king
public class King extends Pieces
{

	public King(int t) {
		super(t);
		name = "King";
		image = Images.pieces[Math.abs(team-1)][0];
		iconNum = 0;
	}

	public long pieceType(Board board) {
		return board.kingBB[team];
	}

	public long possibleMoves(Board board, long coord, boolean update) {

		if(update)
			update(board);

		moves = 0L;
		long slideThreats;
		long threaten;
		long rooks;

		slideThreats = board.slideThreatsBB[team];
		threaten = board.threatenBB[Math.abs(team-1)];
		rooks = board.rooksBB[team];

		if((piece&coord) != 0)
			moves = pseudoMoves(coord);

		if((board.kingMoved&piece) != 0 && check == 0) {	//castling
			rooks &= board.rookMoved;
			long checkSpaces = 0L;
			while(rooks != 0) {
				long rookPos = Long.numberOfTrailingZeros(rooks);
				coord = 1L<<rookPos;

				checkSpaces = Check.obstruct(piece, coord)&~((piece|rooks)^board.empty);

				if(coord-1>piece-1 && checkSpaces == 0) {
					moves |= piece<<2;
				}
				else if(coord-1<piece-1 && checkSpaces == 0) {
					moves |= piece>>2;
				}

				rooks &= rooks - 1;
			}
		}

		moves &= ~slideThreats;

		if(!threat)
			moves &= notAlly&~threaten;

		return moves;
	}

	public long pseudoMoves(long coord) {

		int trail = Long.numberOfTrailingZeros(coord);
		long pm = 0L;

		if(trail == 9) {
			pm = Board.kingMoves;
		}
		else {
			if(trail > 9) {
				pm = Board.kingMoves<<(trail - 9);
			}
			else if(trail < 9) {
				pm = Board.kingMoves>>(9 - trail);
			}

			if((coord&Board.colA) != 0) {
				pm &= ~Board.colH;
			}
			if((coord&Board.colH) != 0) {
				pm &= ~Board.colA;
			}
		}

		return pm;
	}

}
