package Visuals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;

public class Images {

	public static BufferedImage pieces[][] = new BufferedImage[2][6];
	public static List<BufferedImage[][]> pieceModels = new ArrayList<>();
	public static BufferedImage menuIcons[] = new BufferedImage[10];

	public static void setUpImages() {                                          //Separates consolidated image file into different images

		File modelPath = new File("img");
      File[] pathArray = modelPath.listFiles();
      Arrays.sort(pathArray);
      for(int m = 0; m < 5; m++)
      {
        try
        {
            BufferedImage biPieces = ImageIO.read(pathArray[m]);
						BufferedImage pieceSet[][] = new BufferedImage[2][11];
            for(int i = 0; i < 2; i++)
            {
                for(int j = 0; j < 11; j++)
                {
                    pieceSet[i][j] = biPieces.getSubimage(j*(biPieces.getWidth()/11), i*(biPieces.getWidth()/11), (biPieces.getWidth()/11), (biPieces.getWidth()/11));
                }
            }
						pieceModels.add(pieceSet);
        }
        catch(Exception e)
        {
          System.out.println("Problem reading icon image files.");
        }
      }
			pieces = pieceModels.get(2);
    }

    public static void readMenuIcons() {
      try
      {
        BufferedImage tempIcons = ImageIO.read(new File("img/SmallModels.png"));
        for(int i = 0; i < 10; i++)
        {
          menuIcons[i] = tempIcons.getSubimage(i*(tempIcons.getWidth()/10), 0, (tempIcons.getWidth()/10), 25);
        }
      }
      catch(Exception e)
      {
        System.out.println("Problem reading menu image files.");
      }
    }

		public static void newImages(int tm, int mdl) {
			BufferedImage[][] temp = new BufferedImage[2][11];
			temp = pieceModels.get(mdl/2);
			BufferedImage[] im = new BufferedImage[11];
			im = temp[mdl%2];
			pieces[tm] = im;
		}
}
