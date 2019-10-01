import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Spot extends JButton {

      public Spot(int row, int col) {
          setBorder(BorderFactory.createLineBorder(Color.WHITE));

          if((row % 2 == 0) ^ (col % 2 == 0))
            setBackground(Color.RED);
          else
            setBackground(Color.BLACK);

          setVisible(true);
      }

      public void Highlight() {
          setBorder(BorderFactory.createLineBorder(Color.yellow));
      }
}
