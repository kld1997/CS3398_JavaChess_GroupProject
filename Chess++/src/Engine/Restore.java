package Engine;
import java.io.*;

public class Restore implements Serializable{

	public final int team;
	public final char pid;
	public final long coord;

	public Restore(int t, char p, long c) {

		this.team = t;
		this.pid = p;
		this.coord = c;
	}
}
