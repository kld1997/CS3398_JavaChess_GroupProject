package Options;

import Engine.Board;

public class Options {
	
	boolean timer;
	int[] time = new int[Board.teamNum];
	int[] timeInc = new int[Board.teamNum];
	boolean highlight;
	boolean online;
	String mode;
	String[][] board = new String[8][8];
	
	public Options() {
		
		defaultSet();
	}
	
	public boolean getTimer() { return timer; }
	public void setTimer(boolean t) { timer = t; }
	
	public int[] getTime() { return time; }
	public void setTime(int[] t) { time = t; }
	
	public int[] getTimeInc() { return timeInc; }
	public void setTimeInc(int[] i) { timeInc = i; }
	
	public boolean getHighlight() { return highlight; }
	public void setHighlight(boolean h) { highlight = h; }
	
	public boolean getOnline() { return online; }
	public void setOnline(boolean o) { online = o; }
	
	public String getMode() { return mode; }
	public void setMode(String m) { mode = m; }
	
	public String[][] getBoard() { return board; }
	public void setBoard(String[][] b) { board = b; }
	
	public void defaultSet() {
		
		timer = false;
		highlight = true;
		online = false;
		mode = "STANDARD";
		board = BoardTypes.standardChessBoard();
	}
	
	public void bulletSet() {
		
		timer = true;		
		highlight = true;
		online = false;
		mode = "STANDARD";
		board = BoardTypes.standardChessBoard();
		
		for(int i = 0; i < Board.teamNum; i++) {
			time[i] = 60;
			timeInc[i] = 1;
		}
	}
}