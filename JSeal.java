import java.awt.image.BufferedImage;
import java.util.Scanner;

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

			//Declare holdString
			String holdString;
            
            do
            {
                //Ask user for secret message.
                System.out.println("Your secret message can only have " + workImage.getSize() + " characters.");
                System.out.println("What is your secret message? ");
            
                //Need to confirm user input is within bounds.

                holdString = keyboard.nextLine();
            } while(!check(holdString, workImage.getSize()));

            //Set secret.
            workImage.setSecret(holdString);
              
            //Encode.
            workImage.encode();
            System.out.println("Encoding complete.");
            
            //Save.
            System.out.println("Please name the encoded file: ");
            workImage.save(keyboard.nextLine());
        }
        
        else if(command.equals("decode"))
        {
            //Prompt user for coded image path.
            System.out.println("What is the name of the image you wish to use? ");
            workImage = new CodedImage(keyboard.nextLine(), true);
            
            //Decode.
            workImage.decode();
            System.out.println("Decoding complete.");
            
            //Display secret message.
            System.out.println("Your encoded message was: \n" + workImage.getSecret());
        }
        
        else if(command.equals("help"))
        {
            //Display help message.
            System.out.println("JSeal requires one of two possible arguments. \"Encode\" or \"Decode\".");
        }
        
        else
        {
            //Display error message.
            System.out.println("The command you entered does not exist." +
                               "\nPlease enter the command \"JSeal help\" for a list of commands.");
        }
    }
    
    //Checks user input for size.
    public static boolean check(String input, int size)
    {
        if(input.length() <= size)
            return(true);
        else
            return(false);
    }
}
