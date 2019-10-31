package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
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

        Button changeprofile = new Button("Change Profile");
        changeprofile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        changeprofile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        add(changeprofile);

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
    }

    //// Placeholders until button implementations.

    public void start() {
        started = true;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(Menu.class.getResource("Chess1.jpg")).getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }
}
