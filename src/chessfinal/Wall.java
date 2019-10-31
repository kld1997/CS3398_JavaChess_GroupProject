
public class Wall extends Pieces
{
    public Wall(int t)
    {
        super(t);
        value = 0;
        name = "Wall";
        image = Images.pieces[Math.abs(team-1)][2];
        iconNum = 2;
    }
    /*
    public long pieceType(NewBoard board)
    {
        return board.wallBB[team];
    }
    public long pseudoMoves(long occ, long coord){return 0;}

     */
}
