package Menu;

import javax.swing.*;

public class ProfileMain extends JFrame {
    public ProfileMain() {
        setTitle("Profile");

        setSize(640, 480);
        setResizable(false);
        ProfileMenu profile = new ProfileMenu();
        add(profile);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
