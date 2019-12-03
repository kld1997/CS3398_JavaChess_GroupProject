package Menu;

import Engine.Board;
import GUI.ChessGui;
import Options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomMenu extends JPanel {

	public boolean started = false;
	Options customOptions;

	public CustomMenu(Options customOptions) {
		this.customOptions = customOptions;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel clabel = new JLabel("Custom Game");
		clabel.setFont(new Font("Algerian", Font.BOLD, 20));
		clabel.setForeground(Color.BLUE);
		clabel.setVerticalAlignment(clabel.TOP);
		clabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(clabel);

		add(Box.createVerticalStrut(10));

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
				toggleHighlights();
		/*	}
		});

		add(highlightButton);*/

		add(Box.createVerticalStrut(10));

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

		Button timeButton = new Button("Set Play Time");
		timeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		timeButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPlayTime(customOptions);
			}
		});

		add(timeButton);

		add(Box.createVerticalStrut(10));

		Button playButton = new Button("Play Game!");
		playButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		playButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChessGui(new Board(customOptions));
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

		JRadioButton superEasy = new JRadioButton("Very Easy");
		//superEasy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//superEasy.setSelected(false);
		//add(superEasy);

		//add(.createVerticalStrut(20));

		JRadioButton easy = new JRadioButton("Easy");
		//easy.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//easy.setSelected(false);
		//add(easy);

		//add(.createVerticalStrut(20));

		JRadioButton medium = new JRadioButton("Medium", true);
		//medium.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//medium.setSelected(false);
		//add(medium);

		//add(.createVerticalStrut(20));

		JRadioButton hard = new JRadioButton("Hard");
		//hard.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//hard.setSelected(false);
		//add(hard);

		//add(.createVerticalStrut(20));

		JRadioButton vet = new JRadioButton("Very Hard");
		//vet.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		//vet.setSelected(false);
		//add(vet);

		//add(Box.createVerticalStrut(30));
		
		ButtonGroup difficulty = new ButtonGroup();
		difficulty.add(superEasy);
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		difficulty.add(vet);
		
		JPanel radioPanel = new JPanel();
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

		/*Button okButton = new Button("Ok!");
		okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		okButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (superEasyBox.isSelected()) {
					customOptions.setCPU(1);
					easyBox.setSelected(false);
					mediumBox.setSelected(false);
					hardBox.setSelected(false);
					vetBox.setSelected(false);
				} else if (easyBox.isSelected()) {
					customOptions.setCPU(2);
					superEasyBox.setSelected(false);
					mediumBox.setSelected(false);
					hardBox.setSelected(false);
					vetBox.setSelected(false);
				} else if (mediumBox.isSelected()) {
					customOptions.setCPU(3);
					superEasyBox.setSelected(false);
					easyBox.setSelected(false);
					hardBox.setSelected(false);
					vetBox.setSelected(false);
				} else if (hardBox.isSelected()) {
					customOptions.setCPU(4);
					superEasyBox.setSelected(false);
					easyBox.setSelected(false);
					mediumBox.setSelected(false);
					vetBox.setSelected(false);
				} else if (vetBox.isSelected()) {
					customOptions.setCPU(5);
					superEasyBox.setSelected(false);
					easyBox.setSelected(false);
					mediumBox.setSelected(false);
					hardBox.setSelected(false);
				}
				new CustomMain(customOptions);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(okButton);*/
	}

	public void toggleHighlights() {
		JLabel jlabel = new JLabel("Highlights");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);
		JRadioButton on = new JRadioButton("On" , true);
		JRadioButton off = new JRadioButton("Off");
		
		ButtonGroup set = new ButtonGroup();
		set.add(on);
		set.add(off);
		
		JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridBagLayout());
        radioPanel.add(on);
        radioPanel.add(off);
        
        add(radioPanel);
	}

	public void selectCustomMode() {
		
		JLabel jlabel = new JLabel("Game Mode");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		JRadioButton standard = new JRadioButton("Standard Mode");

		JRadioButton capture = new JRadioButton("Capture the King Mode");

		ButtonGroup mode = new ButtonGroup();
		mode.add(standard);
		mode.add(capture);
		
		JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridBagLayout());
        radioPanel.add(standard);
        radioPanel.add(capture);
        
        add(radioPanel);
	}

	public void setPlayTime(Options customOptions) {
		
		JLabel jlabel = new JLabel("Timer");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		add(Box.createVerticalStrut(50));

		Button teamButton = new Button("Set Team Timer's");
		teamButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		teamButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTeamTime(customOptions);
			}
		});

		add(teamButton);

		add(Box.createVerticalStrut(10));

		Button incrementButton = new Button("Set Timer Increments");
		incrementButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		incrementButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setIncrementalTime(customOptions);
			}
		});

		add(incrementButton);

		add(Box.createVerticalStrut(10));

		Button okButton = new Button("Ok!");
		okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		okButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				new CustomMain(customOptions);
			}
		});
		add(okButton);
	}

	public void setTeamTime(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Set the time for teams:");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(Box.createVerticalStrut(10));

		JLabel whiteLabel = new JLabel("White Team:");
		whiteLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		whiteLabel.setForeground(Color.BLUE);
		whiteLabel.setVerticalAlignment(jlabel.TOP);
		whiteLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(whiteLabel);

		add(Box.createVerticalStrut(10));

		JTextField whiteField = new JTextField(10);
		whiteField.setMaximumSize(new Dimension(300, 25));
		add(whiteField);

		add(Box.createVerticalStrut(20));

		JLabel blackLabel = new JLabel("Black Team:");
		blackLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		blackLabel.setForeground(Color.BLUE);
		blackLabel.setVerticalAlignment(jlabel.TOP);
		blackLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(blackLabel);
		add(Box.createVerticalStrut(10));

		JTextField blackField = new JTextField(10);
		blackField.setMaximumSize(new Dimension(300, 25));

		add(blackField);

		add(Box.createVerticalStrut(20));

		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		submitButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String whiteTime;
				String blackTime;
				whiteTime = whiteField.getText();
				blackTime = blackField.getText();
				System.out.println(whiteTime + "\n");
				System.out.println(blackTime);
				int whiteInt = Integer.parseInt(whiteTime);
				int blackInt = Integer.parseInt(blackTime);

				int[] timeList = new int[2];

				timeList[0] = whiteInt;
				timeList[1] = blackInt;

				customOptions.setTimer(true);
				customOptions.setTime(timeList);

				setPlayTime(customOptions);
			}
		});
		add(submitButton);
	}

	public void setIncrementalTime(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Set the time increments for teams:");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(Box.createVerticalStrut(10));

		JLabel whiteLabel = new JLabel("White Team:");
		whiteLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		whiteLabel.setForeground(Color.BLUE);
		whiteLabel.setVerticalAlignment(jlabel.TOP);
		whiteLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(whiteLabel);

		add(Box.createVerticalStrut(10));

		JTextField whiteField = new JTextField(10);
		whiteField.setMaximumSize(new Dimension(300, 25));
		add(whiteField);

		add(Box.createVerticalStrut(20));

		JLabel blackLabel = new JLabel("Black Team:");
		blackLabel.setFont(new Font("Algerian", Font.BOLD, 30));
		blackLabel.setForeground(Color.BLUE);
		blackLabel.setVerticalAlignment(jlabel.TOP);
		blackLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(blackLabel);
		add(Box.createVerticalStrut(10));

		JTextField blackField = new JTextField(10);
		blackField.setMaximumSize(new Dimension(300, 25));

		add(blackField);

		add(Box.createVerticalStrut(20));

		JButton submitButton = new JButton("Submit");
		submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		submitButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String whiteTime;
				String blackTime;
				whiteTime = whiteField.getText();
				blackTime = blackField.getText();
				System.out.println(whiteTime + "\n");
				System.out.println(blackTime);
				int whiteInt = Integer.parseInt(whiteTime);
				int blackInt = Integer.parseInt(blackTime);

				int[] timeList = new int[2];

				timeList[0] = whiteInt;
				timeList[1] = blackInt;

				customOptions.setTimer(true);
				customOptions.setTimeInc(timeList);

				setPlayTime(customOptions);
			}
		});
		add(submitButton);
	}

	public void start() {
		started = true;
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (!started) {
			g.drawImage(new ImageIcon("imgMenu/Chess1.jpg").getImage(), 0, 0, 640, 480, this);
		} else {
			setBackground(Color.RED);
		}
	}
}