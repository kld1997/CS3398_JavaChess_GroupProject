package Menu;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Profile extends JPanel {
    private String username;
    private int score;
    private int rank;

    List<Profile> profiles = new ArrayList<Profile>();

    public Profile(String username) {
        this.username = username;
        score = 0;
        rank = 0;
    }

    public void addProfile(Profile p) {
        profiles.add(p);
    }

    public void selectProfiles(String usernameToFind) {
        for (Profile profile : profiles) {

        }
    }

    public void listProfiles() {
        removeAll();;
        updateUI();
        for (Profile profile : profiles) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel ulabel = new JLabel("Username: " + profile.username);
            ulabel.setFont(new Font("Algerian", Font.BOLD, 10));
            ulabel.setForeground(Color.BLUE);
            ulabel.setVerticalAlignment(ulabel.TOP);
            ulabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            add(ulabel);
            add(Box.createVerticalStrut(10));

            JLabel slabel = new JLabel("Score: " + profile.score);
            slabel.setFont(new Font("Algerian", Font.BOLD, 10));
            slabel.setForeground(Color.BLUE);
            slabel.setVerticalAlignment(slabel.TOP);
            slabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            add(slabel);
            add(Box.createVerticalStrut(10));

            JLabel rlabel = new JLabel("Rank: " + profile.username);
            rlabel.setFont(new Font("Algerian", Font.BOLD, 10));
            rlabel.setForeground(Color.BLUE);
            rlabel.setVerticalAlignment(rlabel.TOP);
            rlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            add(rlabel);
            add(Box.createVerticalStrut(10));
        }
    }

    public void deleteProfile(String usernameToFind) {
        for (Profile profile : profiles) {
            if (profile.username == usernameToFind) {
                profiles.remove(profile);
            }
        }
    }

    public void setUsername(String username, Profile p) {
        for (Profile profile : profiles) {
            if (profile.username == p.username) {
                this.username = profile.username;
            }
        }
    }

    public void setScore(int score, Profile p) {
        for (Profile profile : profiles) {
            if (profile.score == p.score) {
                this.score = profile.score;
            }
        }
    }

    public void setRank(int rank, Profile p) {
        for (Profile profile : profiles) {
            if (profile.rank == p.rank) {
                this.rank = profile.rank;
            }
        }
    }

    public String getUsername(Profile p) {
        return p.username;
    }

    public int getScore(Profile p) {
        return p.score;
    }

    public int getRank(Profile p) {
        return p.rank;
    }
}