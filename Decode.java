import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Decode
{
    static int heightSize = 6;      //Decrypting y skip variable.
	static int widthSize = 6;       //Decypting x skip variable.
    
    //Decoding Protocol  
    public static String[] decode(BufferedImage img)
	{
        String[] hexArray = new String[calculateSize(img)];
        int hexCounter = 0;

		for(int i = 0; i < img.getHeight(); i += heightSize)
        {
			for(int j = 0; j < img.getWidth(); j += widthSize)
            {
				String hexColor = Integer.toHexString(img.getRGB(j, i));
                
                hexArray[hexCounter] = hexColor;
                
                System.out.println(hexColor);
                
                if(hexCounter < (hexArray.length - 1))
                    hexCounter++;
            }
        }
        
        return(hexArray);
	}
    
    //This method returns int size which is the total amount of characters that can be stored.
    public static int calculateSize(BufferedImage img)
	{
		int size = (img.getWidth() * img.getHeight()) / (heightSize * widthSize);
        
		return size;
	}
    
     //This method converts a String array holding all the hex values back into characters and finally a String.
	public static String hexToString(String[] hexArray)
	{
        char[] secret = new char[hexArray.length];
        String tempString;
        String secretMsg = "";
                             
        for(int counter = 0; counter < hexArray.length; counter++)
        {
            tempString = hexArray[counter];
            secret[counter] = (char)Integer.parseInt(hexArray[counter].substring(6,8));
        }
        
        for(int counter = 0; counter < secret.length; counter++)
            secretMsg += secret[counter];
                             
        return(secretMsg);
	}
}