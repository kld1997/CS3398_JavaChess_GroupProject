package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProfileMenu extends JPanel {

    public boolean started = false;

    public ProfileMenu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel plabel = new JLabel("Profile");
        plabel.setFont(new Font("Algerian", Font.BOLD, 50));
        plabel.setForeground(Color.BLUE);
        plabel.setVerticalAlignment(plabel.TOP);
        plabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(plabel);

        add(Box.createVerticalStrut(20));

        Button createProfile = new Button("Create Profile");
        createProfile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        createProfile.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //chessGui gui = new chessGui();
                createMenu();
            }
        });

        add(createProfile);

        add(Box.createVerticalStrut(10));

        Button showProfile = new Button("Show Profile");
        showProfile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        showProfile.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //chessGui gui = new chessGui();
                showMenu();
            }
        });

        add(showProfile);

        add(Box.createVerticalStrut(10));

        Button changeprofile = new Button("Change Profile");
        changeprofile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        changeprofile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMenu();
            }
        });

        add(changeprofile);

        add(Box.createVerticalStrut(10));

        Button deleteProfile = new Button("Delete Profile");
        deleteProfile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        deleteProfile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMenu();
            }
        });

        /***
         try {
         We can put background music later here.
         }
         ***/
    }

    public void createMenu() {

        removeAll();
        updateUI();

        add(Box.createVerticalStrut(50));

        JLabel addlabel = new JLabel("Create a user name for your profile:");
        addlabel.setFont(new Font("Algerian", Font.BOLD, 10));
        addlabel.setForeground(Color.BLUE);
        addlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(addlabel);

        add(Box.createVerticalStrut(10));

        JTextField createField = new JTextField(1);
        JButton createButton = new JButton("Submit");

        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username;
                username = createField.getText();
                Profile newProfile = new Profile(username);
            }
        });
        add(createField);
        add(createButton);
    }

    public void showMenu() {
        removeAll();
        updateUI();


    }

    public void changeMenu() {

    }

    public void deleteMenu() {

    }

    //// Placeholders until button implementations.

    public void start() {
        started = true;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (!started) {
            g.drawImage(new ImageIcon(Menu.class.getResource("Chess3.jpg")).getImage(), 0, 0, 640, 480, this);
        } else {
            setBackground(Color.RED);
        }
    }

}