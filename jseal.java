import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class jseal
{    
    //Main method.
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
                Encode.save(Encode.code(img));
                System.out.println("Encryption complete.");
            }
            
            else if(input.equals("decode"))
            {
                System.out.print("Please enter the name of the file you want to decrypt: ");
                String fileName = keyboard.nextLine();
                
                BufferedImage img = ImageIO.read(new File(fileName));
                System.out.println(Decode.hexToString(Decode.decode(img)));
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
