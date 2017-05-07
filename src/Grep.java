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

		switch (args.length) {
		case 1: // No extra arguments; Default to stdin
			grepStdIn(pattern);
			break;
		case 2: // One file
			grepSingleFile(pattern, args[1]);
			break;
		default: // Multiple files
			grepMultiFiles(pattern, Arrays.copyOfRange(args,  1,  args.length));
			break;
		}
	}

	// Stdin formatting
	private static void grepStdIn(Pattern pattern) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StdInLineHandler reader = new StdInLineHandler(pattern, br);

			while (reader.readLine() != null) {
				reader.printLine();
			}
		}
	}

	// Single file formatting; Uses StdInLineHandler due to having the same formatting as stdin
	private static void grepSingleFile(Pattern pattern, String file) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			StdInLineHandler reader = new StdInLineHandler(pattern, br);

			while (reader.readLine() != null) {
				reader.printLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	// Multiple files formatting
	private static void grepMultiFiles(Pattern pattern, String[] files) throws IOException {
		for (String file : files) {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
				FileLineHandler reader = new FileLineHandler(pattern, br, file);

				while (reader.readLine() != null) {
					reader.printLine();
				}
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
