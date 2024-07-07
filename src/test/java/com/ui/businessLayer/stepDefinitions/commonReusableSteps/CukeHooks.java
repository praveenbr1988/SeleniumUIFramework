package com.ui.businessLayer.stepDefinitions.commonReusableSteps;

import com.ui.coreLayer.FrameworkConfigs.LoggerUtil;
import com.ui.orchestrationLayer.Generics.TestParameters;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


import java.io.IOException;

public class CukeHooks {

    private static final Logger logger = LoggerUtil.getLogger(CukeHooks.class);

    @BeforeAll
    public static void beforeAll() throws IOException {
    }

    @AfterAll
    public static void afterAll() throws IOException {
    }

    @Before
    public void setUp(Scenario scenario) {
        TestParameters.setScenario(scenario);
        //addStepLog("Execution started for Scenario: " + TestParameters.getScenario().getName());
        logger.info("Cuke Hooks-Before Scenario");
        System.out.println("Cuke Hooks-Before Scenario: " + TestParameters.getScenario().getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        //addStepLog("Execution completed for Scenario: " + TestParameters.getScenario().getName() + " and driver closed");
        logger.info("After Scenario");
        System.out.println("Cuke Hooks-After Scenario: " + TestParameters.getScenario().getName() + " and driver closed");

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) throws IOException {
        //addScreenshot(scenario);
        logger.info("Cuke Hooks-Before Step");
        System.out.println("Cuke Hooks-Before Step: " + TestParameters.getScenario().getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        //addScreenshot(scenario);
        logger.info("Cuke Hooks-After Step");
        System.out.println("Cuke Hooks-After Step: " + TestParameters.getScenario().getName());
    }


}
