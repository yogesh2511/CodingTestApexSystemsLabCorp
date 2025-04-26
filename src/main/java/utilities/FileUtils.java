package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	public static <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
		InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
		return new ObjectMapper().readValue(inputStream, valueType);
	}
}
