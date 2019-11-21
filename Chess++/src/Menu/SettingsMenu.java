package Menu;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsMenu extends JPanel {

    public boolean started = false;

    public SettingsMenu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel slabel = new JLabel("Settings");
        slabel.setFont(new Font("Algerian", Font.BOLD, 50));
        slabel.setForeground(Color.BLUE);
        slabel.setVerticalAlignment(slabel.TOP);
        slabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(slabel);

        add(Box.createVerticalStrut(20));

        JCheckBox toggleMusic = new JCheckBox("Toggle Music");
        toggleMusic.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(toggleMusic);

        toggleMusic.setSelected(false);

        if (toggleMusic.isSelected()) {
            //// turn on music
        } else {
            ///// turn off music
        }

        add(Box.createVerticalStrut(10));

        JCheckBox toggleSFX = new JCheckBox("Toggle Sound Effects");
        toggleSFX.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(toggleSFX);

        toggleMusic.setSelected(false);

        if (toggleMusic.isSelected()) {
            //// turn on SFX
        } else {
            ///// turn off SFX
        }

        add(Box.createVerticalStrut(10));

        JCheckBox highlights = new JCheckBox("Toggle Piece Move Highlights");
        highlights.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(highlights);

        highlights.setSelected(false);

        if (highlights.isSelected()) {
            //new ChessGui(true);

        } else {
            //new ChessGui(false);
        }

        add(Box.createVerticalStrut(100));

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

    //// Placeholders until button implementations.

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
