import java.awt.*;
public interface Piece
{
    public long possibleMoves(Board board, int x, int y);
    public void movePiece(Board board, int x1, int y1, int x2, int y2);
}
