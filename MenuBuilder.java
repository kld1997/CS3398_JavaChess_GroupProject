

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class MenuBuilder extends JMenuBar implements ItemListener
{
  ChessSquare[][] arraySquares = new ChessSquare[8][8];
  JMenu chooseColorOne = new JMenu("Board Color 1");
  JMenu chooseColorTwo = new JMenu("Board Color 2");
  ButtonGroup groupOne = new ButtonGroup();
  ButtonGroup groupTwo = new ButtonGroup();
  JCheckBoxMenuItem[] arrayOne = new JCheckBoxMenuItem[13];
  JCheckBoxMenuItem[] arrayTwo = new JCheckBoxMenuItem[13];

  public MenuBuilder(BufferedImage[][] colorsImg, ChessSquare[][] a)
  {
    arraySquares = a;
    JMenu menu = new JMenu("Options");
    add(menu);

    JCheckBoxMenuItem color;
    for(int i = 0; i < 2; i++)
    {
      color = new JCheckBoxMenuItem("Red", new ImageIcon(colorsImg[0][0]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[0] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[0] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Black", new ImageIcon(colorsImg[0][1]), false);
      if(i == 0)
      {
        color.setState(true);
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[1] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[1] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Dark Blue", new ImageIcon(colorsImg[0][2]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[2] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[2] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Cyan", new ImageIcon(colorsImg[0][3]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[3] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[3] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Dark Gray", new ImageIcon(colorsImg[0][4]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[4] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[4] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Light Gray", new ImageIcon(colorsImg[0][5]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[5] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[5] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Dark Green", new ImageIcon(colorsImg[1][0]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[6] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[6] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Lime Green", new ImageIcon(colorsImg[1][1]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[7] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[7] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Magenta", new ImageIcon(colorsImg[1][2]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[8] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[8] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Orange", new ImageIcon(colorsImg[1][3]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[9] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[9] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Pink", new ImageIcon(colorsImg[1][4]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[10] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[10] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("White", new ImageIcon(colorsImg[1][5]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[11] = color;
      }
      else
      {
        color.setState(true);
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[11] = color;
      }
      color.addItemListener(this);
      color = new JCheckBoxMenuItem("Yellow", new ImageIcon(colorsImg[2][0]), false);
      if(i == 0)
      {
        chooseColorOne.add(color);
        groupOne.add(color);
        arrayOne[12] = color;
      }
      else
      {
        chooseColorTwo.add(color);
        groupTwo.add(color);
        arrayTwo[12] = color;
      }
      color.addItemListener(this);
    }
    menu.add(chooseColorOne);
    menu.add(chooseColorTwo);
  }


    @Override
  public void itemStateChanged(ItemEvent e)
  {
    if(e.getStateChange() == ItemEvent.SELECTED)
    {
      JCheckBoxMenuItem chosenColor = (JCheckBoxMenuItem)e.getItem();
      boolean belongsFirst = false;
      boolean belongsSecond = false;
      String newColor = chosenColor.getText();
      ChessSquare temp;
      for(int i = 0; i < 13; i++)
      {
        boolean selected = arrayOne[i].getState();
        boolean selectedTwo = arrayTwo[i].getState();
        if(selected)
          belongsFirst = newColor.equals(arrayOne[i].getText());
        if(selectedTwo)
          belongsSecond = newColor.equals(arrayTwo[i].getText());
      }
      if(belongsFirst)
      {
        if(newColor.equals("Red"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.RED);
              }
            }
          }
        }
        else if(newColor.equals("Black"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp =arraySquares[i][j];
                temp.setBackground(Color.BLACK);
              }
            }
          }
        }
        else if(newColor.equals("Dark Blue"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.BLUE);
              }
            }
          }
        }
        else if(newColor.equals("Cyan"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.CYAN);
              }
            }
          }
        }
        else if(newColor.equals("Dark Gray"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.DARK_GRAY);
              }
            }
          }
        }
        else if(newColor.equals("Lime Green"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(new Color(152, 255, 102));
              }
            }
          }
        }
        else if(newColor.equals("Dark Green"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.GREEN);
              }
            }
          }
        }
        else if(newColor.equals("Light Gray"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.LIGHT_GRAY);
              }
            }
          }
        }
        else if(newColor.equals("Magenta"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.MAGENTA);
              }
            }
          }
        }
        else if(newColor.equals("Orange"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.ORANGE);
              }
            }
          }
        }
        else if(newColor.equals("Pink"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.PINK);
              }
            }
          }
        }
        else if(newColor.equals("White"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.WHITE);
              }
            }
          }
        }
        else if(newColor.equals("Yellow"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.YELLOW);
              }
            }
          }
        }
      }
      if(belongsSecond)
      {
        if(newColor.equals("Red"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.RED);
              }
            }
          }
        }
        else if(newColor.equals("Black"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.BLACK);
              }
            }
          }
        }
        else if(newColor.equals("Dark Blue"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.BLUE);
              }
            }
          }
        }
        else if(newColor.equals("Cyan"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.CYAN);
              }
            }
          }
        }
        else if(newColor.equals("Dark Gray"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.DARK_GRAY);
              }
            }
          }
        }
        else if(newColor.equals("Lime Green"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(new Color(152, 255, 102));
              }
            }
          }
        }
        else if(newColor.equals("Dark Green"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.GREEN);
              }
            }
          }
        }
        else if(newColor.equals("Light Gray"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.LIGHT_GRAY);
              }
            }
          }
        }
        else if(newColor.equals("Magenta"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.MAGENTA);
              }
            }
          }
        }
        else if(newColor.equals("Orange"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.ORANGE);
              }
            }
          }
        }
        else if(newColor.equals("Pink"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.PINK);
              }
            }
          }
        }
        else if(newColor.equals("White"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.WHITE);
              }
            }
          }
        }
        else if(newColor.equals("Yellow"))
        {
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if((i % 2 == 0) ^ (j % 2 == 0))
              {
                temp = arraySquares[i][j];
                temp.setBackground(Color.YELLOW);
              }
            }
          }
        }
      }
    }
  }
}
