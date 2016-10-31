import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CodedImage {
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int heightMod = 6;          //Indicates jump distance on x axis.
    private int widthMod = 6;           //Indicates jump distance on y axis.
    private int size;                   //Stores number amount of hidden material.
    private String filePath;            //String containing user indicated file path.
    private String secretMsg;           //String containing user indicated secret message.

    private BufferedImage currentImg;

    //Overloaded Constructor.
    public CodedImage(String pathName, boolean coded) {
        this.setPath(pathName);
        this.setBufferedImage();
        this.setFlag(coded);
        this.setSize();
    }

    //Open Image Method.
    public void setBufferedImage() {
        try {
            this.currentImg = ImageIO.read(new File(this.filePath));
        } catch (IOException e) {
        }
    }

    //Save file function.
    public void save(String filePath) {
        try {
            System.out.println(filePath);
            File outFile = new File(filePath);
            ImageIO.write(this.currentImg, "bmp", outFile);
            System.out.println("Save Successful.");
        } catch (IOException e) {
            System.out.println("Save Unsuccessful.");
        }
    }

    //Set boolean flag.
    public void setFlag(boolean indicater) {
        this.coded = indicater;
    }

    //Sets user instructed path name.
    public void setPath(String path) {
        this.filePath = path;
    }

    //This method returns int size which is the total amount of characters that can be stored.
    public void setSize() {
        this.size = (currentImg.getWidth() * currentImg.getHeight()) / (this.heightMod * this.widthMod) - 4;
    }

    //Method to change secret instance variable.
    public void setSecret(String secret) {
        this.secretMsg = secret + "****";
    }

    //Method to return secret instance variable.
    public String getSecret() {
        return (this.secretMsg);
    }

    //Method to return size
    public int getSize() {
        return (this.size);
    }

    //String to Color array
    public Color[] secretToColorArray() {
        //Stores secret as an array of characters.
        char[] charArray = getSecret().toCharArray();

        //Stores secret as an array of hex Strings.
        String[] hexArray = new String[charArray.length];

        //Stores secret as an array of Color.
        Color[] colorArray = new Color[getSize()];

        //Populate Hex Array.
        for (int counter = 0; counter < charArray.length; counter++) {
            hexArray[counter] = "00" + Integer.toHexString((int) charArray[counter]);
            System.out.println(hexArray[counter]);
        }

        //Populate Color Array.
        for (int counter2 = 0; counter2 < charArray.length; counter2++) {
            colorArray[counter2] = new Color((int) Long.parseLong(hexArray[counter2], 16));
        }

        return (colorArray);
    }

    public Color[] fileToColorArray(File file) throws IOException {
        int lim;
        File2Hex converter = new File2Hex();
        String hexString = converter.convertToHex(file);
        Scanner hexStringScanner = new Scanner(hexString);

        ArrayList<String> snipArray = new ArrayList<>();

        while(hexStringScanner.hasNext()) {
            snipArray.add(hexStringScanner.next());
        }

        //Add EOF indicator.
        for (int i = 0; i < 12; i++) {
            snipArray.add("FF");
        }

        lim = snipArray.size();

        //Stores secret as an array of hex Strings.
        String[] hexArray = snipArray.toArray(new String[0]);

        //Stores secret as an array of Color.
        Color[] colorArray = new Color[getSize()];

        //Populate Hex Array.
        for (int counter = 0; counter < lim; counter++) {
            hexArray[counter] = "00" + hexArray[counter];
        }

        //Populate Color Array.
        for (int counter2 = 0; counter2 < lim; counter2++) {
            colorArray[counter2] = new Color((int) Long.parseLong(hexArray[counter2], 16));
        }

        return (colorArray);
    }

    public void argbintArrayToHex(int[] argb, File file) {
        int length = 0;
        String[] stringArray = new String[argb.length];

        for (int counter = 0; counter < argb.length; counter++) {

            Color tempColor = new Color(argb[counter]);

            int a = tempColor.getAlpha();
            int r = tempColor.getRed();
            int g = tempColor.getGreen();
            int b = tempColor.getBlue();

            String hex = String.format("%02x%02x%02x", r, g, b);

            stringArray[counter] = hex;

            if (hex.equals("0000ff") &&
                    stringArray[counter - 1].equals("0000ff") &&
                    stringArray[counter - 2].equals("0000ff") &&
                    stringArray[counter - 3].equals("0000ff") &&
                    stringArray[counter - 4].equals("0000ff") &&
                    stringArray[counter - 5].equals("0000ff") &&
                    stringArray[counter - 6].equals("0000ff") &&
                    stringArray[counter - 7].equals("0000ff") &&
                    stringArray[counter - 8].equals("0000ff") &&
                    stringArray[counter - 9].equals("0000ff") &&
                    stringArray[counter - 10].equals("0000ff") &&
                    stringArray[counter - 11].equals("0000ff")) {
                length = counter;
                break;
            }
        }

        String[] finArray = new String[length + 1];

        for (int counter = 0; counter <= length; counter++) {
            finArray[counter] = stringArray[counter].substring(4);
        }

        System.out.println(Arrays.toString(finArray));

        byte[] byteArray = new byte[finArray.length - 12];

        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = Byte.parseByte(finArray[i], 16);
        }

        try {
            OutputStream os = new FileOutputStream(file);

            for (int i = 0; i < byteArray.length; i++) {
                os.write(byteArray[i]);
            }
            os.close();

        } catch (Exception e) {}
    }

    public String argbintArrayToString(int[] argb) {
        char[] charArray = new char[argb.length];

        for (int counter = 0; counter < argb.length; counter++) {
            Color tempColor = new Color(argb[counter]);

            int a = tempColor.getAlpha();
            int r = tempColor.getRed();
            int g = tempColor.getGreen();
            int b = tempColor.getBlue();

            String hex = String.format("%02x%02x%02x", r, g, b);

            int hexTransition = Integer.parseInt(hex, 16);

            charArray[counter] = (char) hexTransition;
        }

        String message = new String(charArray);

        message = secretTrim(message);

        return (message);
    }

    //Cut message at **** end
    public String secretTrim(String uncut) {
        String cut = uncut.substring(0, uncut.indexOf("****"));

        return (cut);
    }

    public void decodeFile(File file, File decoded) {
        File returnFile;
            //Declare & instantiate int array to size getSize()
            int[] rgbIntArray = new int[this.getSize()];

            int intCounter = 0;

            //Iterator through and populate ARGB int array.
            for (int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += heightMod) {
                for (int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += widthMod) {
                    if (intCounter < rgbIntArray.length) {
                        rgbIntArray[intCounter] = (currentImg.getRGB(xScroll, yScroll));
                        intCounter++;
                    }
                }
            }

            argbintArrayToHex(rgbIntArray, decoded);
    }

    public void encodeFile(File file) {
        try {
            Color[] colorArray = fileToColorArray(file);

            int colorCounter = 0;

            for (int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += this.heightMod) {
                for (int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += this.widthMod) {
                    if (colorCounter < colorArray.length - 1) {
                        Color tempColor = colorArray[colorCounter];

                        if (tempColor != null) {
                            int r = tempColor.getRed();
                            int g = tempColor.getGreen();
                            int b = tempColor.getBlue();

                            currentImg.setRGB(xScroll, yScroll, tempColor.getRGB());
                            colorCounter++;
                        }
                    }
                }
            }
        } catch (IOException e) {}
    }

    //Encoding protocol.
    public void encode() {
        Color[] colorArray = secretToColorArray();

        int colorCounter = 0;

        for (int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += this.heightMod) {
            for (int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += this.widthMod) {
                if (colorCounter < colorArray.length - 1) {
                    Color tempColor = colorArray[colorCounter];

                    if (tempColor != null) {
                        int r = tempColor.getRed();
                        int g = tempColor.getGreen();
                        int b = tempColor.getBlue();

                        currentImg.setRGB(xScroll, yScroll, tempColor.getRGB());
                        colorCounter++;
                    }
                }
            }
        }
    }

    //Decoding protocol.
    public void decode() {
        //Declare & instantiate int array to size getSize()
        int[] rgbIntArray = new int[this.getSize()];

        int intCounter = 0;

        //Iterator through and populate ARGB int array. 
        for (int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += heightMod) {
            for (int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += widthMod) {
                if (intCounter < rgbIntArray.length) {
                    rgbIntArray[intCounter] = (currentImg.getRGB(xScroll, yScroll));
                    intCounter++;
                }
            }
        }

        this.secretMsg = argbintArrayToString(rgbIntArray);
    }
}
