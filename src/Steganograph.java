import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Steganograph {
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int blockWidth;
    private int blockHeight;
    private int size;                   //Stores number amount of hidden material.
    private String filePath;            //String containing user indicated file path.

    private BufferedImage currentImg;

    //Overloaded Constructor.
    public Steganograph(String pathName, boolean coded) {
        this.setPath(pathName);
        this.setBufferedImage();
        this.setFlag(coded);
        this.setSize();

        System.out.println("Width: " + currentImg.getWidth());
        System.out.println("Height: " + currentImg.getHeight());
        System.out.println("BlockWide: " + this.blockWidth);
        System.out.println("BlockHeight: " + this.blockHeight);
        System.out.println("NumBlocks: " + this.getSize());
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
        this.blockHeight = currentImg.getHeight() / 3;
        this.blockWidth = currentImg.getWidth() / 3;

        //RESERVE ONE BLOCK AT THE VERY END TO INDICATE HOW MANY BLOCKS ARE USED.
        this.size = (currentImg.getWidth()/3 * currentImg.getHeight()/3) - 1;
    }

    //Method to return size
    public int getSize() {
        return (this.size);
    }

    public void encodeFile (WorkingFile file) {
        //RED: 256 -> 8 BIT
        //GREEN: 256 -> 8 BIT
        //BLUE: 256 -> 8 BIT
        // TOTAL: 24-BIT COLOUR
        // 3 bytes per colour.
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb = myWhite.getRGB();

        //SET BOTTOM-RIGHT CORNER BLOCK-SIZE
        currentImg.setRGB((this.blockWidth - 1) * 3 + 1, (this.blockHeight - 1 ) * 3 + 1, 25);

        //REPLACE IMAGE WITH DATA
        for (int xBlock = 0; xBlock < blockWidth; xBlock++) {
            for (int yBlock = 0; yBlock < blockHeight; yBlock++) {

                Block currentBlock = new Block((xBlock * 3) + 1, (yBlock * 3) + 1);
                int totalRGB = 0;
                for(int i = 0; i < 9; i++) {

                    if (i == 4) {
                        //Skip main coordinate since data is unrecoverable
                        continue;
                    } else{
                        Coordinate temp = currentBlock.getCoordinates()[i];
                        int x = temp.getX();
                        int y = temp.getY();

                        int currentRGB = currentImg.getRGB(x, y);
                        totalRGB += currentRGB;
                        System.out.println("xBlock: " + xBlock + "yBLock: " + yBlock + "ith: " + i);
                        System.out.println("currentRGB: " + currentRGB);
                    }
                }

                System.out.println("totalRGB: " + totalRGB);
                System.out.println("avgRGB: " + totalRGB / 8);

                currentImg.setRGB(currentBlock.getMain().getX(),
                        currentBlock.getMain().getY(),
                        totalRGB / 8);
            }
        }
    }



}
