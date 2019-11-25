package Pieces;
import Pieces.PieceTypes.Ordinal;
import Engine.*;
import Visuals.*;
public class Builder extends Piece implements Ordinal
{
    public Builder(int t)
    {
        super(t);
        name = "Builder";
        ID = '5';
        iconNum = 4;
        image = Images.pieces[Math.abs(team-1)][iconNum];
    }
    public long pseudoMoves(long occ, long coord) {
        return Moves.DX(occ, coord);
    }
    public void getAllPM(Board board) {

        moveList.clear();

        update(board);

        long allMoves;
        long captures;
        long temp = piece;
        long coord = 0L;
        int p = 0;
        int t = 0;

        while(temp != 0) {
            p = Long.numberOfTrailingZeros(temp);
            coord = 1L<<p;
            temp &= ~coord;

            allMoves = possibleMoves(board, coord, false);
            captures = allMoves&enemyPieces;
            allMoves &= ~captures;

            while(allMoves != 0) {
                t = Long.numberOfTrailingZeros(allMoves);
                moveList.add(new Move(p, t, 7, ID));
                allMoves &= allMoves - 1;
            }
            while(captures != 0) {
                t = Long.numberOfTrailingZeros(captures);
                moveList.add(new Move(p, t, 7, ID));
                captures &= captures - 1;
            }
        }
    }
}
