package com.ui.coreLayer.FrameworkConfigs;

import com.ui.businessLayer.pages.crewA.GooglePage;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ProjectConfigurations {

    private static final Logger logger = LoggerUtil.getLogger(GooglePage.class);
    private Properties properties;
    private static final String FILE_NOT_FOUND = "There was a File not found Exception";
    private static final String CONFIG_ERROR = "There was a Configuration Error";
    private static final String CONFIG_PATH;

    static {
        String var10000 = System.getProperty("user.dir");
        CONFIG_PATH = var10000 + File.separatorChar + "src" + File.separatorChar + "test" + File.separatorChar + "resources" + File.separatorChar + "propertyFiles" + File.separatorChar + "config.properties";
    }


    public ProjectConfigurations() {

        try {
            FileInputStream configurations = new FileInputStream(CONFIG_PATH);
            try {
                this.properties = new Properties();
                this.properties.load(configurations);
            } catch (Throwable var5) {
                try {
                    configurations.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }
                throw var5;
            }
            configurations.close();

        } catch (FileNotFoundException var6) {
            logger.info("There was a File Not Found Exception " + var6);
        } catch (IOException var7) {
            logger.info("There was a Configuration Error " + var7);
        }
    }

    public ProjectConfigurations(String filePath) {

        try {
            String var10002 = System.getProperty("user.dir");
            FileInputStream configurations = new FileInputStream(var10002+ filePath);
            try {
                this.properties = new Properties();
                this.properties.load(configurations);
            } catch (Throwable var5) {
                try {
                    configurations.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }
                throw var5;
            }
            configurations.close();

        } catch (FileNotFoundException var6) {
            logger.info("There was a File Not Found Exception " + var6);
        } catch (IOException var7) {
            logger.info("There was a Configuration Error " + var7);
        }
    }

    public String getData(String elementName){
        return this.properties.getProperty(elementName);
    }



    public void setPropertyAndValue(String property, String value) {

        try {
            FileOutputStream configuration = new FileOutputStream(CONFIG_PATH);
            try {
                this.properties.setProperty(property, value);
                this.properties.store(configuration, "updates");
            } catch (Throwable var7) {
                try {
                    configuration.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
                throw var7;
            }
            configuration.close();

        } catch (FileNotFoundException var8) {
            logger.info("There was a File Not Found Exception " + var8);
        } catch (IOException var9) {
            logger.info("There was a Configuration Error " + var9);
        }
    }




}
