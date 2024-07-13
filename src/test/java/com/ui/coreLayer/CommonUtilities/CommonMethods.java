package com.ui.coreLayer.CommonUtilities;

import com.ui.coreLayer.FrameworkConfigs.CustomCucumberTestNGTests;
import com.ui.coreLayer.FrameworkConfigs.ProjectConfigurations;

import java.text.SimpleDateFormat;
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

}
