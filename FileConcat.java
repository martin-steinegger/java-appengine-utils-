import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class FileConcat {

	public static void concat(String[] filelist, OutputStream out)
			throws IOException {
		ListOfFiles mylist = new ListOfFiles(filelist);

		SequenceInputStream s = new SequenceInputStream(mylist);
		byte[] ioBuf = new byte[8192];
		int bytesRead;
		while ((bytesRead = s.read(ioBuf)) != -1)
			out.write(ioBuf, 0, bytesRead);

		s.close();
	}

	static class ListOfFiles implements Enumeration<BufferedInputStream> {

		private String[] listOfFiles;
		private int current = 0;

		public ListOfFiles(String[] listOfFiles) {
			this.listOfFiles = listOfFiles;
		}

		public boolean hasMoreElements() {
			if (current < listOfFiles.length)
				return true;
			else
				return false;
		}

		public BufferedInputStream nextElement() {
			BufferedInputStream in = null;

			if (!hasMoreElements())
				throw new NoSuchElementException("No more files.");
			else {
				String nextElement = listOfFiles[current];
				current++;
				try {
					in = new BufferedInputStream(new FileInputStream(
							nextElement));

				} catch (FileNotFoundException e) {
					System.err
							.println("ListOfFiles: Can't open " + nextElement);
				}
			}
			return in;
		}
	}
}