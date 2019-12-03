package GUI;
import java.io.*;

public class Coordinate implements Serializable
{
    private int intCoordx, intCoordy;
    private String stringCoordx, stringCoordy;
    public Coordinate()
    {
        intCoordx = -1;
        intCoordy = -1;
        stringCoordx = "";
        stringCoordy = "";
    }
    public Coordinate(int x, int y)
    {
        intCoordx = x;
        intCoordy = y;
        stringCoordx = 8-x + "";
        stringCoordy = (char)(y+97) + "";
    }
    public Coordinate(String x)
    {
        //intCoordx = ;
        //intCoordy = ;
        stringCoordx = x.charAt(1) + "";
        stringCoordy = x.charAt(0) + "";
    }
    public int getIntCoordx() { return intCoordx; }
    public int getIntCoordy() { return intCoordy; }
    public String toString()
    {
        return stringCoordy + stringCoordx;
    }
}
