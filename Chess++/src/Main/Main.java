//THIS CODE WAS BROUGHT TO YOU BY MAGGIA GANG
package Main;
import javax.swing.JFrame;
import Menu.*;
import Visuals.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Chess++");

        setSize(720, 640);
        setResizable(false);
        Menu menu = new Menu();
        add(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
    	Images.setUpImages();
    	Images.readMenuIcons();
        new Main();
    }
}