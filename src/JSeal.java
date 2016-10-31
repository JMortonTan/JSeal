import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class JSeal {
    //Create a scanner for user input.
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int input;
        System.out.println("Enter 1 for encode, 2 for decode: ");
        input = keyboard.nextInt();
        keyboard.nextLine();

        switch(input) {
            case 1:
                encodeFile();
                break;
            case 2:
                decodeFile();
                break;
        }
    }

    public static void encodeFile() {
        CodedImage workImage;      //Declares CodedImage object.
        String password;
        String savePath;
        String path;

        System.out.println("What is the path of your image?");
        path = keyboard.nextLine();

        workImage = new CodedImage(path, false);

        System.out.println("What is the path of your file?");
        path = keyboard.nextLine();

        workImage.encodeFile(new File(path));

        /////////////////////////
        //Set key.
        System.out.println("Please select a passcode to lock this file: ");
        password = keyboard.nextLine();



        /////////////////////////

        //Encode.
        System.out.println("Encoding complete.");

        //Save.
        System.out.println("Please name the encoded file: ");
        savePath = path.substring(0, path.lastIndexOf('/') + 1) + "" + keyboard.nextLine();
        workImage.save(savePath);
    }

    public static void decodeFile() {
        CodedImage workImage;      //Declares CodedImage object.
        String savePath;

        System.out.println("What is the path of your image?");
        String path = keyboard.nextLine();

        workImage = new CodedImage(path, false);

        System.out.println("Give a name to decoded file:");
        savePath = keyboard.nextLine();

        //Create decoded file
        File decoded = new File(savePath);

        workImage.decodeFile(new File(path), decoded);

        //savePath = path.substring(0, path.lastIndexOf('/') + 1) + "" + savePath;
        //workImage.save(savePath);
    }
}
