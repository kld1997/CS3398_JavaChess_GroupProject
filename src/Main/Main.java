//THIS CODE WAS BROUGHT TO YOU BY MAGGIA GANG
package Main;
import javax.swing.JFrame;
import Menu.*;
import Visuals.*;

public class Main extends JFrame {
    public Main(String[] arg) {
        setTitle("Chess++");

        setSize(640, 480);
        setResizable(false);
        String[] path = arg;
        Menu menu = new Menu(path);
        add(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
    	Images.setUpImages();
    	String[] path = args;
    	Images.readMenuIcons();
        new Main(path);
    }
}