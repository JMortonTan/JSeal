import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class Decode
{
    static int heightSize = 6;      //Encrypting y skip variable.
	static int widthSize = 6;       //Encrypting x skip variable.
    
    //Encoding Protocol.
    public static BufferedImage code(BufferedImage img)
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
        
        System.out.print("Enter a message: ");
		input = keyboard.nextLine();
        
        String[] hexArray = charToHex(input);
        int hexCounter = 0;
        
        hexArray = colorKey(hexArray);
        
		for(int i = 0; i < img.getHeight(); i +=heightSize)
        {
			for(int j = 0; j < img.getWidth(); j +=widthSize)
            {   
                img.setRGB(j, i, hexStringToRGBInt(hexArray[hexCounter]));
                    
                if(hexCounter < (hexArray.length - 1))
                    hexCounter++;
            }
        }
        return(img);
	}
    
    //This is the initial modifier for preparing the String[] of hexcodes.
    public static String[] charToHex(String message)
	{
		String[] hexArray = new String[message.length()];

		for(int i = 0; i < message.length(); i++)
        {
			hexArray[i] = "00" + String.format("%04x", ((int) message.charAt(i)));
        }

		return hexArray;
	}
    
    //This is the final modifier for preparing the String[] of hexcodes.
    public static String[] colorKey(String[] charHex)
    {
        String[] colorHex = new String[charHex.length];
        
        for(int counter = 0; counter < charHex.length; counter++)
        {
            colorHex[counter] = "ff" + charHex[counter];
        }
        
        return(colorHex);
    }
    
    //This method converts the hexadecimal code back into an RGB int.
	public static int hexStringToRGBInt(String hex)
    {        
        Color hexColor = new Color(
            Integer.valueOf(hex.substring( 2, 4 ), 16),
            Integer.valueOf(hex.substring( 4, 6 ), 16),
            Integer.valueOf(hex.substring( 6, 8 ), 16));
        
        int r = hexColor.getRed();
        int g = hexColor.getGreen();
        int b = hexColor.getBlue();
        
        return 0xFF000000 | r | g | b;
    }
    
    //Save file function
    public static void save(BufferedImage img)
    {
    try {
        File outFile = new File("save.jpg");
        ImageIO.write(img, "jpg", outFile);
    } catch (IOException e){}
    }
}
