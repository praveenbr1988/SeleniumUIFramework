package com.ui.businessLayer.pages.crewA;

import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.orchestrationLayer.Generics.ScenarioContext;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;


public class GooglePage {

    private static final Logger logger = LoggerUtil.getLogger(GooglePage.class);

    private WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchBox;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        logger.info("Entering search text: " + searchTerm);
        searchBox.sendKeys(searchTerm);
        ScenarioContext.getInstance().setScenarioContext("searchTerm",searchTerm );

    }

    public void getBrowserTitle() throws InterruptedException {
        String title = driver.getTitle();
        Thread.sleep(5000);
        ScenarioContext.getInstance().setScenarioContext("Title",title );
        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
    }

    public void extracttxtFromImg(){

        ITesseract tesseract = new Tesseract();
        try {
            String text = tesseract.doOCR(new File("D:\\Projects\\IdeaProjects\\UIProject\\SeleniumUIFramework\\src\\main\\resources\\Images\\sampleimg.png"));
            System.out.println(text);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }





}
