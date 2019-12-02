//Kody Davis
package Visuals;

import javax.swing.*;
import java.awt.event.*;
import Saves.*;
import Engine.*;

public class SaveButton extends JMenuItem {

    public static SaveState saveState;

    public SaveButton(String title, Board thisBoard) {
        super(title);
        saveState = SaveState.createMenu(thisBoard);
        this.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                saveState.displaySaveMenu();
            }
         });

        if(thisBoard.options.getMode().equals("BULLET"))
          setVisible(false);
    }
}
