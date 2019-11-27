package Menu;

import Options.Options;

import javax.swing.*;

public class CustomMain extends JFrame {
    //Options customOptions = new Options();
    public CustomMain(Options o) {
        setTitle("Custom Game");

        setSize(640, 480);
        setResizable(false);
        CustomMenu custom = new CustomMenu(o);
        add(custom);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}