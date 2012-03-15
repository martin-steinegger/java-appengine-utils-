import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * SeekableInputStream is a extention of the ByteArrayInputStream.
 * It«s possible to seek in the stream
 * 
 * @author martin.steinegger
 *
 */
public final class SeekableInputStream extends ByteArrayInputStream {

	private int length;

	public SeekableInputStream(byte[] data) {
		super(data);
		length = data.length;
	}

	public void seek(long position) {
		this.pos = (int) position;
	}

	public void readFully(byte b[]) throws IOException {
		readFully(b, 0, b.length);
	}

	public void readFully(byte b[], int off, int len) throws IOException {
		int n = 0;
		do {
			int count = this.read(b, off + n, len - n);
			if (count < 0)
				throw new EOFException();
			n += count;
		} while (n < len);
	}

	public long getFilePointer() {
		return pos;
	}

	public int length() {
		return length;
	}

	public final byte readByte() throws IOException {
		int ch = this.read();
		if (ch < 0)
			throw new EOFException();
		return (byte) (ch);
	}
}