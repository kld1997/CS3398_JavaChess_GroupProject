import javax.swing.JFrame;
import Engine.*;
import GUI.*;
import Menu.*;
import Online.*;
import Pieces.*;
import Profiles.*;
import Visuals.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Chess++");

        setSize(640, 480);
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