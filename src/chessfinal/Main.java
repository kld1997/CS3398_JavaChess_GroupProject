
//Roy Grady
import java.util.Scanner;


class Main {
    public static void main(String[] args) {

      Images.setUpImages();
      Images.readMenuIcons();
    	new ChessGui(new Board());
    }
}
