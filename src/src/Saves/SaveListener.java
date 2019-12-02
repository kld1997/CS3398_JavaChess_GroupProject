package Saves;

import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import Visuals.*;

public class SaveListener implements ActionListener {
    static SaveState state;

    public SaveListener() {
      state = SaveState.createMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      int saveButtonString = new Integer(Integer.parseInt(((SaveLoadButton)e.getSource()).getActionCommand().substring(4)));
      String newT = state.boardState.options.getMode();
      state.saveMenu.saveArray[saveButtonString].updateTitle(newT);
      try {
        state.serialize(saveButtonString);
      } catch(Exception i) {
        i.printStackTrace();
      }
      state.saveMenu.setVisible(false);
    }
}
