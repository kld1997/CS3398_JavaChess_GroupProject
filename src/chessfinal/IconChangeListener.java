package chessfinal;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class IconChangeListener implements ItemListener
{
    ChessGui gui;
    public IconChangeListener(ChessGui temp)
    {
      gui = temp;
    }

      @Override
    public void itemStateChanged(ItemEvent e)
    {
      if(e.getStateChange() == ItemEvent.SELECTED)
      {
        int team = gui.teamNum;
        String newIcons = ((JCheckBoxMenuItem)e.getItem()).getActionCommand();
        if(team == 0)
        {
          int i = 0;
          for(Enumeration<AbstractButton> buttons = gui.menu.groupTwo.getElements(); buttons.hasMoreElements();)
          {
            AbstractButton compare = buttons.nextElement();
            compare.setVisible(true);
            if(newIcons.equals(compare.getActionCommand()))
            {
              compare.setVisible(false);
              gui.menu.modelControl.setPieceModel(team, i);
            }
            i++;
          }
          gui.playerTwoModels = gui.menu.modelControl.playerOnePieces;
        }
        else
        {
          int i = 0;
          for(Enumeration<AbstractButton> buttons = gui.menu.groupOne.getElements(); buttons.hasMoreElements();)
          {
            AbstractButton compare = buttons.nextElement();
            compare.setVisible(true);
            if(newIcons.equals(compare.getActionCommand()))
            {
              compare.setVisible(false);
              gui.menu.modelControl.setPieceModel(team, i);
            }
            i++;
          }
          gui.playerOneModels = gui.menu.modelControl.playerTwoPieces;
        }
    }
    gui.updateBoard(gui.gameBoard);
  }
}
