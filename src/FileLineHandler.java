import java.io.*;
import java.util.regex.*;

public class FileLineHandler extends BaseLineHandler {

	public FileLineHandler(Pattern p, BufferedReader br, String f) {
		super(p, br, f);
	}

	@Override
	public void printLine() {
		Matcher matcher = pattern.matcher(line);

		if (matcher.find()) {
			System.out.println(file + ": " + line);
		}
	}

}
