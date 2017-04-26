import java.io.*;
import java.util.regex.*;

public class Grep {

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Usage: PATTERN [FILE]");
			return;
		}

		Pattern pattern = null;
		try {
			pattern = Pattern.compile(args[0]);
		} catch (PatternSyntaxException e) {
			System.err.println("Invalid regular expression");
			System.exit(1);
		}

		BufferedReader br = null;
		int i = 1;
		do {
			try {
				if (args.length == 1) {
					// No files; Default to System.in
					br = new BufferedReader(new InputStreamReader(System.in));
				}
				else {
					// Open file
					br = new BufferedReader(new InputStreamReader(new FileInputStream(args[i])));
				}

				String s;
				while ((s = br.readLine()) != null) {
					Matcher matcher = pattern.matcher(s);

					// Pattern matched; print line
					if (matcher.find()) {
						if (args.length <= 2) {
							// Don't need to specify file with System.in or with just one file
							System.out.println(s);
						}
						else {
							// Additionally print out file the line was matched in
							System.out.println(args[i] + ": " + s);
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} finally {
				i += 1;
				if (br != null)
					br.close();
			}
		} while (i < args.length);
	}

}
