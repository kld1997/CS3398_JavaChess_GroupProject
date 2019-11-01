import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChessServer {

	public ServerSocket server;
	public Socket connection;
	public int port;
	public String message = null;
	
	public ChessServer(Board board, MainBoardPanel mbp, int p) {
		
		port = p;
		
	}
	
	 public void startRunning(ChessGui g, Board board, MainBoardPanel mbp) {
 		try {
 			System.out.println("TRY2");
 			server = new ServerSocket(port, 100);
 			while(true) {
 				try {
 			            try {
 			            	System.out.println("TRY3");
 							connection = server.accept();
 							System.out.println("TRY4");
 						} catch (IOException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 			            try {
 			            	g.output = new ObjectOutputStream(connection.getOutputStream());
 			            	g.output.flush();
 							g.input = new ObjectInputStream(connection.getInputStream());
 			            } catch (IOException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 			            readin(g, board, mbp);
 				/*} catch(EOFException eofException) {
 					System.out.println("FDFDDF");*/
 				}
 				finally {
 					System.out.println(connection.getInputStream());
 				}
 			}
 		} catch(IOException ioException) {
 			ioException.printStackTrace();
 		}
 	}
     
     private void readin(ChessGui g, Board gameBoard, MainBoardPanel mbp) {
     	while(true) {
	    		try {
					try {
						message = (String) g.input.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		if(mbp.teamNum == 1 && Board.convertToCoord(message) != -1L) {
	    			if(gameBoard.makeMove(mbp.teamNum, message, false)) {
	    				mbp.teamNum = Math.abs(mbp.teamNum - 1);
	    				mbp.updateBoard();
	    			}
	    		}
     	}
 	}
}
