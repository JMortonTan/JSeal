import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class JSeal
{    
    //Main method.
	public static void main(String[] args)
	{
	    String command = args[0];



	    CodedImage workImage = new CodedImage(


		try
		{
			Scanner keyboard = new Scanner(System.in);
			String input;

			System.out.print("Encode or Decode?: ");
			input = keyboard.nextLine();
            
            if(input.equals("encode"))
            {
                System.out.print("Please enter the path to the image file you wish to use: ");
                String fileName = keyboard.nextLine();
                
                BufferedImage img = ImageIO.read(new File(fileName));
                Encode.save(Encode.code(img));
                System.out.println("Encryption complete.");
            }
            
            else if(input.equals("decode"))
            {
                System.out.print("Please enter the name of the file you want to decrypt: ");
                String fileName = keyboard.nextLine();
                
                BufferedImage dimg = ImageIO.read(new File(fileName));
                System.out.println(Decode.hexToString(Decode.decode(dimg)));
            }
            
            else
                System.out.println("Your input was invalid");
		}
        
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
