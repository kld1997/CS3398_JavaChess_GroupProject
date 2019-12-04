package Menu;

import Engine.Board;
import GUI.ChessGui;
import GUI.SetPiecesFrame;
import Options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomMenu extends JPanel {

	public boolean started = false;
	ButtonGroup difficulty;
	ButtonGroup cpuTeam;
	JCheckBox highlight;
	ButtonGroup mode;
	JCheckBox timeToggle;
	Options customOptions;
	ButtonGroup stBoard;
	
	JRadioButton superEasy;
	JRadioButton easy;
	JRadioButton medium;
	JRadioButton hard;
	JRadioButton vet;
	
	JRadioButton white;
	JRadioButton black;
	
	JRadioButton standard;
	JRadioButton capture;
	
	JTextField whiteField;
	JTextField whiteField2;
	JTextField whiteField3;
	JTextField blackField;
	JTextField blackField2;
	JTextField blackField3;
	
	JRadioButton standard2;
	JRadioButton custom;

	public CustomMenu(Options customOptions) {
		this.customOptions = customOptions;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/*JLabel clabel = new JLabel("Custom Game");
		clabel.setFont(new Font("Algerian", Font.BOLD, 20));
		clabel.setForeground(Color.BLUE);
		clabel.setVerticalAlignment(clabel.TOP);
		clabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(clabel);*/

		//add(Box.createVerticalStrut(10));

		if (customOptions.getCPU() > 0) {
			/*Button cpuButton = new Button("Set CPU Difficulty");
			cpuButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			cpuButton.addActionListener(new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectCPUDifficulty(customOptions);
				}
			});

			add(cpuButton);*/
			selectCPUDifficulty();
		}
		add(Box.createVerticalStrut(10));

		/*Button highlightButton = new Button("Toggle Highlights");
		highlightButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		highlightButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {*/
				
		/*	}
		});

		add(highlightButton);*/

		/*Button modeButton = new Button("Select Game Mode");
		modeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		modeButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {*/
				selectCustomMode();
			/*}
		});

		
		add(modeButton);*/
		add(Box.createVerticalStrut(10));
				
		toggleHighlights();
		
		//add(Box.createVerticalStrut(10));

		/*Button timeButton = new Button("Set Play Time");
		timeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		timeButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {*/
				setPlayTime();
			/*}
		});

		add(timeButton);*/

		//add(Box.createVerticalStrut(10));
		
		setBoard();
		
		add(Box.createVerticalStrut(10));

		Button playButton = new Button("Play Game!");
		playButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		playButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(customOptions.getCPU() > 0) {
					int dif = 3;
					if(superEasy.isSelected())
						dif = 1;
					else if(easy.isSelected())
						dif = 2;
					else if(medium.isSelected())
						dif = 3;
					else if(hard.isSelected())
						dif = 4;
					else if(vet.isSelected())
						dif = 5;
					
					int team = 1;
					if(white.isSelected())
						team = 0;
					else if(black.isSelected())
						team = 1;
					
					customOptions.setCPU(dif);
					customOptions.setCPUTeam(team);
				}
				
				customOptions.setHighlight(highlight.isSelected());
				
				customOptions.setCaptureKing(capture.isSelected());
				
				customOptions.setTimer(timeToggle.isSelected());
				
				if(timeToggle.isSelected()) {
					int[] time = new int[2];
					int[] incr = new int[2];
					
					try {
						time[0] = Integer.parseInt(whiteField.getText()) * 60;
						time[0] += Integer.parseInt(whiteField2.getText());
						incr[0] = Integer.parseInt(whiteField3.getText());
						
						time[1] = Integer.parseInt(blackField.getText()) * 60;
						time[1] += Integer.parseInt(blackField2.getText());
						incr[1] = Integer.parseInt(blackField3.getText());
					} catch(Exception IllegalArgumentException) {
						time[0] = 182;
						time[1] = 182;
						incr[0] = 0;
						incr[1] = 0;
					}
					
					customOptions.setTime(time);
					customOptions.setTimeInc(incr);
				}
				
				if(standard2.isSelected())
					new ChessGui(new Board(customOptions));
				else if(custom.isSelected())
					new SetPiecesFrame(customOptions);
			}
		});

		add(playButton);

		add(Box.createVerticalStrut(10));

		Button backButton = new Button("Back");
		backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		backButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(backButton);
	}

	public void selectCPUDifficulty() {
		//removeAll();
		//updateUI();

		JLabel jlabel = new JLabel("CPU Difficulty");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		//add(Box.createVerticalStrut(30));

		superEasy = new JRadioButton("Very Easy");
		//superEasy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//superEasy.setSelected(false);
		//add(superEasy);

		//add(.createVerticalStrut(20));

		easy = new JRadioButton("Easy");
		//easy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//easy.setSelected(false);
		//add(easy);

		//add(.createVerticalStrut(20));

		medium = new JRadioButton("Medium", true);
		//medium.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//medium.setSelected(false);
		//add(medium);

		//add(.createVerticalStrut(20));

		hard = new JRadioButton("Hard");
		//hard.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//hard.setSelected(false);
		//add(hard);

		//add(.createVerticalStrut(20));

		vet = new JRadioButton("Very Hard");
		//vet.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//vet.setSelected(false);
		//add(vet);

		//add(Box.createVerticalStrut(30));
		
		difficulty = new ButtonGroup();
		difficulty.add(superEasy);
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		difficulty.add(vet);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setOpaque(false);
        radioPanel.setLayout(new GridBagLayout());
        radioPanel.add(superEasy);
        radioPanel.add(easy);
        radioPanel.add(medium);
        radioPanel.add(hard);
        radioPanel.add(vet);
        
        //radioPanel.setBorder(BorderFactory.createTitledBorder(
        //          BorderFactory.createEtchedBorder(), "Married?"));

        //setContentPane(radioPanel);
        add(radioPanel);
        
        JLabel jlabel2 = new JLabel("Select The CPU's Team");
		jlabel2.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel2.setForeground(Color.BLUE);
		jlabel2.setVerticalAlignment(jlabel2.TOP);
		jlabel2.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel2);
		
		white = new JRadioButton("White Team");
		black = new JRadioButton("Black Team", true);
		
		cpuTeam = new ButtonGroup();
		cpuTeam.add(white);
		cpuTeam.add(black);
		
		JPanel radioPanel2 = new JPanel();
		radioPanel2.setOpaque(false);
		radioPanel2.add(white);
		radioPanel2.add(black);
		
		add(radioPanel2);
	}

	public void toggleHighlights() {
		JPanel jpan = new JPanel();
		//jpan.setOpaque(false);
		jpan.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		JLabel jlabel = new JLabel("Highlights");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		jpan.add(jlabel);
		
		highlight = new JCheckBox();
		highlight.setSelected(true);
		
		jpan.add(highlight);
        
        add(jpan);
	}

	public void selectCustomMode() {
		
		JLabel jlabel = new JLabel("Game Mode");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		standard = new JRadioButton("Standard Mode", true);

		capture = new JRadioButton("Capture the King Mode");

		mode = new ButtonGroup();
		mode.add(standard);
		mode.add(capture);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setOpaque(false);
        radioPanel.setLayout(new GridBagLayout());
        radioPanel.add(standard);
        radioPanel.add(capture);
        
        add(radioPanel);
	}

	public void setPlayTime() {
		
		JPanel jpan = new JPanel();
		//jpan.setOpaque(false);
		jpan.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		JLabel jlabel = new JLabel("Timer");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		jpan.add(jlabel);

		//add(Box.createVerticalStrut(50));
		
		timeToggle = new JCheckBox("");
		jpan.add(timeToggle);
		add(jpan);
		
		JPanel time = new JPanel();
		time.setOpaque(false);
		time.setLayout(new BoxLayout(time, BoxLayout.PAGE_AXIS));

		JPanel white = new JPanel();
		
		JLabel whiteLabel = new JLabel("White Team's Time: ");
		white.add(whiteLabel);
		
		//timeFields(white);
		
		whiteField = new JTextField(2);
		white.add(whiteField);
		
		JLabel colon = new JLabel(":");
		white.add(colon);
		
		whiteField2 = new JTextField(3);
		white.add(whiteField2);
		
		JLabel inc = new JLabel("Set Increment: ");
		white.add(inc);
		
		whiteField3 = new JTextField(2);
		white.add(whiteField3);
		
		whiteField.setText("2");
		whiteField2.setText("00");
		whiteField3.setText("5");
		
		JPanel black = new JPanel();
		
		JLabel blackLabel = new JLabel("Black Team's Time: ");
		black.add(blackLabel);
		
		//timeFields(black);
		
		blackField = new JTextField(2);
		black.add(blackField);
		
		JLabel colon2 = new JLabel(":");
		black.add(colon2);
		
		blackField2 = new JTextField(3);
		black.add(blackField2);
		
		JLabel inc2 = new JLabel("Set Increment: ");
		black.add(inc2);
		
		blackField3 = new JTextField(2);
		black.add(blackField3);
		
		blackField.setText("2");
		blackField2.setText("00");
		blackField3.setText("5");
		
		time.add(white);
		time.add(black);
		
		add(time);
		
	}
	
	public void setBoard() {
		
		JLabel jlabel = new JLabel("Board Type");
		jlabel.setOpaque(true);
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		add(jlabel);
		
		standard2 = new JRadioButton("Standard", true);
		custom = new JRadioButton("Custom");
		
		stBoard = new ButtonGroup();
		stBoard.add(standard2);
		stBoard.add(custom);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setOpaque(false);
        radioPanel.setLayout(new GridBagLayout());
        radioPanel.add(standard2);
        radioPanel.add(custom);
        
        add(radioPanel);
	}

	public void start() {
		started = true;
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (!started) {
			g.drawImage(new ImageIcon("imgMenu/Chess1.jpg").getImage(), 0, 0, 720, 640, this);
		} else {
			setBackground(Color.RED);
		}
	}
}