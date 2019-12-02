package Saves;

import java.awt.event.*;

public class LoadListener implements ActionListener {
    static SaveState state;

    public LoadListener() {
      state = SaveState.createMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveLoadButton load = (SaveLoadButton)e.getSource();
        if(load.curTitle == "Empty")
          return;
        int loadButtonString = new Integer(Integer.parseInt(load.getActionCommand().substring(4)));
        try {
          state.deserialize(loadButtonString);
        } catch(Exception i) {
          i.printStackTrace();
        }
        state.saveMenu.setVisible(false);
    }
}
