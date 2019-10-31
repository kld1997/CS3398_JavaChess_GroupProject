package Menu;

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

public class Menu extends JPanel {

    public boolean started = false;
    public boolean clear = false;

    public Menu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //add(Box.createVerticalStrut(280));

        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
                //start();
            }
        });

        add(playbutton);

        add(Box.createVerticalStrut(10));

        Button profilebutton = new Button("Profile");
        profilebutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        profilebutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileMain();
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
                new SettingsMain();
                start();
            }
        });
        add(settingsButton);
        //add(Box.createVerticalGlue());
        /***
         try {
         We can put background music later here.
         }
         ***/

        //setVisible(false);
    }

    public void start() {
        started = true;
    }

    public void clear() {
        clear = true;
    }

    public void selectNumPlayers() {

        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Select Number of Players");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 30));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button oneplayerbutton = new Button("Single Player");
        oneplayerbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        oneplayerbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 0
                // bool highlight = 0;
                //new ChessGui(0, false);
                selectGameMode();
            }
        });
        add(oneplayerbutton);

        add(Box.createVerticalStrut(10));

        Button twoplayerbutton = new Button("Two Player (Online)");
        twoplayerbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        twoplayerbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 0
                // bool highlight = 0;
                //new ChessGui(0, false);
                selectOnlineMode();
                //selectGameMode();
            }
        });
        add(twoplayerbutton);
    }

    public void selectGameMode() {

        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Select Mode");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button regularbutton = new Button("Regular Chess");
        regularbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        regularbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 0
                // bool highlight = 0;
                //new ChessGui(0, false);
                start();
            }
        });

        add(regularbutton);

        add(Box.createVerticalStrut(10));

        Button bulletbutton = new Button("Bullet Chess");
        bulletbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        bulletbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 1
                // bool highlight = 0;
                // new ChessGui(1, false);
                start();
            }
        });

        add(bulletbutton);

        add(Box.createVerticalStrut(10));

        Button plusbutton = new Button("Chess Plus Plus");
        plusbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        plusbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 2;
                // bool highlight = 0;
                // new ChessGui(2, false);
                start();
            }
        });

        add(plusbutton);
    }

    public void selectOnlineMode() {
        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Online Mode");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button connectButton = new Button("Connect");
        connectButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        connectButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 2;
                // bool highlight = 0;
                // new ChessGui(2, false);
                selectConnectMode();
            }
        });
        add(connectButton);

        add(Box.createVerticalStrut(10));

        Button hostButton = new Button("Host");
        hostButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        hostButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int gamemode = 2;
                // bool highlight = 0;
                // new ChessGui(2, false);
                selectHostMode();
            }
        });
        add(hostButton);
    }

    public void selectConnectMode() {
        String ipaddress;
        String port;

        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Enter IP and Port");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 40));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(50));

        JLabel iplabel = new JLabel("IP Address:");
        iplabel.setFont(new Font("Algerian", Font.BOLD, 30));
        iplabel.setForeground(Color.BLUE);
        iplabel.setVerticalAlignment(jlabel.TOP);
        iplabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(iplabel);

        add(Box.createVerticalStrut(10));

        JTextField ipField = new JTextField(10);
        //ipField.setColumns(10);
        ipField.setMaximumSize(new Dimension(300, 25));
        add(ipField);

        add(Box.createVerticalStrut(20));

        JLabel portlabel = new JLabel("Port Number:");
        portlabel.setFont(new Font("Algerian", Font.BOLD, 30));
        portlabel.setForeground(Color.BLUE);
        portlabel.setVerticalAlignment(jlabel.TOP);
        portlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(portlabel);

        add(Box.createVerticalStrut(10));

        JTextField portField = new JTextField(10);
        portField.setMaximumSize(new Dimension(300, 25));

        add(portField);

        add(Box.createVerticalStrut(20));

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipaddress;
                String port;
                ipaddress = ipField.getText();
                port = portField.getText();
                Integer.parseInt(port);
                System.out.println(ipaddress + "\n");
                System.out.println(port);
            }
        });
        add(submitButton);
    }

    public void selectHostMode() {
        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Enter Port Number:");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 40));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(50));

        JTextField portField = new JTextField(10);
        portField.setMaximumSize(new Dimension(300, 25));

        add(portField);

        add(Box.createVerticalStrut(20));

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String port;
                port = portField.getText();
                Integer.parseInt(port);
                System.out.println(port);
            }
        });
        add(submitButton);
    }


    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(Menu.class.getResource("Chess.jpg")).getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
        if (clear) {
            removeAll();
            updateUI();
        }
    }
}