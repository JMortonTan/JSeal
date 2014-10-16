import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class CodedImage extends BufferedImage
{
    private boolean coded;              //Flag to identify if CodedImage object is coded or not coded.
    private int heightMod = 6;          //Indicates jump distance on x axis.
    private int widthMod = 6;           //Indicates jump distance on y axis.
    private int size;                   //Stores number amount of hidden material.
    private String filePath;	        //String containing user indicated file path.
    private String secretMsg;	        //String containing user indicated secret message.

    //Overloaded Constructor.
    public CodedImage(String pathName, boolean coded)
    {
        this.setPath(pathName);
        this.setFlag(coded);
        this.setBufferedImage();
        this.setSize();
    }
    
    //Open Image Method.
    public void setBufferedImage()
    {
        super(ImageIO.read(new File(this.filePath)));
    }
    
    //Save file function.
    public static void save(String filePath)
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
        this.coded = indicate;
    }
    
    //Sets user instructed path name.
    public void setPath(String path)
    {
        this.filePath = path;
    }
    
    //This method returns int size which is the total amount of characters that can be stored.
    public static int setSize()
    {
	this.size = (this.getWidth() * this.getHeight()) / (this.heightMod * this.widthMod);
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
        char[] encodeRdy = new char[getSize()];        //Create blank char array of size getSize(), populate it to null character.
        String[] hexArray = new String[getSize()];

        //Copy chars from msgArray to new char array leaving leftover spaces as null characaters.
        for(int charCounter = 0; charCounter < msgArray.length; charCounter++)
            encodeRdy[charCounter] = msgArray[charCounter];
        
        //New encodeRdy char array to String hex array #AARRGGBB?/#RRGGBB?
        for(int rdyCounter = 0; rdyCounter < hexArray.length; rdyCounter++)
            hexArray[rdyCounter] = asciiToHex(encodeRdy[rdyCounter]);
        
        //4x ascii in one hex code?
        
        for(int yScroll = 0; yScroll < this.getHeight(); yScroll += heightMod)
        {
	    for(int xScroll = 0; xScroll < this.getWidth(); xScroll += widthMod)
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
        int[] rgbIntArray = new int[getSize()];
        //Declare & instantiate string hex array to size getSize()
        String[] hexArray = new String[getSize()];
        //Declare & instantiate char array to size getSize()
        char[] charArray = new char[getSize()];
        
        int intCounter = 0;
        
        //Iterator through and populate ARGB int array.
        do
        {
            for(int yScroll = 0; yScroll < this.getHeight(); yScroll += heightMod)
            {
                for(int xScroll = 0; xScroll < this.getWidth(); xScroll += widthMod)
                {   
                    rgbIntArray[intCounter] = (this.getRGB(xScroll, yScroll));
                    intCounter++;
                }
            }
        }while(intCounter < getSize());
        
        //ARGB int array convert to HEX array.
        
        
        //Hex array to char array.
        //Char array to String.
        //Cut null characters out of String end.
    }
    
/* Legacy Code
    //Encoding Protocol.
    public static BufferedImage code(BufferedImage img)
	{   
        String[] hexArray = charToHex(input);
        int hexCounter = 0;
        
        hexArray = colorKey(hexArray);
        
		for(int i = 0; i < img.getHeight(); i +=heightMod)
        {
			for(int j = 0; j < img.getWidth(); j +=widthMod)
            {   
                img.setRGB(j, i, hexStringToRGBInt(hexArray[hexCounter]));
                    
                if(hexCounter < (hexArray.length - 1))
                    hexCounter++;
            }
        }
        return(img);
	}
	
	//Decoding Protocol  
    public static String[] decode(BufferedImage img)
	{
        String[] hexArray = new String[calculateSize(img)];
        int hexCounter = 0;
        
		for(int i = 0; i < img.getHeight(); i += heightMod)
        {
			for(int j = 0; j < img.getWidth(); j += widthMod)
            {                
				String hexColor = Integer.toHexString(img.getRGB(j, i));
                hexArray[hexCounter] = hexColor;
                                            
                hexCounter++;
            }
        }
                
        return(hexArray);
	}
    
    //This is the initial modifier for preparing the String[] of hexcodes.
    public static String[] charToHex(String message)
	{
		String[] hexArray = new String[message.length()];

		for(int i = 0; i < message.length(); i++)
        {
			hexArray[i] = "00" + String.format("%04x", ((int) message.charAt(i)));
        }

		return hexArray;
	}
    
    //This is the final modifier for preparing the String[] of hexcodes.
    public static String[] colorKey(String[] charHex)
    {
        String[] colorHex = new String[charHex.length];
        
        for(int counter = 0; counter < charHex.length; counter++)
        {
            colorHex[counter] = "ff" + charHex[counter];
        }
        
        return(colorHex);
    }
    
    //This method converts the hexadecimal code back into an RGB int.
	public static int hexStringToRGBInt(String hex)
    {        
        Color hexColor = new Color(
            Integer.valueOf(hex.substring( 2, 4 ), 16),
            Integer.valueOf(hex.substring( 4, 6 ), 16),
            Integer.valueOf(hex.substring( 6, 8 ), 16));
        
        int r = hexColor.getRed();
        int g = hexColor.getGreen();
        int b = hexColor.getBlue();
        
        return 0xFF000000 | r | g | b;
    }
    
    //This method converts a String array holding all the hex values back into characters and finally a String.
	public static String hexToString(String[] hexArray)
	{
        char[] secret = new char[hexArray.length];
        String tempString;
        String secretMsg = "";
        
        System.out.println(secret.length);
                                     
        for(int counter = 0; counter < hexArray.length; counter++)
        {
            tempString = hexArray[counter];
            secret[counter] = (char)Integer.parseInt(tempString.substring(2,tempString.length()));
            System.out.print(secret[counter]);
        }
        
        for(int counter = 0; counter < secret.length; counter++)
            secretMsg += Character.toString(secret[counter]);
                             
        return(secretMsg);
	}
*/
}
