import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class File2Hex
{
    public static String convertToHex(File file) throws IOException {
        String outString;
        InputStream is = new FileInputStream(file);

        int bytesCounter =0;
        int value = 0;
        StringBuilder sbHex = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();

        while ((value = is.read()) != -1) {
            //convert to hex value with "X" formatter
            sbHex.append(String.format("%02X ", value));

            //if 16 bytes are read, reset the counter,
            //clear the StringBuilder for formatting purpose only.
            if(bytesCounter==15){
                sbResult.append(sbHex);
                sbHex.setLength(0);
                bytesCounter=0;
            }else{
                bytesCounter++;
            }
        }

        //if still got content
        if(bytesCounter!=0){
            //add spaces more formatting purpose only
            for(; bytesCounter<16; bytesCounter++){
                //1 character 3 spaces
                sbHex.append("   ");
            }
            sbResult.append(sbHex);
        }

        outString = sbResult.toString();

        is.close();
        return(outString);
    }

    public static void main(String[] args) throws IOException
    {
        System.out.print(convertToHex(new File("README.md")));
    }
}