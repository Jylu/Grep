import java.io.*;
import java.util.regex.*;

public abstract class BaseLineHandler implements LineHandler {
	protected BufferedReader reader;
	protected Pattern pattern;
	protected String line;
	protected String file;

	public BaseLineHandler(Pattern p, BufferedReader br, String f) {
		pattern = p;
		reader = br;
		file = f;
		line = null;
	}

	@Override
	public String readLine() throws IOException {
		line = reader.readLine();
		return line;
	}
}
