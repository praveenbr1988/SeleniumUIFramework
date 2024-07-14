package com.ui.coreLayer.CommonUtilities;

import com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests;
import com.ui.coreLayer.FrameworkConfigs.ProjectConfigurations;
import com.ui.orchestrationLayer.Generics.TestMembersFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

public class CommonMethods {

    public static ThreadLocal<Boolean> proceedFurther = new ThreadLocal<Boolean>();
    protected static final String ROWSTARTTAG = "<TR style='border: 2px solid #FFFFFF; background-color: #333333; color: #D3D3D3;'>";
    protected static final String ROWENDTAG = "</TR>";
    protected static final String COLUMNSTARTTAG = "<TD style='border: 2px solid #FFFFFF; background-color: #333333; color: #D3D3D3; width: 120px'>";
    protected static final String COLUMNENDTAG = "</TD>";
    protected static final String TABLESTARTTAG = "<TABLE style='border: 2px solid #FFFFFF; table-layout: fixed; background-color: #333333; color: #D3D3D3;'>";
    protected static final String TABLEENDTAG = "</TABLE>";
    protected static final String HEADERSTARTTAG = "<TH style='border: 2px solid #FFFFFF; background-color: #333333; color: #D3D3D3; width: 120px'><font size='2'>";
    protected static final String HEADERENDTAG = "</TH>";
    private static WebDriver driver = TestMembersFactory.getDriver();

    /**
     * Method to get current date and time.
     *
     * @return Return current date and time as a string.
     */
    public static String getCurrentDateAndTime() {
        SimpleDateFormat oFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Date oDate = new Date();
        return oFormat.format(oDate).toString();
    }

    /**
     * Method to get current date.
     *
     * @return Return current date as a string.
     */
    public static String getCurrentDate() {
        SimpleDateFormat oFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date oDate = new Date();
        return oFormat.format(oDate).toString();
    }

    /**
     * Method to get current time.
     *
     * @return Return current time as a string.
     */
    public static String getCurrentTime() {
        SimpleDateFormat oFormat = new SimpleDateFormat("hh:mm:ss a");
        Date oDate = new Date();
        return oFormat.format(oDate).toString();
    }

    /**
     * Format the Expected Result and Actual Result for Object Found and/or
     * Validation to report. Return it as a String Value.
     *
     * @param sFieldName Field Name to report
     * @param sExpectedValue Expected Result message for object.
     * @param sActualValue Actual Result message for object.
     * @return HTML formatted message for validation
     */
    public static String formatResultsInTable(String sFieldName, String sExpectedValue, String sActualValue) {
        return getTableStartTag()
                + getHeaderWithColumns(new String[] { "Field Name", "Expected Value", "Actual Value" })
                + getRowWithColumns(new String[] { sFieldName, sExpectedValue, sActualValue })
                + getTableEndTag();
    }


    public static String getTableStartTag(){
        return TABLESTARTTAG;
    }

    public static String getTableStartTag(String heading){
        return TABLESTARTTAG +"<CAPTION><B><font size='3'>"+ heading+"</B></CAPTION>";
    }

    /**
     * Method to get header with column headers
     *
     * @param sColumns String array of column headers
     * @return HTML code of heading with column headers
     */
    public static String getHeaderWithColumns(String[] sColumns) {
        String sTotal = ROWSTARTTAG;
        for (int iColumn = 0; iColumn < sColumns.length; iColumn++) {
            sTotal += HEADERSTARTTAG + sColumns[iColumn] + HEADERENDTAG;
        }
        return sTotal+ROWENDTAG;
    }

    public static String getRowWithColumns(String[] sColumns){
        StringBuilder sTotal = new StringBuilder(ROWSTARTTAG);
        for (String sColumn : sColumns) {
            sTotal.append(COLUMNSTARTTAG).append(sColumn).append(COLUMNENDTAG);
        }
        return sTotal+ROWENDTAG;
    }

