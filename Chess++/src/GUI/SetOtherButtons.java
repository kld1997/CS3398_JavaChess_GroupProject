package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SetOtherButtons extends JPanel {

	public SetOtherButtons(SetPiecesBoard spb, SetPiecesSelect sps, GenericInfoPanel top) {
	
		setLayout(new GridLayout(1, 2));
		switchTeams(spb, sps);
		switchTurn(spb, top);
	}
	
	public void switchTeams(SetPiecesBoard spb, SetPiecesSelect sps) {
		
		JButton switchTeam = new JButton("Switch Teams");
        switchTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spb.teamSet = Math.abs(spb.teamSet-1);
                sps.removeAll();
                sps.showPanel(spb);
                sps.validate();
                sps.repaint();
            }
        });
        add(switchTeam);
	}
	
	public void switchTurn(SetPiecesBoard spb, GenericInfoPanel top) {
		
		JButton turn = new JButton("Switch Turn");
        turn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spb.teamTurn = Math.abs(spb.teamTurn-1);
                top.setTeamText(spb.teamTurn);
            }
        });
        add(turn);
	}
}
