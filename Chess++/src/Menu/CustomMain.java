package Menu;

import Options.Options;

import javax.swing.*;

public class CustomMain extends JFrame {
    public CustomMain(Options customOptions) {
        setTitle("Custom Game");

        setSize(720, 640);
        setResizable(false);
        CustomMenu custom = new CustomMenu(customOptions);
        add(custom);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}