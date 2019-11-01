public class Wall extends Pieces
{
    public Wall(int t)
    {
        super(t);
        value = 0;
        name = "Wall";
        ID = 'W';
        iconNum = 2;
        image = Images.pieces[Math.abs(team-1)][iconNum];
    }
    /*
    public long pieceType(NewBoard board)
    {
        return board.wallBB[team];
    }
    public long pseudoMoves(long occ, long coord){return 0;}

     */
}
