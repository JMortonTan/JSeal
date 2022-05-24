import com.beust.jcommander.JCommander;
import java.io.File;
import java.util.Scanner;


public class JSeal {
    //Create a scanner for user input.
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        //Argument Handler
        Args myArgs = new Args();
        JCommander.newBuilder()
                .addObject(myArgs)
                .build()
                .parse(args);

        //Disallow both encode mode and decode mode
        if (myArgs.getEncode() == true && myArgs.getDecode() == true) {
            System.out.println("You must select either one encode or decode at a time.");
            System.exit(0);
        } else if (myArgs.getEncode() == true) {
            encodeFile();
        } else if (myArgs.getDecode() == true) {
            decodeFile();
        }
    }

    public static void encodeFile() {
        Steganograph workImage;      //Declares CodedImage object.
        String savePath;
        String path;

        System.out.println("What is the path of your image?");
        path = keyboard.nextLine();

        workImage = new Steganograph(path, false);

        System.out.println("What is the path of your file?");
        path = keyboard.nextLine();

        //Encode.
        workImage.encodeFile(new WorkingFile(path));
        System.out.println("Encoding complete.");

        //Save.
        System.out.println("Please name the encoded file: ");
        savePath = path.substring(0, path.lastIndexOf('/') + 1) + "" + keyboard.nextLine();
        workImage.save(savePath);
    }

    public static void decodeFile() {
//        CodedImage workImage;      //Declares CodedImage object.
//        String savePath;
//
//        System.out.println("What is the path of your image?");
//        String path = keyboard.nextLine();
//
//        workImage = new CodedImage(path, false);
//
//        System.out.println("Give a name to decoded file:");
//        savePath = keyboard.nextLine();
//
//        //Create decoded file
//        File decoded = new File(savePath);
//
//        workImage.decodeFile(new File(path), decoded);

        //savePath = path.substring(0, path.lastIndexOf('/') + 1) + "" + savePath;
        //workImage.save(savePath);
    }
}
