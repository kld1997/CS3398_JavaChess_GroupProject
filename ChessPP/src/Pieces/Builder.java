package Pieces;
import Engine.*;
import Visuals.*;
public class Builder extends Piece implements PieceTypes.Ordinal
{
    public Builder(int t)
    {
        super(t);
        value = 0;
        name = "Builder";
        ID = 'B';
        iconNum = 4;
        image = Images.pieces[Math.abs(team-1)][iconNum];
    }
    public long pseudoMoves(long occ, long coord) {
        return Moves.DX(occ, coord);
    }
}
