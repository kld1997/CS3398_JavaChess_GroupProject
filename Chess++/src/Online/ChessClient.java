package Online;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Engine.*;
import GUI.*;

public class ChessClient {
	
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public Socket connection;
	public String message = null;
	public int turn = 1;
	public String serverIP;
    public int port;
    boolean connected = false;
    ChessGui gui;
	
	public ChessClient(String address, int p) {
		
		serverIP = address;
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
				try {
					connection = new Socket(InetAddress.getByName(serverIP), port);
					connected = true;
			            try {
			            	output = new ObjectOutputStream(connection.getOutputStream());
			            	output.flush();
							input = new ObjectInputStream(connection.getInputStream());
			            } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            readin();
				/*} catch(EOFException eofException) {
					System.out.println("FDFDDF");*/
				}
				finally {
					//System.out.println(connection.getInputStream());
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
					System.out.println(message);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(gui != null && gui.gameBoard.teamTurn != turn && Board.convertToCoord(message) != -1L) {
    			if(gui.gameBoard.makeMove(message, false)) {
    				//System.out.println(message);
    				gui.getMainPanel().moveMade(gui);
    				gui.getMainPanel().updateBoard();
    			}
    		}
    	}
	}
    
    public void setGui(ChessGui g) {
    	gui = g;
    }
    
    public boolean connected() {
    	return connected;
    }
}