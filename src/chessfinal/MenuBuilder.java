package chessfinal;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class MenuBuilder extends JMenuBar
{
  ModelController modelControl;
  JMenu playerOneIcons = new JMenu("Icon Set");
  JMenu playerTwoIcons = new JMenu("Icon Set");
  ButtonGroup groupOne = new ButtonGroup();
  ButtonGroup groupTwo = new ButtonGroup();
  int currentTeam = 0;

  public MenuBuilder(ChessSquare[][] squares)
  {
    playerTwoIcons.setVisible(false);
    modelControl = new ModelController();
    JMenu menu = new JMenu("Options");
    JMenu iconSet = new JMenu();
    add(menu);

    for(int j = 0; j < 2; j++)
    {
      for(int i = 0; i < 5; i++)
      {
        if(i == 0)
        {
          iconSet = buildChoices("CharactersOne", j);
        }
        else if(i == 1)
        {
          iconSet = buildChoices("CharactersTwo", j);
        }
        else if(i == 2)
        {
          iconSet = buildChoices("Classic", j);
        }
        else if(i == 3)
        {
          iconSet = buildChoices("ShadedOne", j);
        }
        else if(i == 4)
        {
          iconSet = buildChoices("ShadedTwo", j);
        }

        if(j == 0)
        {
          playerOneIcons.add(iconSet);
        }
        else if(j == 1)
        {
          playerTwoIcons.add(iconSet);
        }
      }
    }

    menu.add(playerOneIcons);
    menu.add(playerTwoIcons);
    menu.add(new ColorMenu("Choose Board Color", squares));
  }

  private JMenu buildChoices(String text, int tm)
  {
    JMenu iconMenu = new JMenu(text);
    String colorOne = "Red";
    String colorTwo = "Silver";
    String colorThree = "Gold";
    String colorFour = "Black";
    boolean sel = false;
    JCheckBoxMenuItem temp;
    if(tm == 0)
    {
      if(text.equals("CharactersOne"))
      {
        temp = new JCheckBoxMenuItem(colorOne, new ImageIcon(modelControl.menuIcons[1]), sel);
        temp.setActionCommand(text + " " + colorOne);
        iconMenu.add(temp);
        groupOne.add(temp);
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[0]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupOne.add(temp);
      }
      else if(text.equals("CharactersTwo"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[2]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupOne.add(temp);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[3]), sel);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupOne.add(temp);
      }
      else if(text.equals("Classic"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[4]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupOne.add(temp);
        temp.setVisible(false);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[5]), true);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupOne.add(temp);
      }
      else if(text.equals("ShadedOne"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[6]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupOne.add(temp);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[7]), sel);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupOne.add(temp);
      }
      else if(text.equals("ShadedTwo"))
      {
        temp = new JCheckBoxMenuItem(colorFour, new ImageIcon(modelControl.menuIcons[8]), sel);
        temp.setActionCommand(text + " " + colorFour);
        iconMenu.add(temp);
        groupOne.add(temp);
        temp = new JCheckBoxMenuItem(colorOne, new ImageIcon(modelControl.menuIcons[9]), sel);
        temp.setActionCommand(text + " " + colorOne);
        iconMenu.add(temp);
        groupOne.add(temp);
      }
    }
    else
    {
      if(text.equals("CharactersOne"))
      {
        temp = new JCheckBoxMenuItem(colorOne, new ImageIcon(modelControl.menuIcons[1]), sel);
        temp.setActionCommand(text + " " + colorOne);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[0]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupTwo.add(temp);
      }
      else if(text.equals("CharactersTwo"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[2]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[3]), sel);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupTwo.add(temp);
      }
      else if(text.equals("Classic"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[4]), true);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[5]), sel);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp.setVisible(false);
      }
      else if(text.equals("ShadedOne"))
      {
        temp = new JCheckBoxMenuItem(colorThree, new ImageIcon(modelControl.menuIcons[6]), sel);
        temp.setActionCommand(text + " " + colorThree);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp = new JCheckBoxMenuItem(colorTwo, new ImageIcon(modelControl.menuIcons[7]), sel);
        temp.setActionCommand(text + " " + colorTwo);
        iconMenu.add(temp);
        groupTwo.add(temp);
      }
      else if(text.equals("ShadedTwo"))
      {
        temp = new JCheckBoxMenuItem(colorFour, new ImageIcon(modelControl.menuIcons[8]), sel);
        temp.setActionCommand(text + " " + colorFour);
        iconMenu.add(temp);
        groupTwo.add(temp);
        temp = new JCheckBoxMenuItem(colorOne, new ImageIcon(modelControl.menuIcons[9]), sel);
        temp.setActionCommand(text + " " + colorOne);
        iconMenu.add(temp);
        groupTwo.add(temp);
      }
    }
    return iconMenu;
  }

  public void teamChange()
  {
    if(currentTeam == 0)
    {
      currentTeam = 1;
      playerOneIcons.setVisible(false);
      playerTwoIcons.setVisible(true);
    }
    else
    {
      currentTeam = 0;
      playerOneIcons.setVisible(true);
      playerTwoIcons.setVisible(false);
    }
  }



  public void setListener(IconChangeListener e)
  {
    for(Enumeration<AbstractButton> buttons = groupOne.getElements(); buttons.hasMoreElements();)
    {
      AbstractButton compare = buttons.nextElement();
      compare.addItemListener(e);
    }
    for(Enumeration<AbstractButton> buttons = groupTwo.getElements(); buttons.hasMoreElements();)
    {
      AbstractButton compare = buttons.nextElement();
      compare.addItemListener(e);
    }
  }
}
