import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
    }
     
    //Sample code for loading image into BufferedImage object.
    public static void load()
    {
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("usa.jpg"));
        } catch (IOException e){
        }
    }
    
    //Sample code for recieving dimension, should break into two when implemented.
    public static int[][] getDimension(BufferedImage img)
    {
        int[][] dimensions = new int[img.getHeight()][img.getWidth()];
        return(dimensions);
    }
    
    //Sample code for outputting buffered image (modified)
    public static void save(BufferedImage img)
    {
    try {
        File outFile = new File("save.jpg");
        ImageIO.write(img, "jpg", outFile);
    } catch (IOException e){}
    }
}