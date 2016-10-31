import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Encoder {
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int heightMod = 6;          //Indicates jump distance on x axis.
    private int widthMod = 6;           //Indicates jump distance on y axis.
    private int size;                   //Stores number amount of hidden material.
    private String filePath;            //String containing user indicated file path.
    private String secretMsg;           //String containing user indicated secret message.

    private BufferedImage currentImg;

    //Overloaded Constructor.
    public Encoder(String pathName, boolean coded) {
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
            ImageIO.write(this.currentImg, "png", outFile);
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
        }

        //Populate Color Array.
        for (int counter2 = 0; counter2 < charArray.length; counter2++) {
            colorArray[counter2] = new Color((int) Long.parseLong(hexArray[counter2], 16));
        }

        return (colorArray);
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
}
