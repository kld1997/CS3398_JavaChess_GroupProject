package Pieces;

public class Move {

	public int from;
	public int to;
	public int type; //0: normal, 1: capture, 2: EP, 3: promote, 4: capture+promote, 5: castle, 6: distant capture, 7: Build
	public char pid;
	
	public Move(int f, int t) {
		
		this.from = f;
		this.to = t;
	}
	
	public Move(int f, int t, int ty) {
		
		this.from = f;
		this.to = t;
		this.type = ty;
	}
	
	public Move(int f, int t, int ty, char p) {
		
		this.from = f;
		this.to = t;
		this.type = ty;
		this.pid = p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + from;
		result = prime * result + to;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		return true;
	}

}
