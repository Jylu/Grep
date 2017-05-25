import java.io.*;

public class FileLineHandler implements LineHandler {
	private BufferedReader reader;
	private String[] files;
	private int curFile;
	private String line;
	private String fName;

	public FileLineHandler(String[] files) {
		this.files = files;
		curFile = 0;

		openFile();
	}

	private boolean openFile() {
		if (curFile == files.length) {
			return false;
		}

		fName = files[curFile++];
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fName)));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());

			return openFile();
		}

		return true;
	}

	@Override
	public String readLine() throws IOException {
		line = reader.readLine();

		// EOF
		if (line == null) {
			reader.close();

			// Another file has been opened
			if (openFile()) {
				readLine();
			}
		}

		return line;
	}

	@Override
	public void printLine() {
		if (files.length == 1) {
			System.out.println(line);
		}
		else {
			System.out.println(fName + ": " + line);
		}
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
