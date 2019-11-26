package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Engine.*;
import Visuals.*;
import Saves.*;

public class ChessGui extends JFrame
{
    public GenericInfoPanel topPanel;
    public HistoryPanel rightPanel;
    public ChessPanel mainPanel;
    JPanel centerPanel = new JPanel(new BorderLayout());
    public MenuBuilder menu;
    public Board gameBoard;

    public ChessGui(Board b)
    {
    	gameBoard = b;
      int[] temp = b.options.getTime();
    	if(b.options.getTimer())
    		topPanel = new BulletInfoPanel(this, b.options.getTime());
    	else
    		topPanel = new InfoPanel(this);
        mainPanel = new ChessPanel(this);
        rightPanel = new HistoryPanel(this);
        setJMenuBar(menu);
        mainPanel.getMainPanel().updateBoard();
        rightPanel.pp.setGui(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.7), (int)(screenSize.getHeight()*.75));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.15), (int) (screenSize.getHeight()*.15));
        setLayout(new BorderLayout());
        addPanels();
        this.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            int confirmed = JOptionPane.showConfirmDialog(null,
              "Do you want to save the current game?", "Save Game Message Box",
              JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
              SaveState.createMenu().displaySaveMenu();
            }
          }
        });
        setVisible(true);
        toFront();
    }
    public void addPanels()
    {
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(mainPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
    public MainBoardPanel getMainPanel()
    {
        return mainPanel.getMainPanel();
    }
    public HistoryPanel getHisotryPanel()
    {
        return rightPanel;
    }
    public GenericInfoPanel getInfoPanel()
    {
        return topPanel;
    }
}
