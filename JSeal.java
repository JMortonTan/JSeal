import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class JSeal
{
    //Create a scanner for user input.
    private static Scanner keyboard = new Scanner(System.in);
    
    //Main method.
	public static void main(String[] args)
	{
	    String command = args[0];  //Stores command given from command argument.
        CodedImage workImage;      //Declares CodedImage object.
        
        //Change command argument to lower case to ensure different capitalization combinationss are congruent.
        command = command.toLowerCase();
        
        if(command.equals("encode"))
        {
            //Ask user for file path of image.
            System.out.println("What is the name of the image you wish to use? ");
            workImage = new CodedImage(keyboard.nextLine(), false);
            
            do
            {
                //Ask user for secret message.
                System.out.println("Your secret message can only have" + workImage.getSize + " characters.");
                System.out.println("What is your secret message? ");
            
                //Need to confirm user input is within bounds.
                String holdString = keboard.nextLine();
            } while(!check(holdString))
            
            //Set secret.
            workImage.setSecret(keyboard.nextLine, workImage.getSize);
              
            //Encode.
            workImage.encode();
            
            //Save.
            workImage.save();
        }
        
        else if(command.equals("decode"))
        {
            //Prompt user for coded image path.
            System.out.println("What is the name of the image you wish to use? ");
            workImage = new CodedImage(keyboard.nextLine(), true);
            
            //Decode.
            workImage.decode();
            
            //Display secret message.
            System.out.println("Your encoded message was: " + workImage.getSecret());
        }
        
        else if(command.equals("help"))
        {
            //Display help message.
            System.out.println("JSeal requires one of two possible arguments. ""Encode"" or ""Decode"".");
        }
        
        else
        {
            //Display error message.
            System.out.println("The command you entered does not exist." +
                               "\nPlease enter the command ""JSeal help"" for a list of commands.");
        }
    }
    
    //Checks user input for size.
    public static boolean check(String input, int size)
    {
        if(input.length <= size)
            return(true);
        else
            return(false);
    }

    
/*  LEGACY CODE
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
*/
}
