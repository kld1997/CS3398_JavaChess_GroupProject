package MusicPlayer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.File;
import java.util.Vector;
import javax.sound.sampled.*;
import javax.sound.midi.*;

public class JavaSound extends JPanel implements ChangeListener, Runnable {

	Vector demos = new Vector(4);
    JTabbedPane tabPane = new JTabbedPane();
    int width = 760, height = 500;
    int index;

    
    public JavaSound (String audioDirectory) {
    	
    	setLayout(new BorderLayout());
    	JMenuBar menuBar = new JMenuBar();
    
    	
    	/*
    	 * this will create a menu bar for the music player where the user will
    	 * be able to exit the music player 
    	 * 
    	 */
        if (SoundApplet.applet == null) {
            JMenu fileMenu = (JMenu) menuBar.add(new JMenu("File"));
            JMenuItem item = (JMenuItem) fileMenu.add(new JMenuItem("Exit"));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { System.exit(0); }
            });
        }
        
        
        EmptyBorder eb = new EmptyBorder(5,5,5,5);
        BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
        CompoundBorder cb = new CompoundBorder(eb,bb);
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new CompoundBorder(cb,new EmptyBorder(0,0,90,0)));
        final Juke juke = new Juke(audioDirectory);
        p.add(juke);
        demos.add(juke);
        tabPane.addTab("Juke Box", p);

        new Thread(this).start();
        add(tabPane, BorderLayout.CENTER);
      
    }
	
    public void stateChanged(ChangeEvent e) {
        close();
        System.gc();
        index = tabPane.getSelectedIndex();
        open();
    }

    public void close() {
        ((ControlContext) demos.get(index)).close();
    }

    public void open() {
        ((ControlContext) demos.get(index)).open();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void run() {
        EmptyBorder eb = new EmptyBorder(5,5,5,5);
        BevelBorder bb = new BevelBorder(BevelBorder.LOWERED);
        CompoundBorder cb = new CompoundBorder(eb,bb);
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new CompoundBorder(cb,new EmptyBorder(0,0,90,0)));
        CapturePlayback capturePlayback = new CapturePlayback();
        demos.add(capturePlayback);
        p.add(capturePlayback);
        tabPane.addTab("Capture/Playback", p);
    }
	
	public static void main (String [] args) {
		
        String media = "./audio";
        if (args.length > 0) {
            File file = new File(args[0]);
            if (file == null && !file.isDirectory()) {
                System.out.println("usage: offline audio Directory");
            } 
            else {
				media = args[0];
            	 }

        final JavaSound demo = new JavaSound(media);
        JFrame f = new JFrame("Java Sound Demo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { demo.open(); }
            public void windowIconified(WindowEvent e) { demo.close(); }
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(d.width/2 - demo.width/2, d.height/2 - demo.height/2);
        f.setSize(new Dimension(demo.width, demo.height));
        f.setVisible(true);
	}
	
	
}
}
