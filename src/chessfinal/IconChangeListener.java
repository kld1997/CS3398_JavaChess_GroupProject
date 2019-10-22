package chessfinal;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
        String newIcons = ((JCheckBoxMenuItem)e.getItem()).getText();
        if(team == 0)
        {
          for(Enumeration<AbstractButton> buttons = gui.menu.groupTwo.getElements(); buttons.hasMoreElements();)
          {
            AbstractButton compare = buttons.nextElement();
            compare.setVisible(true);
            if(newIcons.equals(compare.getText()))
              compare.setVisible(false);
          }
          gui.playerOneModels = gui.menu.modelControl.playerOnePieces;
        }
        else
        {
          for(Enumeration<AbstractButton> buttons = gui.menu.groupOne.getElements(); buttons.hasMoreElements();)
          {
            AbstractButton compare = buttons.nextElement();
            compare.setVisible(true);
            if(newIcons.equals(compare.getText()))
              compare.setVisible(false);
          }
          gui.playerTwoModels = gui.menu.modelControl.playerTwoPieces;
      }
    }
    gui.updateBoard(gui.gameBoard);
  }
}
