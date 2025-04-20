package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Configuration/config.properties")) {
            properties.load(fis);
            logger.info("Configuration file loaded successfully");

        } catch (IOException e) {
            logger.error("Failed to load config.properties file: {}", e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key,"Property Not Found");
    }
}
