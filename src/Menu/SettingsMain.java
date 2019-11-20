package Menu;

import javax.swing.JFrame;

public class SettingsMain extends JFrame {
    public SettingsMain() {
        setTitle("Settings");

        setSize(640, 480);
        setResizable(false);
        SettingsMenu settings = new SettingsMenu();
        add(settings);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
