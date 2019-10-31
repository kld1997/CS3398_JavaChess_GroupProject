import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Images {
	
	public static BufferedImage pieces[][] = new BufferedImage[2][6];
	
	public static void setUpImages() {                                          //Separates consolidated image file into different images
        		
		try
        {
            BufferedImage biPieces = ImageIO.read(new File("img/Pieces.png"));
            for(int i = 0; i < 2; i ++)
            {
                for(int j = 0; j < 6; j++)
                {
                    pieces[i][j] = biPieces.getSubimage(j*64, i*64, 64, 64);
                }
            }
        }
        catch(Exception e){}
    }
}