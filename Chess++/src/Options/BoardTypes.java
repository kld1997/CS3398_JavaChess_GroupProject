package Options;

public class BoardTypes {
	
	static public String[][] standardChessBoard() {
		
		String standardChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","ba","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wa","wb","wq","wK","wb","wk","wr"}};
		
		return standardChessBoard;
	}
}