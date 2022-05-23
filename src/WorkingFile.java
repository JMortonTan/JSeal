import java.io.File;
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


}
