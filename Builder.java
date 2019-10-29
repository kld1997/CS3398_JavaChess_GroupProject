public class Builder extends Pieces
{
    public Builder(int t)
    {
        super(t);
        value = 3;
    }
    public long pieceType(ChessPPBoard board) {
        return board.builderBB[team];
    }

    @Override
    public long pseudoMoves(long occ, long coord) {
        return Moves.DX(occ, coord);
    }
}
