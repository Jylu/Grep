import java.io.*;
import java.util.Arrays;
import java.util.regex.*;

public class Grep {

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("Usage: PATTERN [FILE]");
			return;
		}

		Pattern pattern = null;
		try {
			pattern = Pattern.compile(args[0]);
		} catch (PatternSyntaxException e) {
			System.err.println("Invalid regular expression");
			System.exit(1);
		}

		if (args.length == 1) { // No extra arguments; Default to stdin
			try (LineHandler reader = new StdInLineHandler()) {
				grep(pattern, reader);
			}
		}
		else { // files
			try (LineHandler reader = new FileLineHandler(Arrays.copyOfRange(args, 1, args.length))) {
				grep(pattern, reader);
			}
		}
	}

	private static void grep(Pattern pattern, LineHandler reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);

			if (matcher.find()) {
				reader.printLine();
			}
		}
	}
}
