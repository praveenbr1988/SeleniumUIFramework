package com.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;
    private static Map<String, String> yamlConfig;

    static {
        properties = new Properties();
        try (InputStream inputStream = new FileInputStream("src/main/resources/common/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        yamlConfig = YamlReader.getEnvironmentConfig();
    }

    public static void getEnvConfigValues() {
        yamlConfig = YamlReader.getEnvironmentConfig();
    }

    public static String getProperty(String key) {
        //return properties.getProperty(key);
        return yamlConfig.getOrDefault(key, properties.getProperty(key));

    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    // Add methods for other types as needed (boolean, double, etc.)
}
