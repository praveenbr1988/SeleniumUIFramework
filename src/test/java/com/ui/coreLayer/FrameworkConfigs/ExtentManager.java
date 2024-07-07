package com.ui.coreLayer.FrameworkConfigs;

import com.aventstack.extentreports.ExtentTest;


public class ExtentManager {

    private ExtentManager() {
    }

    private final static ExtentManager instance = new ExtentManager();

    public static ExtentManager getInstance() {
        return instance;
    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void setExtent(ExtentTest extentTestObj) {
        ExtentManager.extentTest.set(extentTestObj);
    }

    public ExtentTest getExtent() {
        return ExtentManager.extentTest.get();
    }

    public void removeExtentTestObject(){
        extentTest.remove();
    }


}
