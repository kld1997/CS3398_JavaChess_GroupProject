//Kody Davis
package Visuals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
import GUI.*;


public class ColorMenu extends JMenuItem
{
      public static JFrame frame;
      static public ChessSquare[][] boardTiles = new ChessSquare[8][8];
      static private JButton[][] previewArray = new JButton[8][8];
      static private Color buttonColorOne;
      static private Color buttonColorTwo;
      static private Color boardColorOne = Color.WHITE;
      static private Color boardColorTwo = Color.BLACK;
      public JColorChooser firstColor = new JColorChooser(boardColorOne);
      public JColorChooser secondColor = new JColorChooser(boardColorTwo);

      public ColorMenu(String title, ChessSquare[][] tiles) {
          super(title);
          boardTiles = tiles;
          createGUI();
          this.addActionListener(new ActionListener()
          {
              @Override
              public void actionPerformed(ActionEvent actionEvent)
              {
                  buttonColorOne = boardColorOne;
                  buttonColorTwo = boardColorTwo;
                  firstColor.setColor(boardColorOne);
                  secondColor.setColor(boardColorTwo);
                  setPreviewColor(boardColorOne, boardColorTwo);
                  frame.setVisible(true);
              }
           });
      }


      private void createGUI() {

        buttonColorOne = Color.WHITE;
        buttonColorTwo = Color.BLACK;
        JPanel colorPanel = new JPanel(new GridBagLayout());
        JPanel preview = createPreviewPane();
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(40, 20));

        firstColor.setPreviewPanel(new JPanel());
        secondColor.setPreviewPanel(new JPanel());
        firstColor.getSelectionModel().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                buttonColorOne = firstColor.getColor();
                setPreviewColor(buttonColorOne, buttonColorTwo);
            }
        });
        secondColor.getSelectionModel().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                buttonColorTwo = secondColor.getColor();
                setPreviewColor(buttonColorOne, buttonColorTwo);
            }
        });
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                setBoardColor(buttonColorOne, buttonColorTwo);
                boardColorOne = buttonColorOne;
                boardColorTwo = buttonColorTwo;
                frame.setVisible(false);
            }
         });
        firstColor.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Color 1"));
        secondColor.setBorder(BorderFactory.createTitledBorder(
                                              "Choose Color 2"));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.weightx = 0.5;
        c.weighty = 0.5;
        colorPanel.add(firstColor, c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridheight = GridBagConstraints.REMAINDER;
        colorPanel.add(secondColor, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weighty = 0.5;
        c.gridheight = 2;
        c.ipady = 200;
        c.ipadx = 50;
        c.insets = new Insets(20, 20, 20, 20);
        colorPanel.add(preview, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.ipady = 10;
        c.ipadx = 20;
        c.weighty = 1;
        c.gridy = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets = new Insets(20, 0, 10, 10);
        colorPanel.add(okButton, c);

          frame = new JFrame("Board Colors");
          frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

          frame.setContentPane(colorPanel);

          frame.pack();
          frame.setVisible(false);
      }

      static JPanel createPreviewPane()
      {
        JPanel previewPane = new JPanel(new GridLayout(8, 8, 0, 0));
        previewPane.setBorder(BorderFactory.createTitledBorder(
                                             "Preview"));
          for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              previewArray[i][j] = new JButton();
              previewPane.add(previewArray[i][j]);
            }
          }
          setPreviewColor(Color.WHITE, Color.BLACK);
          return previewPane;
      }

      static void setPreviewColor(Color one, Color two)
      {
        for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0)))
                previewArray[i][j].setBackground(one);
              else
                previewArray[i][j].setBackground(two);
            }
          }
      }

      static void setBoardColor(Color one, Color two)
      {
        for(int i = 0; i < 8; i++)
          {
            for(int j = 0; j < 8; j++)
            {
              if(!((i % 2 == 0) ^ (j % 2 == 0))) {
                boardTiles[i][j].setBackground(one);
                if(boardTiles[i][j].getBorder() != null)
                  boardTiles[i][j].Highlight();
              }
              else {
                boardTiles[i][j].setBackground(two);
                if(boardTiles[i][j].getBorder() != null)
                  boardTiles[i][j].Highlight();
              }
            }
          }
      }


}
