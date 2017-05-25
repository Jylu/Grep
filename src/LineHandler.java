import java.io.Closeable;
import java.io.IOException;

public interface LineHandler extends Closeable {
	public String readLine() throws IOException;
	public void printLine();
}
