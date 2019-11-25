package Options;

public class BoardTypes {
	
	static public String[][] standardChessBoard() {
		
		String standardChessBoard[][] = {
				{"br","bk","bb","bq","bK","bb","bk","br"},
				{"bp","bp","bp","bp","bp","bp","bp","bp"},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wj","wK","wb","wk","wr"}};
		
		return standardChessBoard;
	}
}