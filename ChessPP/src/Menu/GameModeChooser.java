package Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import Engine.*;
import GUI.*;
import Online.*;
import Options.Options;
public class GameModeChooser extends JPanel
{
    private JCheckBox b, pp;
    //public boolean started  = false;
    public GameModeChooser() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //add(Box.createVerticalStrut(280));

        JLabel jlabel = new JLabel("Chess++");
        jlabel.setFont(new Font("Algerian", Font.BOLD, 50));
        jlabel.setForeground(Color.BLUE);
        jlabel.setVerticalAlignment(jlabel.TOP);
        jlabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        add(jlabel);
        add(Box.createVerticalStrut(80));
        settingsSetup();

        b = new JCheckBox("Bullet");
        b.setBackground(new Color(230, 0, 230));
        b.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        b.setBorder(BorderFactory.createLineBorder(new Color(50, 0, 180), 3));
        b.setBorderPainted(true);
        add(Box.createVerticalStrut(10));
        add(b);

        pp = new JCheckBox("Plus Plus");
        pp.setBackground(new Color(230, 0, 230));
        pp.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        pp.setBorder(BorderFactory.createLineBorder(new Color(50, 0, 180), 3));
        pp.setBorderPainted(true);
        add(Box.createVerticalStrut(10));
        add(pp);
    }
    public void settingsSetup()
    {
        Button playButton = new Button("Play");
        playButton.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        playButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChessGui(new Board(new Options(b.isSelected(), pp.isSelected())));
            }
        });

        add(playButton);
    }
    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("imgMenu/Chess3.jpg").getImage(), 0, 0, 640, 480, this);
    }
}
