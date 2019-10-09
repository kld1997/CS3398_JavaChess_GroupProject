package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SettingsMenu extends JPanel {

    public boolean started = false;

    public void Menu() {
        JPanel settingspanel = new JPanel();
        settingspanel.setLayout(new BoxLayout(settingspanel, BoxLayout.Y_AXIS));

        JLabel slabel = new JLabel("Settings");
        slabel.setFont(new Font("Algerian", Font.BOLD, 50));
        slabel.setForeground(Color.BLUE);
        slabel.setVerticalAlignment(slabel.TOP);
        slabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        settingspanel.add(slabel);

        settingspanel.add(Box.createVerticalStrut(20));

        Button toggleMusic = new Button("Toggle Music");
        toggleMusic.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        toggleMusic.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //chessGui gui = new chessGui();
                start();
            }
        });

        settingspanel.add(toggleMusic);

        settingspanel.add(Box.createVerticalStrut(10));

        Button changeprofile = new Button("Change Profile");
        changeprofile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        changeprofile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        settingspanel.add(changeprofile);

        settingspanel.add(Box.createVerticalStrut(10));

        JCheckBox highlights = new JCheckBox("Toggle Piece Move Highlights");
        highlights.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        settingspanel.add(highlights);

        highlights.setSelected(false);

        if (highlights.isSelected()) {
            /// code to implement highlight toggle

        } else {
            /// code to implement highlight toggle

        }




        /***
         try {
         We can put background music later here.
         }
         ***/

    }

    //// Placeholders until button implementations.

    public void start() {
        started = true;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(Menu.class.getResource("Menu/Chess.jpg")).getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }
}
