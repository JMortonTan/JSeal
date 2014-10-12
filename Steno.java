/*
	John Joseph Losito
	RU Hack
	Steno project
*/

import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Steno
{
	static int heightSize = 6;
	static int widthSize = 6;
    
	public static void main(String[] args)
	{
		try
		{

			Scanner keyboard = new Scanner(System.in);
			String input;

			System.out.print("Code or decode: ");
			input = keyboard.nextLine();
            
            if(input.equals("code"))
            {
                BufferedImage img = ImageIO.read(new File("usa.jpg"));
                ImageIO.write(img, "jpg", new File("SameFlag.jpg"));
                save(code(img));
                System.out.println("Encryption complete.");
            }
            
            else if(input.equals("decode"))
            {
                System.out.print("Please enter the name of the file you want to decrypt: ");
                String fileName = keyboard.nextLine();
                
                BufferedImage img = ImageIO.read(new File(fileName));
                System.out.println(hexToString(decode(img)));
            }
            
            else
                System.out.println("Your input was invalid");
                                   
                            
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/*
		The calculateSize method
		param img The image that's being calculated
		return The s
	*/
	public static int calculateSize(BufferedImage img)
	{
		int size = (img.getWidth() * img.getHeight()) / (heightSize * widthSize);
        
		return size;
	}

	public static BufferedImage code(BufferedImage img)
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
        
        System.out.print("Enter a message: ");
		input = keyboard.nextLine();
        
        String[] hexArray = chartoHex(input);
        int hexCounter = 0;
        
        hexArray = colorKey(hexArray);
        
		for(int i = 0; i < img.getHeight(); i+=heightSize)
        {
			for(int j = 0; j < img.getWidth(); j+=widthSize)
            {
                img.setRGB(j, i, hexStringtoColor(hexArray[hexCounter]));
            }
        }
        return(img);
	}
           
    public static String[] decode(BufferedImage img)
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
        
        String[] hexArray = String[calculateSize(img)];
        int hexCounter = 0;

		for(int i = 0; i < img.getHeight(); i+=heightSize)
        {
			for(int j = 0; j < img.getWidth(); j+=widthSize)
            {
				String hexColor = Integer.toHexString(img.readRGB());
                hexColor = hexColor.subString(2, hexColor.length());
                    
                hexArray[hexCounter] = hexColor;
                hexCounter++;
            }
        }
        
        return(hexArray);
	}


	/*
		The charToHex method
		param message The cryptic message
		return The cryptic message in hex form
	*/
	public static String[] charToHex(String message)
	{
		String[] hexArray = new String[message.length];

		for(int i = 0; i < message.length; i++)
			hex[i] = String.format("%04x", (int) message.charAt(i));

		return hexArray;
	}

	public static String hexToString(String[] hexArray)
	{
        char[] secret = new char[hexArray.length];
        String secretMsg = "";
                             
        for(int counter = 0; counter < hexArray.length; counter++)
        {
            secret[counter] += (char)hexArray[counter].subString(2, hexArray[counter].length());
        }
        
        for(int counter = 0; counter < secret.length; counter++)
            secretMsg += secret[counter];
                             
        return(secretMsg);
	}

	public static Color hexStringToColor(String hex)
    {
	   hex = hex.Replace("#", "").ToLowerInvariant();

	
	   if (hex.Length == 6)
	   {
		  hex = "ff" + hex;
	   }
	   else if (hex.Length != 8)
	   {
		  throw new Exception("Error with hex String");
	   }

	   int a, r, g, b;
	   a = Convert.ToInt32(hex.Substring(0, 2), 16);
	   r = Convert.ToInt32(hex.Substring(2, 2), 16);
	   g = Convert.ToInt32(hex.Substring(4, 2), 16);
	   b = Convert.ToInt32(hex.Substring(6, 2), 16);

	   return Color.FromArgb(a, r, g, b);
    }
    
    public static String colorKey(String[] charHex)
    {
        String[] colorHex = String[charHex.length];
        
        for(int counter = 0; counter < charHex; counter++)
        {
            colorHex[counter] = "00" + charHex[counter];
        }
        
        return(colorHex);
    }
                              
    public static void open(String fileLocation)
    {
        
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