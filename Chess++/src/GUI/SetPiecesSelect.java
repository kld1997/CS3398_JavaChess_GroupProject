package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Engine.Board;
import Engine.PawnPromote;
import Pieces.*;
import Visuals.Images;

public class SetPiecesSelect extends JPanel {

	char buttonClicked;
	JButton lastSelected;
	String[] promote = new String[2];
	
	public SetPiecesSelect(SetPiecesBoard spb) {
		
		promote[0] = "kbrq";
		promote[1] = promote[0];
		setLayout(new GridLayout(5, 2));
        showPanel(spb);
	}
	
	public void showPanel(SetPiecesBoard spb) {
		
        int teamIcon = Math.abs(spb.teamSet - 1);
        
        for (Piece p : spb.allPieces.get(0).values()) {
        	if(p.ID != 'K') {
	            JButton choiceButton = new JButton();
	            choiceButton.setIcon(new ImageIcon(Images.pieces[teamIcon][p.iconNum]));
	            if(promote[spb.teamSet].contains(p.ID + ""))
	            	choiceButton.setBackground(Color.YELLOW);
	            choiceButton.setActionCommand(p.ID + "");
	            choiceButton.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    JButton temp = (JButton) e.getSource();
	                    buttonClicked = temp.getActionCommand().charAt(0);
	                    spb.selectedPiece = buttonClicked;
	                    if(lastSelected != null) {
	                    	lastSelected.setBorder(UIManager.getBorder("Button.border"));
	                    	if(lastSelected == temp) {
	                    		if(buttonClicked == 'p')
	                    			JOptionPane.showMessageDialog(null, "Cannot promote to a pawn", "Error", JOptionPane.INFORMATION_MESSAGE);
	                    		else if(temp.getBackground() == Color.YELLOW) {
	                    			if(promote[spb.teamSet].length() == 1)
	                    				JOptionPane.showMessageDialog(null, "Must have at least one promote unit", "Error", JOptionPane.INFORMATION_MESSAGE);
	                    			else {
		                    			promote[spb.teamSet] = promote[spb.teamSet].replace(buttonClicked + "", "");
		                    			temp.setBackground(UIManager.getColor("Button.background"));
	                    			}
	                    		}
	                    		else {
	                    			promote[spb.teamSet] = promote[spb.teamSet].concat(buttonClicked + "");
	                    			temp.setBackground(Color.YELLOW);
	                    		}
	                    	}
	                    }
                    	temp.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
	                    lastSelected = temp;
	                    repaint();
	                }
	            });
	            add(choiceButton);
	        }
        }
    }
}
