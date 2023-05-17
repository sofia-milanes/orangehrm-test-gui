package com.orangehrm.tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.orangehrm.tests.driver.DriverFactory;
import com.orangehrm.tests.utils.BrowserConsoleListener;

import org.junit.Test;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners(BrowserConsoleListener.class)
public class OrangeHRMTest
{
    @Parameters({"Browser", "SiteAddress"})
    @BeforeSuite(alwaysRun = true)
    public void orangeHRMTestSuiteSetup(
            @Optional final String driverType,
            @Optional final String siteAddress
    ) {
        DriverFactory.instance().ensureConfig("chrome", siteAddress);
    }

    @AfterMethod(alwaysRun = true)
    public void orangeHRMTestTearDown(ITestResult result) {
        try {
            DriverFactory.instance().destroyDriver();
        } catch (RuntimeException e) {
            fail("Failed to destroy driver: " + e.toString());
        }
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
