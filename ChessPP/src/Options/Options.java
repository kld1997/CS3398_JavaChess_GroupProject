package Options;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Engine.Board;

public class Options {
	
	boolean timer;
	int[] time = new int[Board.teamNum];
	int[] timeInc = new int[Board.teamNum];
	boolean highlight;
	boolean online;
	int cpu;
	boolean captureKing;
	ObjectOutputStream output;
	ObjectInputStream input;
	int turn;
	String mode;
	String[][] board = new String[8][8];
	String promote;
	
	public Options() {
		
		defaultSet();
	}
	public Options(String preset) {
		
		if(preset.equals("bullet"))
			bulletSet();
		else if(preset.equals("wall"))
			wallSet();
		else
			defaultSet();
	}
	public Options(boolean bullet, boolean plusPlus)
	{
		customSet(bullet, plusPlus);
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
	
	public String getPromote() { return promote; }
	public void setPromote(String p) { promote = p; }
	
	public ObjectOutputStream getOutput() { return output; }
	public void setOutput(ObjectOutputStream o) { output = o; }
	
	public ObjectInputStream getInput() { return input; }
	public void setInput(ObjectInputStream i) { input = i; }
	
	public int getTurn() { return turn; }
	public void setTurn(int t) { turn = t; }
	
	public int getCPU() { return cpu; }
	public void setCPU(int c) { cpu = c; }
	
	public boolean getCaptureKing() { return captureKing; }
	public void setCaptureKing(boolean c) { captureKing = c; }
	
	public void defaultSet() {
		
		timer = false;
		highlight = true;
		online = false;
		cpu = 0;
		captureKing = false;
		mode = "STANDARD";
		board = BoardTypes.standardChessBoard();
		promote = "kbrq";
	}
	
	public void bulletSet() {
		
		defaultSet();
		
		timer = true;
		
		for(int i = 0; i < Board.teamNum; i++) {
			time[i] = 120;
			timeInc[i] = 1;
		}
	}
	public void customSet(boolean bullet, boolean plusPlus)
	{
		defaultSet();
		if(bullet)
		{
			timer = true;
			for(int i = 0; i < Board.teamNum; i++) {
				time[i] = 120;
				timeInc[i] = 1;
			}
		}
		if(plusPlus)
		{
			String wallChessBoard[][] = {
					{"br","bk","bb","bq","bK","bb","bk","br"},
					{"bp","bp","bp","bp","bp","bp","bp","bp"},
					{"  ","  ","  ","  ","  ","  ","  ","  "},
					{"  ","  ","  ","  ","  ","  ","  ","bB"},
					{"wa","  ","  ","  ","  ","  ","  ","  "},
					{"  ","  ","  ","  ","  ","  ","  ","  "},
					{"wp","wp","wp","wp","wp","wp","wp","wp"},
					{"wr","wk","wb","wq","wK","wb","wk","wr"}};

			board = wallChessBoard;
		}
	}
	public void wallSet() {
		
		defaultSet();
		
		String wallChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","bk","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","bB"},
				{"wa","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wq","wK","wb","wk","wr"}};
		
		board = wallChessBoard;
	}
	
	public int getCPUMissChance() {
		switch(cpu) {
		case 1: return 50;
		case 2: return 75;
		case 3: return 90;
		case 4: return 95;
		default: return 100;
		}
	}
}