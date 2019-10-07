package chessfinal;

import java.awt.image.BufferedImage;
import javax.swing.*;

public class MenuBuilder extends JMenuBar implements ItemListener
{
  Jpanel board;

  public MenuBuilder(BufferedImage[][] colorsImg, JPanel buttonBoard)
  {
    board = buttonBoard;
    JMenu menu = new JMenu("Options");
    add(menu);

    JMenu chooseColorOne = new JMenu("Board Color 1");
    JMenu chooseColorTwo = new JMenu("Board Color 2");
    ButtonGroup groupOne = new ButtonGroup();
    ButtonGroup groupTwo = new ButtonGroup();
    JCheckBoxMenuItem color;
    for(int i = 0; i < 2; i++)
    {
      color = new JCheckBoxMenuItem("Red", new ImageIcon(colorsImg[0][0]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Black", new ImageIcon(colorsImg[0][1]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        color.setState(true);
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Dark Blue", new ImageIcon(colorsImg[0][2]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Cyan", new ImageIcon(colorsImg[0][3]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Dark Gray", new ImageIcon(colorsImg[0][4]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Light Gray", new ImageIcon(colorsImg[0][5]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Dark Green", new ImageIcon(colorsImg[1][0]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Lime Green", new ImageIcon(colorsImg[1][1]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Magenta", new ImageIcon(colorsImg[1][2]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Orange", new ImageIcon(colorsImg[1][3]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      };
      color = new JCheckBoxMenuItem("Pink", new ImageIcon(colorsImg[1][4]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("White", new ImageIcon(colorsImg[1][5]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        color.setState(true);
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
      color = new JCheckBoxMenuItem("Yellow", new ImageIcon(colorsImg[2][0]), false);
      color.addItemListener(this);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
      }
    }
    menu.add(chooseColorOne);
    menu.add(chooseColorTwo);
  }
  public void itemStateChanged(ItemEvent e)
  {
    if(e.getStateChange() == ItemEvent.SELECTED)
    {
      
      JCheckBoxMenuItem chosenColor = e.getItem();
      String newColor = chosenColor.getText();
      if(newColor.equals("Red"))
      {
        for(int i = 0; i < 8; i++)
        {
          for(int j = 0; j < 8; i++)
          {
            if(!((i % 2 == 0) ^ (j % 2 == 0)))
            {
              temp = board.getComponenet((i+1) * (j+1));
              temp.setBackground(Color.RED);
            }
          }
        }
      }
    }
  }
}
