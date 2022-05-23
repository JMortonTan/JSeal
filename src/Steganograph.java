import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Steganograph {
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int heightMod = 6;          //Indicates jump distance on x axis.
    private int widthMod = 6;           //Indicates jump distance on y axis.
    private int size;                   //Stores number amount of hidden material.
    private String filePath;            //String containing user indicated file path.

    private BufferedImage currentImg;

    //Overloaded Constructor.
    public Steganograph(String pathName, boolean coded) {
        this.setPath(pathName);
        this.setBufferedImage();
        this.setFlag(coded);
        this.setSize();
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

    //Open Image Method.
    public void setBufferedImage() {
        try {
            this.currentImg = ImageIO.read(new File(this.filePath));
        } catch (IOException e) {
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

    //Method to return size
    public int getSize() {
        return (this.size);
    }

    public void encodeFile (File file) {

    }



}
