import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("usa.jpg"));
        } catch (IOException e){
        }
        
        
        
    }
}