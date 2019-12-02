package Saves;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class SaveLoadButton extends JButton {

    String curTitle;

    public SaveLoadButton(String title) {
      super(title);
      curTitle = title;
      setActionCommand("unset");
      setAlignmentX(CENTER_ALIGNMENT);
      setMaximumSize(new Dimension(500, 374));
      setPreferredSize(new Dimension(500, 374));
    }

    public void addListener(ActionListener listener) {
      this.addActionListener(listener);
    }

    public void removeListener(ActionListener listener) {
      this.removeActionListener(listener);
    }

    public void setTitle(String oldTitle) {
      this.setText(oldTitle);
      curTitle = oldTitle;
    }

    public void updateTitle(String gameType) {
      String minZero;
      Calendar now = Calendar.getInstance();
      String month = now.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());
      int date = now.get(Calendar.DATE);
      int year = now.get(Calendar.YEAR);
      int hour = now.get(Calendar.HOUR);
      if(hour == 0)
        hour = 12;
      int min = now.get(Calendar.MINUTE);
      String AMPM = now.getDisplayName(Calendar.AM_PM, Calendar.LONG_FORMAT, Locale.getDefault());
      if(min < 10)
        minZero = ":0";
      else
        minZero = ":";
      String newTitle = gameType + " CHESS" + "   " + month + " " + date + ", " + year + "   " + hour + minZero + min + " " + AMPM;
      curTitle = newTitle;
      this.setText(newTitle);
    }
}
