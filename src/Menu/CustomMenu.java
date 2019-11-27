package Menu;

import Engine.Board;
import GUI.ChessGui;
import Options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.management.OperatingSystemMXBean;

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
            Button cpuButton = new Button("CPU Difficulty Settings");
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

        add(modeButton);

        Button playButton = new Button("Play Game!");
        playButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playButton.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChessGui(new Board(customOptions));
            }
        });

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

        add(Box.createVerticalStrut(50));

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
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new CustomMain(customOptions);
            }
        });
        add(okButton);

        if (easyBox.isSelected()) {
            customOptions.setCPU(1);
            mediumBox.setSelected(false);
            hardBox.setSelected(false);
            vetBox.setSelected(false);
        } else if (mediumBox.isSelected()) {
            customOptions.setCPU(2);
            easyBox.setSelected(false);
            hardBox.setSelected(false);
            vetBox.setSelected(false);
        } else if (hardBox.isSelected()) {
            customOptions.setCPU(3);
            easyBox.setSelected(false);
            mediumBox.setSelected(false);
            vetBox.setSelected(false);
        } else if (vetBox.isSelected()) {
            customOptions.setCPU(4);
            easyBox.setSelected(false);
            mediumBox.setSelected(false);
            hardBox.setSelected(false);
        }
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
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new CustomMain(customOptions);
            }
        });
        add(okButton);

        if (onBox.isSelected()) {
            customOptions.setHighlight(true);
            offBox.setSelected(false);
        } else if (offBox.isSelected()) {
            customOptions.setHighlight(false);
            onBox.setSelected(false);
        }
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

        JCheckBox bulletBox = new JCheckBox("Capture the King Mode");
        bulletBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        bulletBox.setSelected(false);
        add(bulletBox);

        add(Box.createVerticalStrut(30));

        Button okButton = new Button("Ok!");
        okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        okButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new CustomMain(customOptions);
            }
        });
        add(okButton);

        if (standardBox.isSelected()) {
            customOptions.setMode("STANDARD");
            bulletBox.setSelected(false);
        } else if (bulletBox.isSelected()) {
            customOptions.setMode("CTK");
            standardBox.setSelected(false);
        }
    }

    public void setPlayTime(Options customOptions) {

    }

    public void start() {
        started = true;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon("imgMenu/Chess3.jpg").getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }
}
