import javax.swing.JFrame;

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
<<<<<<< Updated upstream
    	new ChessGui();
=======
    	Images.setUpImages();
    	Images.readMenuIcons();
        new Main();
>>>>>>> Stashed changes
    }
}