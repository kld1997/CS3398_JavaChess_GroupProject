package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import javax.swing.*;

import Engine.Board;
import GUI.ChessGui;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import Main.Main;
import Online.ChessClient;
import Online.ChessServer;
import Options.Options;
//import MusicPlayer.*;

public class Menu extends JPanel {

    public boolean started = false;

    public Menu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(10));

        Button playbutton = new Button("Play");
        playbutton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playbutton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newOrLoadGame();
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

        Button musicButton = new Button("Music Player");
        musicButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        musicButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String media = "./audio";
                //Juke juke = new Juke("/.audio");
            }
        });

        Button settingsButton = new Button("Settings");
        settingsButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        settingsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsMain();
                settingsButton.click = false;
            }
        });
        add(settingsButton);

        add(Box.createVerticalStrut(10));

        Button exitButton = new Button("Exit");
        exitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }

    public void start() {
        started = true;
    }

    void newOrLoadGame() {
        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("New or Load Game");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 30));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(50));

        Button newButton = new Button("New Game");
        newButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        newButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
            }
        });
        add(newButton);

        add(Box.createVerticalStrut(10));

        Button loadButton = new Button("Load Game");
        loadButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        loadButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load game implementation;
                start();
            }
        });
        add(loadButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new Main();
            }
        });
        add(backButton);
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

        Button onePlayerButton = new Button("One Player");
        onePlayerButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        onePlayerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGameMode();
            }
        });
        add(onePlayerButton);

        add(Box.createVerticalStrut(10));

        Button twoPlayerButton = new Button("Two Player");
        twoPlayerButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        twoPlayerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectTwoPlayerMode();
            }
        });
        add(twoPlayerButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newOrLoadGame();
            }
        });
        add(backButton);
    }

    public void selectTwoPlayerMode() {
        removeAll();
        updateUI();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel jlabel = new JLabel("Two Player");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 30));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(50));

        Button onlineButton = new Button("Online");
        onlineButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        onlineButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOnlineMode();
            }
        });
        add(onlineButton);

        add(Box.createVerticalStrut(10));

        Button localButton = new Button("Local");
        localButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        localButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGameMode();
                start();
            }
        });
        add(localButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
            }
        });
        add(backButton);
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

        add(Box.createVerticalStrut(35));

        Button regularButton = new Button("Regular Chess");
        regularButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        regularButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChessGui(new Board(new Options()));
            }
        });

        add(regularButton);

        add(Box.createVerticalStrut(10));

        Button bulletButton = new Button("Bullet Chess");
        bulletButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        bulletButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Options options = new Options("bullet");
            	int[] timey = {120, 60};
            	options.setTime(timey);
            	new ChessGui(new Board(options));
            }
        });

        add(bulletButton);

        add(Box.createVerticalStrut(10));

        Button plusButton = new Button("Custom");
        plusButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        plusButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Options customOptions = new Options();
                new CustomMain(customOptions);
            }
        });
        add(plusButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
            }
        });
        add(backButton);
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

        add(Box.createVerticalStrut(80));

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
                //// got to Custom Games Settings as well
            }
        });
        add(hostButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNumPlayers();
            }
        });
        add(backButton);
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

        JLabel portLabel = new JLabel("Port Number:");
        portLabel.setFont(new Font("Algerian", Font.BOLD, 30));
        portLabel.setForeground(Color.BLUE);
        portLabel.setVerticalAlignment(jlabel.TOP);
        portLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(portLabel);
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

        add(Box.createVerticalStrut(20));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOnlineMode();
            }
        });
        add(backButton);
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

        add(Box.createVerticalStrut(150));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOnlineMode();
            }
        });
        add(backButton);
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