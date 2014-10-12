import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        load();
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Test Unicode: ");
        String store = keyboard.next();
        
        System.out.println(store);
    }
     
    //Sample code for loading image into BufferedImage object.
    public static void load()
    {
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("usa.jpg"));
        } catch (IOException e){
            System.out.println("Sorry the file you indicated does not exist.");
        }
        
        img.setRGB(0,0,-4440764);
        
        save(img);
        
        //Code to get RGB integer.
        //Get Hex String of color.
        System.out.println(Integer.toHexString(img.getRGB(246,129)));
        System.out.println(img.getRGB(0,0));
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