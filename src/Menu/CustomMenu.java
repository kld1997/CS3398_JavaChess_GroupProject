package Menu;

import Engine.Board;
import GUI.ChessGui;
import Options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomMenu extends JPanel {

	public boolean started = false;

	public CustomMenu(Options customOptions) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel clabel = new JLabel("Custom Game");
		clabel.setFont(new Font("Algerian", Font.BOLD, 20));
		clabel.setForeground(Color.BLUE);
		clabel.setVerticalAlignment(clabel.TOP);
		clabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(clabel);

		add(Box.createVerticalStrut(10));

		if (customOptions.getCPU() > 0) {
			Button cpuButton = new Button("Set CPU Difficulty");
			cpuButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			cpuButton.addActionListener(new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectCPUDifficulty(customOptions);
				}
			});

			add(cpuButton);
		}
		add(Box.createVerticalStrut(10));

		Button highlightButton = new Button("Toggle Highlights");
		highlightButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		highlightButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleHighlights(customOptions);
			}
		});

		add(highlightButton);

		add(Box.createVerticalStrut(10));

		Button modeButton = new Button("Select Game Mode");
		modeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		modeButton.addActionListener (new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectCustomMode(customOptions);
			}
		});

		add(modeButton);

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

	public void selectCPUDifficulty(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Select CPU Difficulty");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		add(Box.createVerticalStrut(30));

		JCheckBox superEasyBox = new JCheckBox("Super Easy Difficulty");
		superEasyBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		superEasyBox.setSelected(false);
		add(superEasyBox);

		add(Box.createVerticalStrut(20));

		JCheckBox easyBox = new JCheckBox("Easy Difficulty");
		easyBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		easyBox.setSelected(false);
		add(easyBox);

		add(Box.createVerticalStrut(20));

		JCheckBox mediumBox = new JCheckBox("Medium Difficulty");
		mediumBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		mediumBox.setSelected(false);
		add(mediumBox);

		add(Box.createVerticalStrut(20));

		JCheckBox hardBox = new JCheckBox("Hard Difficulty");
		hardBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		hardBox.setSelected(false);
		add(hardBox);

		add(Box.createVerticalStrut(20));

		JCheckBox vetBox = new JCheckBox("Veteran Difficulty");
		vetBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		vetBox.setSelected(false);
		add(vetBox);

		add(Box.createVerticalStrut(30));

		Button okButton = new Button("Ok!");
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
		add(okButton);
	}

	public void toggleHighlights(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Enable / Disable Highlights:");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		JCheckBox onBox = new JCheckBox("Highlights on");
		onBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		onBox.setSelected(false);
		add(onBox);

		add(Box.createVerticalStrut(20));

		JCheckBox offBox = new JCheckBox("Highlights off");
		offBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		offBox.setSelected(false);
		add(offBox);

		add(Box.createVerticalStrut(30));

		Button okButton = new Button("Ok!");
		okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		okButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onBox.isSelected()) {
					customOptions.setHighlight(true);
					offBox.setSelected(false);
				} else if (offBox.isSelected()) {
					customOptions.setHighlight(false);
					onBox.setSelected(false);
				}
				new CustomMain(customOptions);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(okButton);
	}

	public void selectCustomMode(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Select Game Mode:");
		jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
		jlabel.setForeground(Color.BLUE);
		jlabel.setVerticalAlignment(jlabel.TOP);
		jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		add(jlabel);

		JCheckBox standardBox = new JCheckBox("Standard Mode");
		standardBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		standardBox.setSelected(false);
		add(standardBox);

		add(Box.createVerticalStrut(20));

		JCheckBox captureBox = new JCheckBox("Capture the King Mode");
		captureBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		captureBox.setSelected(false);
		add(captureBox);

		add(Box.createVerticalStrut(30));

		Button okButton = new Button("Ok!");
		okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		okButton.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (standardBox.isSelected()) {
					customOptions.setMode("STANDARD");
					captureBox.setSelected(false);
				} else if (captureBox.isSelected()) {
					customOptions.setCaptureKing(true);
					standardBox.setSelected(false);
				}
				new CustomMain(customOptions);
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
			}
		});
		add(okButton);
	}

	public void setPlayTime(Options customOptions) {
		removeAll();
		updateUI();

		JLabel jlabel = new JLabel("Set Play Time:");
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