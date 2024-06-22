package com.ui.coreLayer.CommonUtilities;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlReader {

    private static final String ENVIRONMENT_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/yamlFiles/environments.yaml";
    private static Map<String, Object> yamlMap;
    private static String environment;

    static {
        loadYaml();
    }

    private static void loadYaml() {
        DumperOptions options = new DumperOptions();
        Yaml yaml = new Yaml(options);
        try (InputStream inputStream = new FileInputStream(ENVIRONMENT_FILE_PATH)) {
            yamlMap = yaml.load(inputStream);
            environment = (String) yamlMap.getOrDefault("default", "dev");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("YAML file not found: " + ENVIRONMENT_FILE_PATH, e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML file: " + ENVIRONMENT_FILE_PATH, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getEnvironmentConfig() {
        return (Map<String, String>) ((Map<String, Object>) yamlMap.get("environments")).get(environment);
    }

    public static void setEnvironment(String env) {
        environment = env;
    }

    public static String getEnvironment() {
        return environment;
    }
}
