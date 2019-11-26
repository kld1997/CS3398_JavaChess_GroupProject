package Options;

public class BoardTypes {
	
	static public String[][] standardChessBoard() {
		
		String standardChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","bk","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"bP","  ","  ","  ","  ","  ","  ","ba"},
				{"bj","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","wa","  ","  ","  ","  ","  "},
				{"wP","  ","  ","  ","wj","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wq","wK","wb","wk","wr"}};
		
		return standardChessBoard;
	}
}