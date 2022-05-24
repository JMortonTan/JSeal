import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class WorkingFile extends File {

    //Match all Super-Constructors
    public WorkingFile(String pathname) {
        super(pathname);
    }

    public WorkingFile(String parent, String child) {
        super(parent, child);
    }

    public WorkingFile(File parent, String child) {
        super(parent, child);
    }

    public WorkingFile(URI uri) {
        super(uri);
    }

    public String toHex() throws IOException {
        String hexString;
        InputStream is = new FileInputStream(this);

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

        hexString = sbResult.toString();

        is.close();

        return hexString;
    }
}
