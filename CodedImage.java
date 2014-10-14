import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class CodedImage extends BufferedImage
{
    private int heightMod = 6;      //Indicates jump distance on x axis.
    private int widthMod = 6;       //Indicates jump distance on y axis.

    private String filePath;	    //String containing user indicated file path.

    private String secretMsg;	    //String containing user indicated secret message.

    //Default Constructor
    public CodedImage()
    {
    }

    //Overloaded Constructor
    public CodedImage()
    {

    }

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
        
		for(int i = 0; i < img.getHeight(); i +=heightMod)
        {
			for(int j = 0; j < img.getWidth(); j +=widthMod)
            {   
                img.setRGB(j, i, hexStringToRGBInt(hexArray[hexCounter]));
                    
                if(hexCounter < (hexArray.length - 1))
                    hexCounter++;
            }
        }
        return(img);
	}
	
	//Decoding Protocol  
    public static String[] decode(BufferedImage img)
	{
        String[] hexArray = new String[calculateSize(img)];
        int hexCounter = 0;
        
		for(int i = 0; i < img.getHeight(); i += heightMod)
        {
			for(int j = 0; j < img.getWidth(); j += widthMod)
            {                
				String hexColor = Integer.toHexString(img.getRGB(j, i));
                hexArray[hexCounter] = hexColor;
                                            
                hexCounter++;
            }
        }
                
        return(hexArray);
	}
	
	//This method returns int size which is the total amount of characters that can be stored.
    public static int calculateSize(BufferedImage img)
	{
		int size = (img.getWidth() * img.getHeight()) / (heightMod * widthMod);
        
		return size;
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
    
    //This method converts a String array holding all the hex values back into characters and finally a String.
	public static String hexToString(String[] hexArray)
	{
        char[] secret = new char[hexArray.length];
        String tempString;
        String secretMsg = "";
        
        System.out.println(secret.length);
                                     
        for(int counter = 0; counter < hexArray.length; counter++)
        {
            tempString = hexArray[counter];
            secret[counter] = (char)Integer.parseInt(tempString.substring(2,tempString.length()));
            System.out.print(secret[counter]);
        }
        
        for(int counter = 0; counter < secret.length; counter++)
            secretMsg += Character.toString(secret[counter]);
                             
        return(secretMsg);
	}
}
