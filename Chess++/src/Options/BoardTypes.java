package Options;

public class BoardTypes {
	
	static public String[][] standardChessBoard() {
		
		String standardChessBoard[][] = {
				{"br","bk","  ","bq","bK","bb","bk","br"},
				{"bp","br","bp","bp","bp","bp","bp","bp"},
				{"wp","  ","  ","  ","  ","  ","  ","  "},
				{"  ","wp","  ","  ","  ","  ","  ","  "},
				{"  ","  ","bp","  ","  ","  ","  ","  "},
				{"  ","  ","  ","  ","  ","  ","  ","  "},
				{"wp","wp","wp","wp","wp","wp","wp","wp"},
				{"wr","wk","wb","wq","wK","wb","wk","wr"}};
		
		return standardChessBoard;
	}
}