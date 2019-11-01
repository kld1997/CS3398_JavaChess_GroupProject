package Online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Engine.*;
import GUI.*;

public class ChessClient {
	
	
	public Socket connection;
	public String message = null;
	private String serverIP;
    private int port;
	
	public ChessClient(Board board, MainBoardPanel mbp, String address, int p) {
		
		serverIP = address;
    	port = p;
	}
	
	public void startRunning(ChessGui g, Board board, MainBoardPanel mbp) {
		try {
				try {
					connection = new Socket(InetAddress.getByName(serverIP), port);
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
			
		} catch(IOException ioException) {
			ioException.printStackTrace();
		}
	}
    
    private void readin(ChessGui g, Board gameBoard, MainBoardPanel mbp) {
    	while(true) {
    		try {
				try {
					message = (String) g.input.readObject();
					System.out.println(message);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(mbp.teamNum == 0 && Board.convertToCoord(message) != -1L) {
    			if(gameBoard.makeMove(mbp.teamNum, message, false)) {
    				System.out.println(message);
    				mbp.teamNum = Math.abs(mbp.teamNum - 1);
    				mbp.updateBoard();
    			}
    		}
    	}
	}
}