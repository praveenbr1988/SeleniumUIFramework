package com.ui.businessLayer.stepDefinitions.commonReusableSteps;

import com.ui.businessLayer.businesscomponents.GeneralComponents;
import com.ui.businessLayer.pageObjects.crewA.GooglePage;
import com.ui.coreLayer.CommonUtilities.DriverManager;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.coreLayer.FrameworkConfigs.EncryptAndDecrypt;
import com.ui.coreLayer.FrameworkConfigs.TestEnvironmentConfigurations;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class GeneralStepDef {

    private static final Logger logger = LoggerUtil.getLogger(GeneralStepDef.class);

    protected WebDriver driver = DriverManager.getInstance().getDriver();

    GeneralComponents general = new GeneralComponents();
    GooglePage gPage = new GooglePage(driver);

    @Given ("^I launch the (.+)$")
    public void i_launch_the_application(String application) throws InterruptedException {
        general.launchApplication(application);
        logger.info("Successfully launched");
    }

    @Given("The test data is loaded from {string}")
    public void the_test_data_is_loaded_from(String testDataPath) {
        System.out.println("the Test data path: "+testDataPath);
    }

    @Given ("^Search text in Google$")
    public void search_text_google() throws InterruptedException {
        gPage.enterSearchTerm("Hurray Cucumber Feature executed");
        gPage.getBrowserTitle();
        logger.info("serached successfully");

    }

    @Given ("^Extract Text from Image$")
    public void extract_Text_from_Image() throws InterruptedException {
        gPage.extracttxtFromImg();
        logger.info("extracted successfully");

    }


    @Given ("^Read Environments yaml file$")
    public void read_yamlFile() throws Exception {
        System.out.println(TestEnvironmentConfigurations.getTestURL("EMOQA"));
        System.out.println(TestEnvironmentConfigurations.getTestDBURL("EMU-QA"));
        System.out.println(TestEnvironmentConfigurations.getAPIConfigurations("EMU-QA-Agent"));
    }


    @Given ("^Encrypt password (.+)$")
    public void encrypt_Password(String passwordToEncrypt) throws Exception {
        System.out.println("Encrypted Password is : "+ EncryptAndDecrypt.encrypt(passwordToEncrypt));

    }

    @Given ("^Decrypt password (.+)$")
    public void decrypt_Password(String passwordToDecrypt) throws Exception {
        System.out.println("Decrypted Password is : "+ EncryptAndDecrypt.decrypt(passwordToDecrypt));

    }


}
