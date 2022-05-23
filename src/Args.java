import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Args {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-d", "--decode" },
            description = "Indicates flag for decoding mode.")
    private boolean decode;

    @Parameter(names = { "-e", "--encode" },
            description = "Indicates flag for encoding mode.")
    private boolean encode;

    @Parameter(names = { "-f", "--file" }, description = "File Path")
    private String path;

    public boolean getEncode() {
        return encode;
    }

    public boolean getDecode() {
        return decode;
    }

    public String getPath() {
        return path;
    }
}