package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

import Profiles.*;

public class ProfileMenu extends JPanel {

    public boolean started = false;
    public Profile currentProfile = new Profile("Username");
    ArrayList<Profile> profiles = new ArrayList<Profile>();
    public String fileName = "ProfileData.txt";
    File dataFile = new File(fileName);

    public ProfileMenu() {
        readFile();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel plabel = new JLabel("Current Profile:");
        plabel.setFont(new Font("Algerian", Font.BOLD, 30));
        plabel.setForeground(Color.BLUE);
        plabel.setVerticalAlignment(plabel.TOP);
        plabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(plabel);

        add(Box.createVerticalStrut(10));

        JLabel ulabel = new JLabel("Username: " + currentProfile.getUsername());
        ulabel.setFont(new Font("Algerian", Font.BOLD, 20));
        ulabel.setForeground(Color.BLUE);
        ulabel.setVerticalAlignment(plabel.TOP);
        ulabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(ulabel);

        add(Box.createVerticalStrut(10));

        JLabel rlabel = new JLabel("Rank: " + currentProfile.getRank());
        rlabel.setFont(new Font("Algerian", Font.BOLD, 20));
        rlabel.setForeground(Color.BLUE);
        rlabel.setVerticalAlignment(plabel.TOP);
        rlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(rlabel);

        add(Box.createVerticalStrut(10));

        JLabel slabel = new JLabel("Score: " + currentProfile.getScore());
        slabel.setFont(new Font("Algerian", Font.BOLD, 20));
        slabel.setForeground(Color.BLUE);
        slabel.setVerticalAlignment(plabel.TOP);
        slabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(slabel);

        add(Box.createVerticalStrut(10));

        Button changeButton = new Button("Change Profile");
        changeButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        changeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeProfile();
            }
        });

        add(changeButton);

        add(Box.createVerticalStrut(10));

        Button editButton = new Button("Edit Profiles");
        editButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        editButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProfiles();
            }
        });
        add(editButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Profile profile : profiles) {
                    writeFile(profile);
                }
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
        add(backButton);
    }

    public void editProfiles() {
        removeAll();
        updateUI();

        JLabel jlabel = new JLabel("Edit Profiles:");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 20));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);

        add(Box.createVerticalStrut(20));

        Button addButton = new Button("Add Profile");
        addButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProfile();
            }
        });

        add(addButton);

        add(Box.createVerticalStrut(10));

        Button deleteButton = new Button("Delete Profile");
        addButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProfile();
            }
        });

        add(deleteButton);

        add(Box.createVerticalStrut(10));

        Button backButton = new Button("Back");
        backButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        backButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new ProfileMain();
            }
        });
        add(backButton);
    }

    public void changeProfile() {
        removeAll();
        updateUI();

        add(Box.createVerticalStrut(50));

        JLabel enterLabel = new JLabel("Enter the user name for your profile:");
        enterLabel.setFont(new Font("Algerian", Font.BOLD, 25));
        enterLabel.setForeground(Color.BLUE);
        enterLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(enterLabel);

        add(Box.createVerticalStrut(10));

        JTextField enterField = new JTextField(10);
        enterField.setMaximumSize(new Dimension(300, 25));
        add(enterField);

        add(Box.createVerticalStrut(20));

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(submitButton);

        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username;
                username = enterField.getText();
                Profile profileToFind  = new Profile(username);
                currentProfile = findProfile(profileToFind);

                System.out.println(currentProfile.getUsername() + "\n");

                add(Box.createVerticalStrut(20));

                JLabel changedLabel = new JLabel("Current Profile is now: " + currentProfile.getUsername());
                changedLabel.setFont(new Font("Algerian", Font.BOLD, 25));
                changedLabel.setForeground(Color.BLUE);
                changedLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                add(changedLabel);

                add(Box.createVerticalStrut(20));

                Button okButton = new Button("Ok!");
                okButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                okButton.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new ProfileMain();
                    }
                });
                add(okButton);
            }
        });
    }

    public void addProfile() {
        removeAll();
        updateUI();

        add(Box.createVerticalStrut(30));

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
                profiles.add(newProfile);
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

        add(Box.createVerticalStrut(20));

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

    public void deleteProfile() {
        removeAll();
        updateUI();

        add(Box.createVerticalStrut(30));

        JLabel addlabel = new JLabel("Enter the user name to delete:");
        addlabel.setFont(new Font("Algerian", Font.BOLD, 25));
        addlabel.setForeground(Color.BLUE);
        addlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        add(addlabel);

        add(Box.createVerticalStrut(10));

        JTextField createField = new JTextField(10);
        createField.setMaximumSize(new Dimension(300, 25));
        add(createField);

        add(Box.createVerticalStrut(20));

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username;
                username = createField.getText();
                Profile deleteProfile = findProfile(new Profile(username));
                profiles.remove(deleteProfile);

                add(Box.createVerticalStrut(20));

                JLabel namelabel = new JLabel(deleteProfile.getUsername() + " was deleted!");
                namelabel.setFont(new Font("Algerian", Font.BOLD, 30));
                namelabel.setForeground(Color.BLUE);
                namelabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                add(namelabel);
            }
        });

        add(submitButton);

        add(Box.createVerticalStrut(20));

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

    public Profile findProfile(Profile ProfileToFind) {
        Profile foundProfile = new Profile("Username");
        for (Profile profile : profiles) {
            if (profile.getUsername().equals(ProfileToFind.getUsername())) {
                System.out.println(profile.getUsername() + "\n");
                foundProfile = profile;
            }
        }
        return foundProfile;
    }

    public void start() {
        started = true;
    }

    public void readFile() {
        try {
            FileInputStream fs = new FileInputStream(dataFile);
            ObjectInputStream is = new ObjectInputStream(fs);

            profiles = (ArrayList<Profile>) is.readObject();
            System.out.println("Read Successful\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\n");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(Profile profileToWrite) {
        try {
            FileOutputStream fs = new FileOutputStream(dataFile);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(profileToWrite);
            os.close();
            System.out.println("Write Successful\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\n");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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