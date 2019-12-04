package Pieces;

import Engine.*;
import Visuals.*;

public class Wall extends Piece
{
    public Wall(int t)
    {
        super(t);
        value = 0;
        name = "Wall";
        ID = 'W';
        iconNum = 6;
        image = Images.pieces[Math.abs(team-1)][iconNum];
    }

    public long pseudoMoves(long occ, long coord){return 0;}
}
