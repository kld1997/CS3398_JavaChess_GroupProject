import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

public class ChessboardGUI {

  private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
  private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
  private static int windowsWidth = 800;
  private static int windowsHeight = 600;
  private static Border whiteline = BorderFactory.createLineBorder(Color.white);
  public static JButton[][] pannelArray = new JButton[8][8];
  public static BufferedImage[][] pieces = new BufferedImage[2][6];

  public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            createImages();
            createAndShowBoard();
          }
      });
  }

  private static void createAndShowBoard(){

    JFrame frame = new JFrame("TopLevel");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panelContainer = new JPanel();
    panelContainer.setLayout(new GridLayout(8,8));

    for(int i = 0; i < 8; i++) {
      if(i % 2 == 0) {
        for(int j = 0; j < 8; j++) {
          JButton newSpot = new JButton();
          if(j % 2 == 0) {
            newSpot.setBackground(Color.red);
          }
          else {
            newSpot.setBackground(Color.black);
          }
          newSpot.setBorder(whiteline);
          newSpot.setOpaque(true);
          panelContainer.add(newSpot);
          pannelArray[i][j] = newSpot;
        }
      }
      else {
        for(int k = 0; k < 8; k++) {
          JButton newSpot = new JButton();
          if(k % 2 == 0) {
            newSpot.setBackground(Color.black);
          }
          else {
            newSpot.setBackground(Color.red);
          }
          newSpot.setBorder(whiteline);
          newSpot.setOpaque(true);
          panelContainer.add(newSpot);
          pannelArray[i][k] = newSpot;
        }
      }
    }

    pannelArray[0][0].setIcon(new ImageIcon(pieces[0][2]));
    pannelArray[0][1].setIcon(new ImageIcon(pieces[0][3]));
    pannelArray[0][2].setIcon(new ImageIcon(pieces[0][4]));
    pannelArray[0][3].setIcon(new ImageIcon(pieces[0][1]));
    pannelArray[0][4].setIcon(new ImageIcon(pieces[0][0]));
    pannelArray[0][5].setIcon(new ImageIcon(pieces[0][4]));
    pannelArray[0][6].setIcon(new ImageIcon(pieces[0][3]));
    pannelArray[0][7].setIcon(new ImageIcon(pieces[0][2]));
    for(int a = 0; a < 8; a++) {
      pannelArray[1][a].setIcon(new ImageIcon(pieces[0][5]));
    }

    pannelArray[7][0].setIcon(new ImageIcon(pieces[1][2]));
    pannelArray[7][1].setIcon(new ImageIcon(pieces[1][3]));
    pannelArray[7][2].setIcon(new ImageIcon(pieces[1][4]));
    pannelArray[7][3].setIcon(new ImageIcon(pieces[1][1]));
    pannelArray[7][4].setIcon(new ImageIcon(pieces[1][0]));
    pannelArray[7][5].setIcon(new ImageIcon(pieces[1][4]));
    pannelArray[7][6].setIcon(new ImageIcon(pieces[1][3]));
    pannelArray[7][7].setIcon(new ImageIcon(pieces[1][2]));
    for(int b = 0; b < 8; b++) {
      pannelArray[6][b].setIcon(new ImageIcon(pieces[1][5]));
    }

    frame.setContentPane(panelContainer);

    frame.setBounds((width - windowsWidth) / 2,
            (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    frame.setVisible(true);
    }


    private static final void createImages() {
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    pieces[i][j] = bi.getSubimage(
                            j * 64, i * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  }
