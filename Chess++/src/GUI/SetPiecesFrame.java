package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Engine.Board;
import Options.Options;
import Pieces.Move;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SetPiecesFrame extends JFrame {

	SetPiecesBoard spb;
	SetPiecesSelect sps;
	SetOtherButtons sob;
	GenericInfoPanel top;
	JPanel right;
	JPanel center;
	JButton submit;
	
	public SetPiecesFrame(Options co) {

        spb = new SetPiecesBoard();
        sps = new SetPiecesSelect(spb);
        top = new InfoPanel(0);
        sob = new SetOtherButtons(spb, sps, top);
        submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Options customBoard = co;
            	customBoard.setBoard(spb.startBoard);
            	customBoard.setTurn(spb.teamSet);
            	customBoard.setCPUTeam(Math.abs(spb.teamSet-1));
            	customBoard.setStartTurn(spb.teamTurn);
            	customBoard.setPromote(sps.promote);
            	Board testBoard = new Board(customBoard);
            	int otherTeam = Math.abs(testBoard.teamTurn-1);
            	for(Move move : testBoard.teamMoveList) {
            		if((1L<<move.to) == testBoard.pieceList.get(otherTeam).get('K').piece && !testBoard.pieceList.get(otherTeam).containsKey('P')) {
            			testBoard = null;
            			JOptionPane.showMessageDialog(null, "A king is capturable!", "Error", JOptionPane.INFORMATION_MESSAGE);
            			return;
            		}
            	}
            	testBoard = null;
                new ChessGui(new Board(customBoard));
                setVisible(false);
                dispose();
            }
        });
        right = new JPanel();
        right.setLayout(new BorderLayout());
        sob.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.15), 
        		(int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.1)));
        submit.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getWidth()*.15), 
        		(int)(Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()*.05)));
        right.add(sob, BorderLayout.NORTH);
        right.add(sps, BorderLayout.CENTER);
        right.add(submit, BorderLayout.SOUTH);
        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(top, BorderLayout.NORTH);
        center.add(spb, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screenSize.getWidth()*.7), (int)(screenSize.getHeight()*.75));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth()*.15), (int) (screenSize.getHeight()*.15));
        setLayout(new BorderLayout());
        addPanels();
        setVisible(true);
    }
    public void addPanels()
    {
        add(center, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }
}
