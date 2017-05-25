import java.io.*;

public class StdInLineHandler implements LineHandler {
	private BufferedReader reader;
	private String line;

	public StdInLineHandler() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public String readLine() throws IOException {
		line = reader.readLine();
		return line;
	}

	@Override
	public void printLine() {
		System.out.println(line);
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}
}
