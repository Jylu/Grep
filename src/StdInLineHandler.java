import java.io.*;
import java.util.regex.*;

public class StdInLineHandler extends BaseLineHandler {

	public StdInLineHandler(Pattern p, BufferedReader br) {
		super(p, br, null);
	}

	@Override
	public void printLine() {
		Matcher matcher = pattern.matcher(line);

		if (matcher.find()) {
			System.out.println(line);
		}
	}

}
