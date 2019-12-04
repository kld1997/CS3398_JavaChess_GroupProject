package GUI;

public class GameMove
{
    private Coordinate[] moveString = new Coordinate[2];
    private Coordinate from;
    private Coordinate to;
    private boolean didCapture = false;
    private char ID, captureID;

    public GameMove()
    {
        from = new Coordinate();
        to = new Coordinate();
        moveString[0] = from;
        moveString[1] = to;
        ID = ' ';
    }
    public GameMove(String move, char i, boolean capture, char ci)
    {
        from = new Coordinate(move.substring(0, 2));
        to = new Coordinate(move.substring(2));
        moveString[0] = from;
        moveString[1] = to;
        didCapture = capture;
        captureID = ci;
        ID = i;
    }
    public Coordinate[] getMoveString(){ return moveString; }
    public Coordinate getFrom(){ return from; }
    public Coordinate getTo(){ return to; }
    public boolean getCapture() { return didCapture; }
    public char getCaptureID() { return captureID; }
    public char getID(){ return ID; }
    public String toString()
    {
        return from+ "" + to + "";
    }
}
