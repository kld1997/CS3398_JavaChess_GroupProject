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
import Engine.*;
import GUI.*;
import Online.*;
import Saves.*;
import Options.Options;

public class Menu extends JPanel {

    public boolean started = false;
    public static SaveState loadMenu;
    public JFrame parent;

    public Menu(JFrame creator) {
        parent = creator;
        loadMenu = SaveState.createMenu();
        SaveState.createMenu(this);
        try {
          loadMenu.initButtons();
        } catch(Exception e) {
          e.printStackTrace();
        }

        displayMainMenu();
    }

    public void displayMainMenu() {
        parent.setVisible(true);
        removeAll();
        updateUI();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //add(Box.createVerticalStrut(280));

        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(50));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
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
            }
        });
        add(settingsButton);

        add(Box.createVerticalStrut(10));

        Button loadbutton = new Button("Load Game");
        loadbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        loadbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMenu.displayLoadMenu();
                if(loadMenu.boardState != null)
                    parent.setVisible(false);
            }
        });

        add(loadbutton);

        /***
         try {
         We can put background music later here.
         }
         ***/
    }

    public void start() {
        started = true;
    }

    public void selectNumPlayers() {

        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Local VS Online");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 30));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(100));

        Button oneplayerbutton = new Button("Local Two Player");
        oneplayerbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        oneplayerbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGameMode();
            }
        });
        add(oneplayerbutton);

        add(Box.createVerticalStrut(10));

        Button twoplayerbutton = new Button("Online Two Player");
        twoplayerbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        twoplayerbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOnlineMode();
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

            	new ChessGui(new Board(new Options()));
              parent.setVisible(false);
            }
        });

        add(regularbutton);

        add(Box.createVerticalStrut(10));

        Button bulletbutton = new Button("Bullet Chess");
        bulletbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        bulletbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	new ChessGui(new Board(new Options("bullet")));
            }
        });

        add(bulletbutton);

        add(Box.createVerticalStrut(10));

        Button plusbutton = new Button("Chess Plus Plus");
        plusbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        plusbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	new ChessGui(new Board(new Options("wall")));
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
                selectHostMode();
            }
        });
        add(hostButton);
    }

    public void selectConnectMode() {
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
                System.out.println(ipaddress + "\n");
                System.out.println(port);
                int p = Integer.parseInt(port);

                ChessClient client = new ChessClient(ipaddress, p);

                while(!client.connected()) {
                	try {
						Thread.sleep(250);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                if(client.connection.isConnected()) {
                	Options options = new Options();
                	options.setOnline(true);
                	options.setOutput(client.output);
                	options.setInput(client.input);
                	options.setTurn(client.turn);

                	client.setGui(new ChessGui(new Board(options)));
                }
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
                System.out.println(port);
                int p = Integer.parseInt(port);

                ChessServer server = new ChessServer(p);

                while(!server.connected()) {
                	try {
						Thread.sleep(250);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                if(server.connection.isConnected()) {
                	Options options = new Options();
                	options.setOnline(true);
                	options.setOutput(server.output);
                	options.setInput(server.input);
                	options.setTurn(server.turn);

                	server.setGui(new ChessGui(new Board(options)));
                }

            }
        });
        add(submitButton);
    }


    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon("imgMenu/Chess.jpg").getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }
}
