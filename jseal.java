import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class jseal
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
        
        String[] hexArray = charToHex(input);
        int hexCounter = 0;
        
        hexArray = colorKey(hexArray);
        
		for(int i = 0; i < img.getHeight(); i +=heightSize)
        {
			for(int j = 0; j < img.getWidth(); j +=widthSize)
            {
                img.setRGB(j, i, hexStringToRGBInt(hexArray[hexCounter]));
                hexCounter++;
            }
        }
        return(img);
	}
           
    public static String[] decode(BufferedImage img)
	{
		Scanner keyboard = new Scanner(System.in);
		String input;
        
        String[] hexArray = new String[calculateSize(img)];
        int hexCounter = 0;

		for(int i = 0; i < img.getHeight(); i+=heightSize)
        {
			for(int j = 0; j < img.getWidth(); j+=widthSize)
            {
				String hexColor = Integer.toHexString(img.getRGB(j, i));
                hexColor = hexColor.substring(2, hexColor.length());
                    
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
		String[] hexArray = new String[message.length()];

		for(int i = 0; i < message.length(); i++)
			hexArray[i] = "00" + String.format("%04x", (int) message.charAt(i));

		return hexArray;
	}

	public static String hexToString(String[] hexArray)
	{
        char[] secret = new char[hexArray.length];
        String secretMsg = "";
                             
        for(int counter = 0; counter < hexArray.length; counter++)
        {
            secret[counter] += hexArray[counter].substring(2, hexArray.length).charAt(0);
        }
        
        for(int counter = 0; counter < secret.length; counter++)
            secretMsg += secret[counter];
                             
        return(secretMsg);
	}

	public static int hexStringToRGBInt(String hex)
    {
        //Test code.
        System.out.println(hex);
        
        Color hexColor = new Color(
            Integer.valueOf(hex.substring( 1, 3 ), 16 ),
            Integer.valueOf(hex.substring( 3, 5 ), 16 ),
            Integer.valueOf(hex.substring( 5, 7 ), 16 ));
        
        int r = hexColor.getRed();
        int g = hexColor.getGreen();
        int b = hexColor.getBlue();
        
        return 0xFF000000 | r | g | b;
        
        //int rgb = ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff);
        //return(rgb);
    }
    
    public static String[] colorKey(String[] charHex)
    {
        String[] colorHex = new String[charHex.length];
        
        for(int counter = 0; counter < charHex.length; counter++)
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