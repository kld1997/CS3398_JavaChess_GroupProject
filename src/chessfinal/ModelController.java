package chessfinal;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class ModelController
{

    public BufferedImage playerOnePieces[] = new BufferedImage[6];
    public BufferedImage playerTwoPieces[] = new BufferedImage[6];
    public BufferedImage menuIcons[] = new BufferedImage[10];
    public List<BufferedImage[]> pieceModels = new ArrayList<>();

    public ModelController()
    {
      readPieces();
      setPieceModel(0, 4);
      setPieceModel(1, 5);
      readMenuIcons();
    }

    public void setPieceModel(int team, int model)
    {
      if(team == 0)
        playerOnePieces = pieceModels.get(model);
      else
        playerTwoPieces = pieceModels.get(model);
    }

    void readPieces()
    {
      File modelPath = new File("img");
      File[] pathArray = modelPath.listFiles();
      Arrays.sort(pathArray);
      for(int m = 0; m < 5; m++)
      {
        try
        {
            BufferedImage biPieces = ImageIO.read(pathArray[m]);
            for(int i = 0; i < 2; i++)
            {
                BufferedImage pieces[] = new BufferedImage[6];
                for(int j = 0; j < 6; j++)
                {
                    pieces[j] = biPieces.getSubimage(j*(biPieces.getWidth()/6), i*(biPieces.getWidth()/6), (biPieces.getWidth()/6), (biPieces.getWidth()/6));
                }
                pieceModels.add(pieces);
            }
        }
        catch(Exception e)
        {
          System.out.println("Problem reading icon image files.");
        }
      }
    }

    void readMenuIcons()
    {
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
}
