
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class GeoPosition {

	static LookupService cl;
	final static private Logger log = Logger.getLogger(GeoPosition.class
			.toString());
	static {
		try {
			ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
			final String geo1 = Thread.currentThread().getContextClassLoader()
					.getResource("geo1.dat").getFile();
			final String geo2 = Thread.currentThread().getContextClassLoader()
					.getResource("geo2.dat").getFile();
			final String geo3 = Thread.currentThread().getContextClassLoader()
					.getResource("geo3.dat").getFile();

			FileConcat.concat(new String[] { geo1, geo2, geo3 }, streamOut);

			cl = new LookupService(streamOut.toByteArray(),
					LookupService.GEOIP_MEMORY_CACHE);
		} catch (IOException e) {
			log.log(Level.SEVERE, "database read in", e);
		}
	}

	
}
