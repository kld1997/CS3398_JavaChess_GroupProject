package Online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Engine.*;
import GUI.*;

public class ChessServer {

	public ObjectInputStream input;
	public ObjectOutputStream output;
	public ServerSocket server;
	public Socket connection;
	public String message = null;
	public int turn = 0;
	public int port;
	ChessGui gui;
	
	public ChessServer(int p) {
		
		port = p;
		
		Thread thread = new Thread(){
            public void run(){
              System.out.println("Client Running");
              startRunning();
            }
          };

        thread.start();
	}
	
	 public void startRunning() {
 		try {
 			server = new ServerSocket(port, 100);
 			while(true) {
 				try {
 			            try {
 							connection = server.accept();
 						} catch (IOException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 			            try {
 			            	output = new ObjectOutputStream(connection.getOutputStream());
 			            	output.flush();
 							input = new ObjectInputStream(connection.getInputStream());
 			            } catch (IOException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 			            if(gui != null)
 			            	readin();
 				}
 				finally {
 					System.out.println(connection.getInputStream());
 				}
 			}
 		} catch(IOException ioException) {
 			ioException.printStackTrace();
 		}
 	}
     
     private void readin() {
     	while(true) {
    		try {
				try {
					message = (String) input.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(gui.gameBoard.teamTurn == turn && Board.convertToCoord(message) != -1L) {
    			if(gui.gameBoard.makeMove(message, false)) {
    				System.out.println(message);
    				gui.getMainPanel().updateBoard();
    			}
    		}
     	}
 	}
     
     public void setGui(ChessGui g) {
     	gui = g;
     }
     
     public boolean connected() {
     	if(connection != null)
     		return true;
     	else
     		return false;
     }
}
