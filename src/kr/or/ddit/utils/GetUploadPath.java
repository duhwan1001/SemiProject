package kr.or.ddit.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.io.File;
import org.apache.ibatis.io.Resources;

public class GetUploadPath {
	private static Properties properties = new Properties();
	static {
		String resource = "kr/or/ddit/properties/uploadPath.properties";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getUploadPath(String key) {
		String uploadPath = null;
		uploadPath = properties.getProperty(key);
		uploadPath = uploadPath.replace("/", File.separator);
		return uploadPath;
	}
}
