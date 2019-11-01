
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//Roy Grady
public abstract class Pieces
{

	public int team;
	public int iconNum;
	public String name;
	public int value;
	public long piece;
	public long moves;
	public long enemyPieces;
	public long notAlly;
	public int check;
	public long pinned;
	public boolean threat = false;
	public BufferedImage image;

	public Pieces(int t) {
		this.team = t;
		this.moves = 0;
	}

	public void update(Board board) {

		check = board.check[team];
		enemyPieces = board.teamBB[Math.abs(team-1)];
		notAlly = board.notTeamBB[team];
		pinned = board.pinnedBB[team];
	}

	public void setImage() {
		image = Images.pieces[Math.abs(team)][iconNum];
	}

	public static ArrayList<int[]> bitboardToCoords() {

		ArrayList coords = new ArrayList<int[]>();



		return coords;
	}

	public long possibleMoves(Board board, long coord, boolean update) {

		if(update)
			update(board);

		moves = 0L;
		long occ = ~board.empty;

		if((piece&coord) != 0 && check < 2) {
			moves = pseudoMoves(occ, coord);

			if(!threat)
				moves &= notAlly;

			if(check == 1)
				moves &= Check.checkRestrict(board, team, coord, pinned);
			else if((coord & pinned) != 0)
				moves &= Check.pinRestrict(board, coord, team);
		}

		return moves;
	}

	public long pseudoMoves(long occ, long coord) {

		return 0L;
	}

	public boolean movePiece(Board board, long coord1, long coord2, boolean checked) {

		update(board);

		if(checked == false)
			moves = possibleMoves(board, coord1, false);

		long change = coord1|coord2;

		if((coord2&moves) != 0) {
			if((enemyPieces&coord2) != 0) {
				board.removePiece(coord2);
			}
			piece^= change;
			return true;
		}
		return false;
	}

	public long getAllPM(Board board, boolean threaten) {

		update(board);

		long allMoves = 0L;
		long temp = piece;
		long coord = 0L;
		int p = 0;

		threat = threaten;
		while(temp != 0) {
			p = Long.numberOfTrailingZeros(temp);
			coord = 1L<<p;
			temp &= ~coord;

			allMoves |= possibleMoves(board, coord, false);
		}
		threat = false;

		return allMoves;
	}

	public long threatPos(Board board, long pCoord) {

		update(board);

		long tPos = 0L;
		long unit = piece;
		long coord = 0L;
		int u = 0;

		while(unit != 0) {
			u = Long.numberOfTrailingZeros(unit);
			coord = 1L<<u;
			unit &= ~coord;

			if((possibleMoves(board, coord, false)&pCoord) != 0) {
				tPos |= coord;
				Check.addCheck(board, Math.abs(team-1));
			}
		}

		return tPos;
	}
}
