//Roy Grady
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Board chess = new Board();
        chess.standardChess();
        
        chess.displayArray();
        
        WhitePawn pawnw = new WhitePawn();
        BlackPawn pawnb = new BlackPawn();
        
        //chess.displayBitboard(chess.row8);
        //System.out.println(chess.convertToCoord("h1"));
  
        String line = "";
        int action = -1;
        int teamSwitch = 0;
        String coord = "1111";
        Scanner scan = new Scanner(System.in);
        
        while(action != 0) {
        	if(teamSwitch == 0)
        		System.out.println("white's turn");
        	else
        		System.out.println("black's turn");
        	System.out.println("type action(1:show possible moves, 2:move, 3:show all possible moves, 0:exit): ");
        	line = scan.nextLine();
        	if(line.charAt(0) > 72)
        		action = 2;
        	else
        		action = Integer.parseInt(line);
        	if(action == 1) {
        		System.out.println("enter coordinates(e.g. a2): ");
        		coord = "1111";
            	coord = scan.nextLine() + coord;
            	if(teamSwitch == 0)
            		chess.displayArray(pawnw.possibleMoves(chess, Board.convertToCoord(coord)));
            	else
            		chess.displayArray(pawnb.possibleMoves(chess, Board.convertToCoord(coord)));
        	}
        	if(action == 2) {
        		System.out.println("enter coordinates(e.g. b1c3): ");
        		coord = "1111";
            	coord = scan.nextLine() + coord;
            	if(teamSwitch == 0)
            		pawnw.movePiece(chess, Board.convertToCoord(coord.substring(0,2)), Board.convertToCoord(coord.substring(2,4)));
            	else
            		pawnb.movePiece(chess, Board.convertToCoord(coord.substring(0,2)), Board.convertToCoord(coord.substring(2,4)));
            	chess.displayArray();
            	teamSwitch = Math.abs(teamSwitch - 1);
        	}
        	if(action == 3) {
            	if(teamSwitch == 0)
            		chess.displayArray(pawnw.getAllPM(chess));
            	else
            		chess.displayArray(pawnb.getAllPM(chess));
        	}
        	
        	System.out.println();
        }
        scan.close();
    }
}
