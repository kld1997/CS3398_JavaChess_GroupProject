package Play;
import javax.swing.JApplet;


public class SoundApplet extends JApplet {
	
	static SoundApplet applet;
	private JavaSound demo;
	
	public void init() {
		
		applet = this;
		String media = "./audio";
		String param = null;
		if ((param = getParameter("dir")) != null) {
			media = param;
		}
		getContentPane().add("Center", demo = new JavaSound(media));
	}
	
	public void start() {
		demo.open();
	}
	
	public void stop() {
		demo.close();
	}

}
