import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class CodedImage
{
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int heightMod = 6;          //Indicates jump distance on x axis.
    private int widthMod = 6;           //Indicates jump distance on y axis.
    private int size;                   //Stores number amount of hidden material.
    private String filePath;	        //String containing user indicated file path.
    private String secretMsg;	        //String containing user indicated secret message.
    
    private BufferedImage currentImg;

    //Overloaded Constructor.
    public CodedImage(String pathName, boolean coded)
    {
		this.setBufferedImage();
        this.setPath(pathName);
        this.setFlag(coded);
        this.setSize();
    }
    
    //Open Image Method.
    public void setBufferedImage()
    {
		try
		{
			this.currentImg = ImageIO.read(new File(this.filePath));
		} catch (IOException e){}
    }
    
    //Save file function.
    public void save(String filePath)
    {
        try 
        {
            File outFile = new File(filePath);
            ImageIO.write(this.currentImg, "jpg", outFile);
        } catch (IOException e){}
    }
    
    //Set boolean flag.
    public void setFlag(boolean indicater)
    {
        this.coded = indicater;
    }
    
    //Sets user instructed path name.
    public void setPath(String path)
    {
        this.filePath = path;
    }
    
    //This method returns int size which is the total amount of characters that can be stored.
    public void setSize()
    {
		this.size = (currentImg.getWidth() * currentImg.getHeight()) / (this.heightMod * this.widthMod);
    }
    
    //Method to change secret instance variable.
    public void setSecret(String secret)
    {
        this.secretMsg = secret;
    }
    
    //Method to return secret instance variable.
    public String getSecret()
    {
        return(this.secretMsg);
    }

    //Method to return size
    public int getSize()
    {
		return(this.size);
    }
    
    //Ascii to Hex
    public String asciiToHex(char ascii)
    {
        String hex;
        
        //Ascii to hex
        
        return(hex);
    }
    
    //Encoding protocol.
    public void encode()
    {
        char[] msgArray = secretMsg.toCharArray();     //Array of message Characters.
        char[] encodeRdy = new char[this.getSize()];        //Create blank char array of size getSize(), populate it to null character.
        String[] hexArray = new String[this.getSize()];

        //Copy chars from msgArray to new char array leaving leftover spaces as null characaters.
        for(int charCounter = 0; charCounter < msgArray.length; charCounter++)
            encodeRdy[charCounter] = msgArray[charCounter];
        
        //New encodeRdy char array to String hex array #AARRGGBB?/#RRGGBB?
        for(int rdyCounter = 0; rdyCounter < hexArray.length; rdyCounter++)
            hexArray[rdyCounter] = asciiToHex(encodeRdy[rdyCounter]);
        
        //4x ascii in one hex code?
        
        for(int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += heightMod)
        {
	    for(int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += widthMod)
            {   
                //SET CORD. RGB TO CHAR HEX according to String hex array.

                //HEX SCROLL?
            }
        }
    }
    
    //Decoding protocol.
    public void decode()
    {
        //Declare & instantiate int array to size getSize()
        int[] rgbIntArray = new int[this.getSize()];
        //Declare & instantiate string hex array to size getSize()
        String[] hexArray = new String[this.getSize()];
        //Declare & instantiate char array to size getSize()
        char[] charArray = new char[this.getSize()];
        
        int intCounter = 0;
        
        //Iterator through and populate ARGB int array.
        do
        {
            for(int yScroll = 0; yScroll < currentImg.getHeight(); yScroll += heightMod)
            {
                for(int xScroll = 0; xScroll < currentImg.getWidth(); xScroll += widthMod)
                {   
                    rgbIntArray[intCounter] = (currentImg.getRGB(xScroll, yScroll));
                    intCounter++;
                }
            }
        }while(intCounter < this.getSize());
        
        //ARGB int array convert to HEX array.
        
        
        //Hex array to char array.
        //Char array to String.
        //Cut null characters out of String end.
    }
}
