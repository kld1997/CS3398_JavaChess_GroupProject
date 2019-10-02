package Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("arial", Font.BOLD, 50));
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        setTitle("Chess++");

        setSize(640, 480);
        setResizable(false);

        Menu menu = new Menu();
        menu.add(jlabel);

        add(menu);

        /**panel.setBackground(Color.DARK_GRAY);
        Button button = new Button("New Button");
        panel.add(button);
        add(panel);**/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
