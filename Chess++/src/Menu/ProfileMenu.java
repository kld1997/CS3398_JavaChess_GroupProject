package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProfileMenu extends JPanel {

    public boolean started = false;
    Profile currentProfile;

    public ProfileMenu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel plabel = new JLabel("Profile");
        plabel.setFont(new Font("Algerian", Font.BOLD, 20));
        plabel.setForeground(Color.BLUE);
        plabel.setVerticalAlignment(plabel.TOP);
        plabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(plabel);

        add(Box.createVerticalStrut(10));

        Button createProfile = new Button("Create Profile");
        createProfile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        createProfile.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMenu();
            }
        });

        add(createProfile);

        add(Box.createVerticalStrut(10));

        Button showProfile = new Button("Show Profiles");
        showProfile.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        showProfile.addActionListener (new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        add(deleteProfile);

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
        addlabel.setFont(new Font("Algerian", Font.BOLD, 25));
        addlabel.setForeground(Color.BLUE);
        addlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(addlabel);

        add(Box.createVerticalStrut(10));

        JTextField createField = new JTextField(10);
        createField.setMaximumSize(new Dimension(300, 25));
        add(createField);

        add(Box.createVerticalStrut(20));

        JButton createButton = new JButton("Submit");
        createButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        createButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username;
                username = createField.getText();
                Profile newProfile = new Profile(username);
                newProfile.addProfile(newProfile);
                currentProfile = newProfile;

                add(Box.createVerticalStrut(20));

                JLabel namelabel = new JLabel(username + " was added!");
                namelabel.setFont(new Font("Algerian", Font.BOLD, 30));
                namelabel.setForeground(Color.BLUE);
                namelabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                add(namelabel);
            }
        });

        add(createButton);

        add(Box.createVerticalStrut(50));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new ProfileMenu();
            }
        });
        add(backButton);
    }

    public void showMenu() {
        removeAll();
        updateUI();

        //Profile p;
        //p.listProfiles();

    }

    public void changeMenu() {

    }

    public void deleteMenu() {
        removeAll();
        updateUI();


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