    public static String getTableEndTag(){
        return TABLEENDTAG;
    }

    /**
     * Format the line at the starting and ending of the scenario Return it as a
     * String Value.
     *
     * @param sLine Line to enter
     * @return the formatted HTML code for heading
     */
    public static String formatStartAndEndOfScenario (String sLine) {
        return "<b><p align='center' style='border: 2px solid #FFFFFF; border-image: none; background-color: #333333; color: #D3D3D3;'>------ "
                + sLine + " ------</p></b>";
    }

    /**
     * Method to provide a time delay in Seconds
     *
     * @param iTimeSec
     */
    public static void timeDelay(int iTimeSec){
        try{
            Thread.sleep(iTimeSec*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


    /**
     * Method to get a cell value with color (Pass/Fail)
     *
     * @param status Status to color
     * @return HTML code of value with color
     */
    public static String getStatusWithColor (String status) {
        return switch (status.toUpperCase()) {
            case "PASS" -> "<b><font color='green'>" + status + "</font></b>";
            case "FAIL" -> "<b><font color='red'>" + status + "</font></b>";
            default -> status;
        };
    }


    /**
     * Method to get test parameter from local test listener. Else get value from config.properties file
     *
     * @param sParameter
     * @return
     */

    public static String getTestParameter(String sParameter){

        try {
            if ((System.getProperty(sParameter) != null) && (!System.getProperty(sParameter).isEmpty())) {
                return System.getProperty(sParameter);
            }
            Map<String, String> caps = CustomCucumberTestNGTests.getTestListener().
                    getCurrentXmlTest().getLocalParameters();
            if (caps.containsKey(sParameter)) {
                return caps.get(sParameter);
            } else if (!(new ProjectConfigurations().getData(sParameter).isEmpty())) {
                return new ProjectConfigurations().getData(sParameter);
            } else {
                return "";
            }
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * Method to get the user password using JOption
     * @param userId User ID
     * @return user password
     */
    protected String getPasswordFromUser(String userId) {
        String sPassword = "";
        System.out.println("Please enter password for " + userId + " in the popup...");
        int iCount = 0;
        do {
            try {
                JPasswordField obj = new JPasswordField();
                JOptionPane.showConfirmDialog(null, obj, "Enter Password for " + userId, 2);
                sPassword = new String(obj.getPassword());
                if (sPassword.trim().isEmpty()) {
                    int iOption = JOptionPane.showConfirmDialog(null,
                            "You have not Entered the Password for " + userId + ". Do you want to retry?\n"
                                    + "Click on Yes if you want retry entering the password.\n"
                                    + "Press No to stop the current run and review the User ID in Login Sheet.",
                            "", JOptionPane.YES_NO_OPTION);
                    if (iOption == 1) {
                        CommonMethods.proceedFurther.set(false);
                        return sPassword;
                    }
                } else {
                    break;
                }
            } catch (Exception e) {
            }
            iCount++;
        } while (iCount < 3);

        if (iCount == 3) {
            JOptionPane.showMessageDialog(null,
                    "You have exceeded the maximum number of attempts. Please review the Originator ID in login sheet and Retry running the script.");
            CommonMethods.proceedFurther.set(false);
        }
        System.out.println("Password entered");
        return sPassword;
    }


    /**
     * Method to check the loading status of any page
     * @throws IOException
     */
    protected void checkSpinnerLoading(By spinnerSymbol, int timeout) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return self.name"));

        int iterator = 0;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String sCurrentFrame = "";

        try {
            sCurrentFrame = jsExecutor.executeScript("return self.name").toString();
        } catch (JavascriptException e) {
        }

        driver.switchTo().defaultContent();

//        while (CommonMethods.elementIsVisibleNoReporting(spinnerSymbol, timeout)) {
//            iterator++;
//            if (iterator > timeout) {
//                break;
//            }
//        }
    }



}
