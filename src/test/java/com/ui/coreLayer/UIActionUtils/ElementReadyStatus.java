package com.ui.coreLayer.UIActionUtils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import java.time.Duration;
import java.util.List;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


    public class ElementReadyStatus {
        private static final Logger logger = LoggerUtil.getLogger(ElementReadyStatus.class);
        public WebDriver driver;
        protected ExtentTest testStep;
        protected SoftAssert softAssert;
        private boolean cucumberTest = false;
        private static final String TEST_ABORTED = " -- Test Aborted";
        private static final String SECONDS_FOR = " seconds for ";
        private static final String SECONDS_STRING = " seconds";
        private static final String ALERT_POPUP_DISPLAYED_IN = "Alert Popup DISPLAYED in ";
        private static final String ALERT_POPUP_NOT_DISPLAYED = "Alert Popup NOT DISPLAYED ";
        private static final String STRING_EXPECTED_RESULT = "Expected Result : ";
        private static final String OBJECT_CLICKABLE_FOR = "Object Clickable in ";
        private static final String OBJECT_CLICKABLE_IN = "Object Clickable in ";
        private static final String OBJECT_ENABLED_FOR = "Object ENABLED FOR ";
        private static final String OBJECT_ENABLED = "Object ENABLED in ";
        private static final String OBJECT_EXISTS = "Object EXISTS";
        private static final String OBJECT_FOUND_FOR = "Object Found for ";
        private static final String OBJECT_NOT_CLICKABLE = "Object NOT CLICKABLE ";
        private static final String OBJECT_NOT_ENABLED_FOR_OBJECT = "Object NOT ENABLED for OBJECT -- ";
        private static final String OBJECT_NOT_FOUND = "Object NOT Found ";
        private static final String OBJECT_NOT_VISIBLE = "Object NOT VISIBLE ";
        private static final String OBJECT_VISIBLE_FOR = "Object Visible for ";
        private static final String OBJECT_VISIBLE_IN = "Object Visible in ";
        private static final String OBJECT_NOT_ENABLED = "Object NOT ENABLED ";
        private static final String OBJECT_NOT_EXISTS_FOR_OBJECT = "Object NOT EXISTS for OBJECT -- ";
        private static final String NEW_LINE_ACTUAL_EXCEPTION_RESULT = "\n\n\tActual Exception Result : ";
        private static final String NEW_LINE_ACTUAL_RESULT = "\n\n\tActual Result : ";
        private static final String HTML_SPAN = "<SPAN NOW =";
        private static final String HTML_DATA_IMAGE = "data:image/gif;base64,";
        private static final String OBJECT_DOESNT_EXIST = "Object Does Not Exist ";
        private static final String OBJECT_EXISTS_FOR = "Object exists for ";


        public ElementReadyStatus(WebDriver driver, ExtentTest testStep, SoftAssert softAssert) {
            this.driver = driver;
            this.testStep = testStep;
            this.softAssert = softAssert;
        }

        public ElementReadyStatus(WebDriver driver) {
            this.driver = driver;
            this.setCucumberTest(true);
        }


        protected boolean alertBoxDisplayed() {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds(5L))).until(ExpectedConditions.alertIsPresent());
                return true;
            } catch (Exception var2) {
                this.screenCaptureOnFailure("Alert Popup DISPLAYED in '5' seconds", "Alert Popup NOT DISPLAYED - " + var2);
                return false;
            }
        }

        protected boolean alertBoxDisplayed(int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout))).until(ExpectedConditions.alertIsPresent());
                return true;
            } catch (Exception var3) {
                this.screenCaptureOnFailure("Alert Popup DISPLAYED in '" + timeout + "' seconds", "Alert Popup NOT DISPLAYED - " + var3);
                return false;
            }
        }

        protected boolean alertBoxDisplayedAbort(int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout))).until(ExpectedConditions.alertIsPresent());
                return true;
            } catch (Exception var3) {
                this.screenCaptureOnFailureAbort("Alert Popup DISPLAYED in '" + timeout + "' seconds", "Alert Popup NOT DISPLAYED - " + var3);
                return false;
            }
        }


        protected boolean elementVisible(WebElement element, int timeout) {

            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout))).until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Visible in '" + timeout + "' seconds - for " + this.getObjectIdentityValue(element) + "'", "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Visible for - '" + this.getObjectIdentityValue(element) + "'", "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisible(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout))).until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Visible in '" + timeout + "' seconds - for " + locator.toString() + "'", "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Visible for - '" + locator.toString() + "'", "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleAbort(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout))).until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Visible in '" + timeout + "' seconds - for " + this.getObjectIdentityValue(element) + "'", "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Visible for - '" + this.getObjectIdentityValue(element) + "'", "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleAbort(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout))).until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Visible in '" + timeout + "' seconds - for " + locator.toString() + "'", "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Visible for - '" + locator.toString() + "'", "Object NOT VISIBLE - " + var5);
                return false;
            }
        }


        protected boolean elementVisibleNoReporting(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (Exception var4) {
                logger.error(var4.getMessage());
                return true;
            }
        }

        protected boolean elementVisibleNoReporting(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (Exception var4) {
                logger.error(var4.getMessage());
                return true;
            }
        }

        protected boolean elementNotExists(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                this.screenCaptureOnFailure("Object Does Not Exist" + timeout + " seconds - for " + locator.toString(),"Object exists for - "+ locator.toString());
                return false;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotExists(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated((By)element));
                String var10001 = "Object Does Not Exist" + timeout + " seconds - for ";
                String var10002 = this.getObjectIdentityValue(element);
                this.screenCaptureOnFailure(var10001, "Object Exists for - " + var10002);
                return false;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotExistsAbort(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                this.screenCaptureOnFailure("Object Does Not Exist" + timeout + " seconds - for " + locator.toString(),"Object exists for - "+ locator.toString());
                return false;
            } catch (Exception var5) {
                return true;
            }
        }


        protected boolean elementNotExistsAbort(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated((By)element));
                String var10001 = "Object Does Not Exist" + timeout + " seconds - for " + this.getObjectIdentityValue(element) + "'";
                String var10002 = this.getObjectIdentityValue(element);
                this.screenCaptureOnFailure(var10001, "Object Exists for - " + var10002);
                return false;
            } catch (TimeoutException var4) {
                return true;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotVisible(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                String var10001 = "Object Does Not Exist" + timeout + " seconds - for " + this.getObjectIdentityValue(element) + "'";
                String var10002 = this.getObjectIdentityValue(element);
                this.screenCaptureOnFailure(var10001, "Object Visible for - '" + var10002);
                return false;
            } catch (TimeoutException var4) {
                return true;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotVisible(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                this.screenCaptureOnFailure("Object Does Not Exist" + timeout + " seconds - for " + locator.toString(), "Object Visible for - " + locator.toString());
                return false;
            } catch (TimeoutException var4) {
                return true;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotVisibleAbort(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                String var10001 = "Object NOT VISIBLE - " + timeout + " seconds - for " + this.getObjectIdentityValue(element) + "'";
                String var10002 = this.getObjectIdentityValue(element);
                this.screenCaptureOnFailure(var10001, "Object Visible for - '" + var10002);
                return false;
            } catch (TimeoutException var4) {
                return true;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementNotVisibleAbort(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                this.screenCaptureOnFailure("Object NOT VISIBLE - " + timeout + " seconds - for " + locator.toString(), "Object Visible for - " + locator.toString());
                return false;
            } catch (TimeoutException var4) {
                return true;
            } catch (Exception var5) {
                return true;
            }
        }

        protected boolean elementClickable(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Clickable in '" + timeout + " seconds - for " + this.getObjectIdentityValue(element) + "'", "Object NOT CLICKABLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Clickable for - " + this.getObjectIdentityValue(element) + "'", "Object NOT CLICKABLE - " + var5);
                return false;
            }
        }

        protected boolean elementClickable(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.elementToBeClickable(locator));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Clickable in '" + timeout + " seconds - for " + locator.toString() + "'", "Object NOT CLICKABLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Clickable for - " + locator.toString() + "'", "Object NOT CLICKABLE - " + var5);
                return false;
            }
        }

        protected boolean elementClickableAbort(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Clickable in '" + timeout + " seconds - for " + this.getObjectIdentityValue(element) + "'", "Object NOT CLICKABLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Clickable for - " + this.getObjectIdentityValue(element) + "'", "Object NOT CLICKABLE - " + var5);
                return false;
            }
        }

        protected boolean elementClickableAbort(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.elementToBeClickable(locator));
                return true;
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Clickable in '" + timeout + " seconds - for " + locator.toString() + "'", "Object NOT CLICKABLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Clickable for - " + locator.toString() + "'", "Object NOT CLICKABLE - " + var5);
                return false;
            }
        }

        protected boolean isElementEnabled(WebElement element, int timeout) {
            try {
                if (this.elementVisible(element, timeout) && element.isEnabled()) {
                    return true;
                }
            } catch (Exception var4) {
                this.screenCaptureOnFailure("Object ENABLED for - '" + this.getObjectIdentityValue(element) + "'", var4);
            }
            return false;
        }

        protected boolean isElementEnabled(By locator, int timeout) {
            try {
                if (this.elementVisible(locator, timeout) && this.driver.findElement(locator).isEnabled()) {
                    return true;
                }
            } catch (Exception var4) {
                this.screenCaptureOnFailure("Object ENABLED for - '" + locator.toString() + "'", var4);
            }
            return false;
        }

        protected boolean isElementEnabledAbort(WebElement element, int timeout) {
            try {
                if (this.elementVisibleAbort(element, timeout) && element.isEnabled()) {
                    return true;
                }
            } catch (Exception var4) {
                this.screenCaptureOnFailureAbort("Object ENABLED for - '" + this.getObjectIdentityValue(element) + "'", var4);
            }
            return false;
        }

        protected boolean isElementEnabledAbort(By locator, int timeout) {
            try {
                if (this.elementVisibleAbort(locator, timeout) && this.driver.findElement(locator).isEnabled()) {
                    return true;
                }
            } catch (Exception var4) {
                this.screenCaptureOnFailureAbort("Object ENABLED for - '" + locator.toString() + "'", var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabled(WebElement element, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailure("Object ENABLED", "Object NOT ENABLED for OBJECT - '" + this.getObjectIdentityValue(element) + "'", element);
            } catch (Exception var4) {
                this.screenCaptureOnFailure("Object Found for - '" + this.getObjectIdentityValue(element) + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabled(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailure("Object ENABLED", "Object NOT ENABLED for OBJECT - '" + locator.toString() + "'", locator);
            } catch (Exception var4) {
                this.screenCaptureOnFailure("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabledAbort(WebElement element, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailureAbort("Object ENABLED for - '" + this.getObjectIdentityValue(element) + "'", "Object NOT ENABLED", element);
            } catch (Exception var4) {
                this.screenCaptureOnFailureAbort("Object Found for - '" + this.getObjectIdentityValue(element) + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabledAbort(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailureAbort("Object ENABLED for - '" + locator.toString() + "'", "Object NOT ENABLED", locator);
            } catch (Exception var4) {
                this.screenCaptureOnFailureAbort("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementExistsAndEnabled(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailure("Object EXISTS", "Object NOT EXISTS for OBJECT - '" + locator.toString() + "'", locator);
            } catch (Exception var4) {
                this.screenCaptureOnFailure("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementExistsAndEnabledAbort(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.screenCaptureOnFailureAbort("Object EXISTS", "Object NOT EXISTS for OBJECT - '" + locator.toString() + "'", locator);
            } catch (Exception var4) {
                this.screenCaptureOnFailureAbort("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementExists(By locator, int timeout) {
            WebElement element = null;
            try {
                element = (WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return true;
                }
                this.screenCaptureOnFailure("Object EXISTS", "Object NOT EXISTS for OBJECT - '" + locator.toString() + "'");
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var5);
            }
            return false;
        }

        protected boolean elementExistsAbort(By locator, int timeout) {
            WebElement element = null;
            try {
                element = (WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                if (element != null) {
                    return true;
                }
                this.screenCaptureOnFailureAbort("Object EXISTS", "Object NOT EXISTS for OBJECT - '" + locator.toString() + "'");
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Found for - '" + locator.toString() + "'", "Object NOT Found - " + var5);
            }
            return false;
        }


        protected boolean elementExistsNoReport(By locator, int timeout) {
            WebElement element = null;
            try {
                element = (WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                return element != null;
            } catch (Exception var5) {
                return false;
            }
        }

        protected boolean elementVisibleNoReport(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (Exception var4) {
                return false;
            }
        }

        protected boolean elementVisibleNoReport(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (Exception var4) {
                return false;
            }
        }

        private String getObjectIdentityValueHelper(String eleString) {
            try {
                return getLocator(eleString);
            } catch (Exception var3) {
                return null;
            }
        }

        public static String getLocator(String eleString) {
            String[] locatorTypes = new String[]{"id:", "xpath:", "className:", "tagName:", "name:", "partialLinkText:", "linkText:", "cssSelector:"};
            int arylength = locatorTypes.length;

            for(int i = 0; i < arylength; ++i) {
                if (eleString.contains(locatorTypes[i])) {
                    int startIndex = eleString.indexOf(locatorTypes[i]);
                    return eleString.substring(startIndex);
                }
            }
            return eleString;
        }

        protected String getObjectIdentityValue(WebElement element) {
            if (element == null) {
                return null;
            } else {
                String eleString = element.toString();
                return this.getObjectIdentityValueHelper(eleString);
            }
        }

        protected String getObjectIdentityValue(List<WebElement> element) {
            if (element.get(0) == null) {
                return null;
            } else {
                WebElement newElement = (WebElement)element.get(0);
                String eleString = newElement.toString();
                return this.getObjectIdentityValueHelper(eleString);
            }
        }

        public void screenCaptureOnFailure(String expectedResult, String actualResult) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachment(this.driver));
                this.softAssert.assertTrue(false, errorMessage);
            } else {
                ExtentCucumberAdapter.addTestStepLog("<font color=\"#ff0000\"><b>"+errorMessage+"</b></font color>");
                this.cucumberTest=false;
                Assert.fail(errorMessage);
            }
        }

        public void screenCaptureOnFailure(String expectedResult, String actualResult, WebElement element) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachmentScroll(this.driver, element));
                this.softAssert.assertTrue(false, errorMessage);
            } else {
                ExtentCucumberAdapter.addTestStepLog("<font color=\"#ff0000\"><b>"+errorMessage+"</b></font color>");
                this.cucumberTest=false;
                Assert.fail(errorMessage);
            }
        }

        public void screenCaptureOnFailure(String expectedResult, String actualResult, By locator) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachmentScroll(this.driver, locator));
                this.softAssert.assertTrue(false, errorMessage);
            } else {
                ExtentCucumberAdapter.addTestStepLog("<font color=\"#ff0000\"><b>"+errorMessage+"</b></font color>");
                this.cucumberTest=false;
                Assert.fail(errorMessage);
            }
        }

        public void screenCaptureOnFailure(String expectedResult, Exception e) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Exception Result - " + e.getMessage();
            logger.error(errorMessage, e);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, e.getMessage()));
                this.testStep.log(Status.INFO, this.getImageAttachment(this.driver));
                this.softAssert.assertTrue(false, errorMessage);
            } else {
                ExtentCucumberAdapter.addTestStepLog("<font color=\"#ff0000\"><b>"+e.getMessage()+"</b></font color>");
                this.cucumberTest=false;
                Assert.fail(e.getMessage());
            }
        }

        public void screenCaptureOnFailureAbort(String expectedResult, String actualResult) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachmentAbort(this.driver));
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }


        public void screenCaptureOnFailureAbort(String expectedResult, String actualResult, WebElement element) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachmentScrollAbort(this.driver, element));
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public void screenCaptureOnFailureAbort(String expectedResult, String actualResult, By locator) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
                this.testStep.log(Status.INFO, this.getImageAttachmentScrollAbort(this.driver, locator));
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public void screenCaptureOnFailureAbort(String expectedResult, Exception e) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Exception Result - " + e.getMessage();
            logger.error(errorMessage + " - Test Aborted", e);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, e.getMessage()));
                this.testStep.log(Status.INFO, this.getImageAttachmentAbort(this.driver));
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public String getImageAttachment(WebDriver driver) {
            try {
                return this.getImagePath(driver);
            } catch (Exception var3) {
                this.getCatchSoftAssertion(var3);
                return null;
            }
        }

        public String getImageAttachmentScroll(WebDriver driver, WebElement element) {
            try {
                this.scrollElement(element);
                return this.getImagePath(driver);
            } catch (Exception var4) {
                this.getCatchSoftAssertion(var4);
                return null;
            }
        }

        private String getImagePath(WebDriver driver) {
            String imagePath = "data:image/gif;base64," + (String)((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
            return this.getCucumberTest() ? imagePath : this.testStep.addScreenCaptureFromBase64String(imagePath).toString();
        }

        private void getCatchSoftAssertion(Exception e) {
            if (this.getCucumberTest()) {
                this.softAssert.assertTrue(false, "Driver Not Reachable - " + e.getMessage());
            }
        }

        public String getImageAttachmentScroll(WebDriver driver, By locator) {
            try {
                WebElement element = this.driver.findElement(locator);
                this.scrollElement(element);
                return this.getImagePath(driver);
            } catch (Exception var4) {
                this.getCatchSoftAssertion(var4);
                return null;
            }
        }

        private void getCatchAssertion(Exception e) {
            if (this.getCucumberTest()) {
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, "Driver Not Reachable - Test Aborted - " + e.getMessage());
        }

        public String getImageAttachmentAbort(WebDriver driver) {
            try {
                return this.getImagePath(driver);
            } catch (Exception var3) {
                this.getCatchAssertion(var3);
                return null;
            }
        }

        public String getImageAttachmentScrollAbort(WebDriver driver, WebElement element) {
            try {
                this.scrollElement(element);
                return this.getImagePath(driver);
            } catch (Exception var4) {
                this.getCatchAssertion(var4);
                return null;
            }
        }

        public String getImageAttachmentScrollAbort(WebDriver driver, By locator) {
            try {
                WebElement element = this.driver.findElement(locator);
                this.scrollElement(element);
                return this.getImagePath(driver);
            } catch (Exception var4) {
                this.getCatchAssertion(var4);
                return null;
            }
        }
        public String formatFailureResults(String expectedResult, String actualResult) {
            String errorMessage = "<TABLE style='border:2px solid black; table-layout: fixed'>";
            errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black; width: 200px'><B>Expected Result</B></TD><TD><SPAN style='font-weight:bold'>" + expectedResult + "</SPAN></TD></TR>";
            errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black; width: 200px'><B>Actual Result</B></TD><TD><SPAN style='font-weight:bold'>" + actualResult + "</SPAN></TD></TR>";
            errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='background-color:red'><SPAN style='font-weight:bold'>FAILED</SPAN></TD></TR></TABLE>";
            return errorMessage;
        }

        public String formatPassResults(String expectedResult) {
            String errorMessage = "<TABLE style='border:2px solid black; table-layout: fixed'>";
            errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='border:2px solid black; width: 200px'><B>Expected Result</B></TD><TD><SPAN style='font-weight:bold'>" + expectedResult + "</SPAN></TD></TR>";
            errorMessage = errorMessage + "<TR style='border:2px solid black'><TD style='background-color:green'><SPAN style='font-weight:bold'>PASSED</SPAN></TD></TR></TABLE>";
            return errorMessage;
        }

        public void formatVerificationMessage(String testStepMessage) {
            this.testStep.log(Status.INFO, "<b><p align='center' style='border:2px solid black;background-color:#D3D3D3;font-size:80px'>" + testStepMessage + "</p></b>");
        }

        protected void scrollElement(WebElement element) {
            Coordinates cor = ((Locatable)element).getCoordinates();
            cor.inViewPort();
        }

        public WebElement locatorToWebElement(By locator) {
            try {
                return (WebElement)this.driver.findElements(locator).get(0);
            } catch (IndexOutOfBoundsException var3) {
                return null;
            }
        }

        public void secureDataFailure(String expectedResult, String actualResult) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            } else {
                this.softAssert.assertTrue(false, errorMessage + " - Test Aborted");
            }

        }

        public void secureDataFailure(String expectedResult, Exception e) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Exception Result - " + e.getMessage();
            logger.error(errorMessage, e);
            if (this.getCucumberTest()) {
                this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, e.getMessage()));
            } else {
                this.softAssert.assertTrue(false, errorMessage + " - Test Aborted");
            }

        }


        public void secureDataFailure(String expectedResult, String actualResult, WebElement element) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage);
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            this.softAssert.assertTrue(false, errorMessage);
        }

        public void secureDataFailure(String expectedResult, String actualResult, By locator) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage);
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            this.softAssert.assertTrue(false, errorMessage);
        }

        public void secureDataFailureAbort(String expectedResult, String actualResult) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            if (this.getCucumberTest()) {
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public void secureDataFailureAbort(String expectedResult, Exception e) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Exception Result - " + e.getMessage();
            logger.error(errorMessage + " - Test Aborted", e);
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, e.getMessage()));
            if (this.getCucumberTest()) {
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public void secureDataFailureAbort(String expectedResult, String actualResult, WebElement element) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            if (this.getCucumberTest()) {
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }

        public void secureDataFailureAbort(String expectedResult, String actualResult, By locator) {
            String errorMessage = "Expected Result - " + expectedResult + "\n\n--\nActual Result - " + actualResult;
            logger.error(errorMessage + " - Test Aborted");
            this.testStep.log(Status.FAIL, this.formatFailureResults(expectedResult, actualResult));
            if (this.getCucumberTest()) {
                this.softAssert.assertAll();
            }
            Assert.assertTrue(false, errorMessage + " - Test Aborted");
        }


        protected boolean elementVisibleSecureData(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (TimeoutException var4) {
                this.secureDataFailure("Object Visible in " + timeout + " seconds - for " + this.getObjectIdentityValue(element), "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.secureDataFailure("Object Visible for - " + this.getObjectIdentityValue(element), "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleSecureData(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException var4) {
                this.secureDataFailure("Object Visible in " + timeout + " seconds - for " + locator.toString(), "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.secureDataFailure("Object Visible for - " + locator.toString(), "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleSecureDataAbort(WebElement element, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (TimeoutException var4) {
                this.secureDataFailureAbort("Object Visible in " + timeout + " seconds - for " + this.getObjectIdentityValue(element), "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.secureDataFailureAbort("Object Visible for - " + this.getObjectIdentityValue(element), "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleSecureDataAbort(By locator, int timeout) {
            try {
                (new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                return true;
            } catch (TimeoutException var4) {
                this.secureDataFailureAbort("Object Visible in " + timeout + " seconds - for " + locator.toString(), "Object NOT VISIBLE - " + var4);
                return false;
            } catch (Exception var5) {
                this.secureDataFailureAbort("Object Visible for - " + locator.toString(), "Object NOT VISIBLE - " + var5);
                return false;
            }
        }

        protected boolean elementVisibleAndEnabledSecureData(WebElement element, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element))).isEnabled()) {
                    return true;
                }
                this.secureDataFailure("Object ENABLED, Object NOT ENABLED for OBJECT - " + this.getObjectIdentityValue(element), "Object NOT Found - ");
            } catch (Exception var4) {
                this.secureDataFailure("Object Found for - " + this.getObjectIdentityValue(element), "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabledSecureData(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.secureDataFailure("Object ENABLED, Object NOT ENABLED for OBJECT - " + locator.toString(), "Object NOT Found - ");
            } catch (Exception var4) {
                this.secureDataFailure("Object Found for - " + locator.toString(), "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabledSecureDataAbort(WebElement element, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOf(element))).isEnabled()) {
                    return true;
                }
                this.secureDataFailureAbort("Object ENABLED for - " + this.getObjectIdentityValue(element), "Object NOT ENABLED, element");
            } catch (Exception var4) {
                this.secureDataFailureAbort("Object Found for - " + this.getObjectIdentityValue(element), "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementVisibleAndEnabledSecureDataAbort(By locator, int timeout) {
            try {
                if (((WebElement)(new WebDriverWait(this.driver, Duration.ofSeconds((long)timeout)))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.secureDataFailureAbort("Object ENABLED for - " + locator.toString(), "Object NOT ENABLED - " + locator.toString());
            } catch (Exception var4) {
                this.secureDataFailureAbort("Object Found for - " + locator.toString(), "Object NOT Found - " + var4);
            }
            return false;
        }


        protected boolean elementExistsAndEnabledSecureData(By locator, int timeout) {
            try {
                if (((WebElement) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.secureDataFailure("Object EXISTS", "Object NOT EXISTS for OBJECT - '" + locator.toString() + "'", locator);
            } catch (Exception var4) {
                this.secureDataFailure("Object Found for - " + locator.toString(), "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementExistsAndEnabledSecureDataAbort(By locator, int timeout) {
            try {
                if (((WebElement) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.presenceOfElementLocated(locator))).isEnabled()) {
                    return true;
                }
                this.secureDataFailureAbort("Object EXISTS", "Object NOT EXISTS for OBJECT - " + locator.toString()+"'", locator);
            } catch (Exception var4) {
                this.secureDataFailureAbort("Object Found for - " + locator.toString(), "Object NOT Found - " + var4);
            }
            return false;
        }

        protected boolean elementInvisible(By locator, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Visible for - " + timeout + " seconds - for " + locator.toString(), "Object Visible in " + var4);
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Visible for - " + locator.toString(), "Object Visible for - " + var5);
            }
            return false;
        }

        protected boolean elementInvisible(WebElement element, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOf(element));
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailure("Object Visible for - " + timeout + " seconds - for " + element.toString(), "Object Visible in " + var4);
            } catch (Exception var5) {
                this.screenCaptureOnFailure("Object Visible for - " + element.toString(), "Object Visible for - " + var5);
            }
            return false;
        }

        protected boolean elementInvisibleAbort(By locator, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Visible for - " + timeout + " seconds - for " + locator.toString(), "Object Visible in " + var4);
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Visible for - " + locator.toString(), "Object Visible for - " + var5);
            }
            return false;
        }

        protected boolean elementInvisibleAbort(WebElement element, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOf(element));
            } catch (TimeoutException var4) {
                this.screenCaptureOnFailureAbort("Object Visible for - " + timeout + " seconds - for " + element.toString(), "Object Visible in " + var4);
            } catch (Exception var5) {
                this.screenCaptureOnFailureAbort("Object Visible for - " + element.toString(), "Object Visible for - " + var5);
            }
            return false;
        }

        protected boolean elementInvisibleNoReport(By locator, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOfElementLocated(locator));
            } catch (Exception var4) {
                return false;
            }
        }

        protected boolean elementInvisibleNoReport(WebElement element, int timeout) {
            try {
                return (Boolean) (new WebDriverWait(this.driver, Duration.ofSeconds((long) timeout)))
                        .until(ExpectedConditions.invisibilityOf(element));
            } catch (Exception var4) {
                return false;
            }
        }

        public boolean getCucumberTest() {
            return this.cucumberTest;
        }

        public void setCucumberTest(boolean cucumberTest) {
            this.cucumberTest = cucumberTest;
        }





    }