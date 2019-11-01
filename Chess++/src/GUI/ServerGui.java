package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import Engine.*;
import Online.*;

public class ServerGui extends ChessGui {
	
	public ServerGui(Board b, int onlineSet, String add, int p)
    {
		ChessGui g = this;
    	this.online = onlineSet;
    	address = add;
    	port = p;
        topPanel = new InfoPanel(this);
        mainPanel = new ChessPanel(this, false);
        rightPanel = new HistoryPanel(this);
        setJMenuBar(menu);
        mainPanel.getMainPanel().updateBoard();
        rightPanel.pp.setGui(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.7), (int)(screenSize.getHeight()*.75));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.15), (int) (screenSize.getHeight()*.15));
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
        ChessClient client = new ChessClient(b, getMainPanel(), address, port);
        Thread thread = new Thread(){
            public void run(){
              System.out.println("Thread Running");
              client.startRunning(g, b, getMainPanel());
            }
          };

          thread.start();
    	
    }
}