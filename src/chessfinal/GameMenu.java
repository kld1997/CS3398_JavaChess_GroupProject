
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class GameMenu extends JPanel {

    public boolean started = false;

    public GameMenu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playbutton.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChessGui();
                start();
            }
        });
        setVisible(false);

        add(playbutton);

        add(Box.createVerticalStrut(10));

        Button profilebutton = new Button("Profile");
        profilebutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        profilebutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        add(profilebutton);

        add(Box.createVerticalStrut(10));

        Button settingsButton = new Button("Settings");
        settingsButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        settingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JPanel settingspanel = new JPanel();
                //settingsframe.setTitle("Settings");

                //settingsframe.setSize(640, 480);
                //settingsframe.setResizable(false);

                //SettingsMenu settings = new SettingsMenu();
                //settings.Menu();
                //settingsframe.add(settings);
            }
        });


        add(settingsButton);

        /***
        try {
          We can put background music later here.
        }
         ***/
        //setVisible(true);

    }

    //// Placeholders until button implementations.

    public void start() {
        started = true;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(Menu.class.getResource("Chess.jpg")).getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }
}
