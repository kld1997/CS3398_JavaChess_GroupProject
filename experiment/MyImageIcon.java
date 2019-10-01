package chess;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MyImageIcon extends ImageIcon {

    private String name;

    public MyImageIcon(BufferedImage piece, String imageName){
       super(piece);
       name = imageName;
    }

    // Override equals method and check if names are equal
    @Override
    public boolean equals(MyImageIcon other){
        if (other == null) return false;
        return name.equals(otherImage.name);
    }

/*    @Override
    public String getDescription(){
        return name;
    }
*/
}
