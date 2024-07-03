package com.ui.coreLayer.FrameworkConfigs;

import com.ui.businessLayer.pageObjects.crewA.GooglePage;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.util.Map;

public class TestEnvironmentConfigurations {

    private static final Logger logger = LoggerUtil.getLogger(GooglePage.class);

    private TestEnvironmentConfigurations() {
        throw new IllegalStateException("utility class");
    }

    public static String getTestURL(String environment) throws Exception {
        try {
            String environmentURL = new ProjectConfigurations().getData("environmentURL");
            //String environmentURL = CheckConfigurationSettings.getPropertyValue("environmentURL");
            if (environmentURL != null) {
                String logMessage = "Using environmentURL for Test URL as: " + environmentURL;
                logger.info(logMessage);
                return environmentURL;
            } else {
                EnvironmentConfiguration config = new EnvironmentConfiguration();
                return config.getEnvironment(environment);
            }
        } catch (YAMLException var3) {
            logger.info("Badly Formatted Environments.yml file " + var3.getMessage());
            throw new Exception("Badly Formatted Environments.yml file " + var3.getMessage());
        } catch (IOException var4) {
            logger.info("File bad or not found " + var4.getMessage());
            throw new Exception("File bad or not found " + var4.getMessage());
        }
    }


    public static DatabaseConfiguration getTestDBURL(String environment) throws Exception {
        try{
            EnvironmentConfiguration config = new EnvironmentConfiguration();
            Map<String, String> dbCfg = config.getDatabaseConfigs(environment);
            return buildDatabaseConfiguration((String)dbCfg.get("connection"), (String)dbCfg.get("user"), (String)dbCfg.get("password"));
        } catch(Exception var3){
            logger.info("Test Database Environment not found: "+var3.getMessage());
            throw new Exception("Test Database Environment not found: "+var3.getMessage());
        }
    }

    public static APIConfiguration getAPIConfigurations(String environment) throws Exception {
        try{
            EnvironmentConfiguration config = new EnvironmentConfiguration();
            Map<String, String> dbCfg = config.getAPIConfigs(environment);
            return buildApiConfiguration((String)dbCfg.get("baseURL"), (String)dbCfg.get("user"), (String)dbCfg.get("password"));
        } catch(Exception var3){
            logger.info("Test API Environment not found: "+var3.getMessage());
            throw new Exception("Test API Environment not found: "+var3.getMessage());
        }
    }

    public static DatabaseConfiguration buildDatabaseConfiguration(String connection, String username, String password) throws Exception {

        try{
            if(connection!=null && !connection.isEmpty() && username!= null && !username.isEmpty() && password!= null && !password.isEmpty()){
                return new DatabaseConfiguration(connection,username,password);
            }
            else{
                throw new Exception("Connection or username or password for the DB needs to be set properly");
            }

        } catch(Exception var4){
            logger.info("Error occurred when trying to build the DB configurations " + var4.getMessage());
            throw new Exception("Error occurred when trying to build the DB configurations " + var4.getMessage());
        }
    }

    public static APIConfiguration buildApiConfiguration(String baseURL, String username, String password) throws Exception {

        try{
            if(baseURL!=null && !baseURL.isEmpty() && username!= null && !username.isEmpty() && password!= null && !password.isEmpty()){
                return new APIConfiguration(baseURL,username,password);
            }
            else{
                throw new Exception("BaseURL or username or password for the API needs to be set properly");
            }

        } catch(Exception var4){
            logger.info("Error occurred when trying to build the API configurations " + var4.getMessage());
            throw new Exception("Error occurred when trying to build the API configurations " + var4.getMessage());
        }
    }


}
