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
        
        
        int action = -1;
        int teamSwitch = 0;
        String coord;
        Scanner scan = new Scanner(System.in);
        
        while(action != 0) {
        	if(teamSwitch == 0)
        		System.out.println("white's turn");
        	else
        		System.out.println("black's turn");
        	System.out.println("type action(1:show possible moves, 2:move, 0:exit): ");
        	action = Integer.parseInt(scan.nextLine());
        	if(action == 1) {
        		System.out.println("enter coordinates(xy): ");
            	coord = scan.nextLine();
            	if(teamSwitch == 0)
            		chess.displayArray(pawnw.possibleMoves(chess, Integer.parseInt(coord.substring(0,1)), Integer.parseInt(coord.substring(1,2))));
            	else
            		chess.displayArray(pawnb.possibleMoves(chess, Integer.parseInt(coord.substring(0,1)), Integer.parseInt(coord.substring(1,2))));
        	}
        	if(action == 2) {
        		System.out.println("enter coordinates(x1y1x2y2): ");
            	coord = scan.nextLine();
            	if(teamSwitch == 0)
            		pawnw.movePiece(chess, Integer.parseInt(coord.substring(0,1)), Integer.parseInt(coord.substring(1,2)), Integer.parseInt(coord.substring(2,3)), Integer.parseInt(coord.substring(3,4)));
            	else
            		pawnb.movePiece(chess, Integer.parseInt(coord.substring(0,1)), Integer.parseInt(coord.substring(1,2)), Integer.parseInt(coord.substring(2,3)), Integer.parseInt(coord.substring(3,4)));
            	chess.displayArray();
        	}
        	
        	System.out.println();
        	teamSwitch = Math.abs(teamSwitch - 1);
        }
        scan.close();
    }
}
