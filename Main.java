//Roy Grady
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Board chess = new Board();
        chess.standardChess();
        
        chess.displayArray();
        
        //chess.displayBitboard(Board.kingMoves);
        //System.out.println(chess.convertToCoord("h1"));
  
        String line = "";
        int action = -1;
        int teamSwitch = 0;
        String coord = "1111";
        String type = "";
        Scanner scan = new Scanner(System.in);
        
        while(action != 0) {
        	if(teamSwitch == 0)
        		System.out.println("white's turn");
        	else
        		System.out.println("black's turn");
        	System.out.println("type action(1:show possible moves, 2:move, 3:show all possible moves, 0:exit): ");
        	try {
        		line = scan.nextLine();
        		if(line.charAt(0) > 72)
        			action = -1;
        		else
        			action = Integer.parseInt(line);
        	} catch(Exception e) {}
        	if(action == 1) {
        		System.out.println("enter coordinates(e.g. a2): ");
        		coord = "1111";
            	coord = scan.nextLine() + coord;
            	chess.displayArray(chess.showMoves(coord));
        	}
        	if(action == 2) {
        		System.out.println("enter coordinates(e.g. b1c3): ");
        		coord = "1111";
            	coord = scan.nextLine() + coord;
            	chess.makeMove(teamSwitch, coord, false);
            	chess.displayArray();
            	teamSwitch = Math.abs(teamSwitch - 1);
        	}
        	if(action == 3) {
        		System.out.println("enter type(e.g. wp): ");
            	type = scan.nextLine();
            	chess.displayArray(chess.showAllMoves(type));
        	}
        	
        	System.out.println();
        }
        scan.close();
    }
}
