package Menu;

import javax.swing.*;

public class GameModeChooserMain extends JFrame
{
    public GameModeChooserMain() {
        setTitle("Custom Mode");

        setSize(640, 480);
        setResizable(false);
        GameModeChooser chooser = new GameModeChooser();
        add(chooser);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
