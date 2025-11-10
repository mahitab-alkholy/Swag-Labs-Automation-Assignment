package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.DataDriven;
import com.google.gson.JsonObject;
import org.testng.Assert;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    private DataDriven dd;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        String path = System.getProperty("user.dir") + "/src/test/resources/testData.json";
        dd = new DataDriven(path);
    }

    @Test
    public void successfulLogin() throws InterruptedException {
        loginPage.open();
        JsonObject valid = dd.getObject("valid");
        loginPage.enterUsername(valid.get("username").getAsString());
        loginPage.enterPassword(valid.get("password").getAsString());
        loginPage.clickLogin();
        Thread.sleep(1500);
        String current = driver.getCurrentUrl();
        Assert.assertTrue(current.contains("/inventory.html"), "URL should contain /inventory.html but was: " + current);
    }

    @Test
    public void invalidLogin() throws InterruptedException {
        loginPage.open();
        JsonObject invalid = dd.getObject("invalid");
        loginPage.enterUsername(invalid.get("username").getAsString());
        loginPage.enterPassword(invalid.get("password").getAsString());
        loginPage.clickLogin();
        Thread.sleep(800);
        String err = loginPage.getErrorMessage();
        Assert.assertTrue(err.toLowerCase().contains("username and password do not match") ||
                          err.toLowerCase().contains("username and password do not match."),
                          "Error message should mention username and password do not match. Actual: " + err);
    }

    @Test
    public void loginWithoutPassword() throws InterruptedException {
        loginPage.open();
        JsonObject np = dd.getObject("no_password");
        loginPage.enterUsername(np.get("username").getAsString());
        loginPage.enterPassword(np.get("password").getAsString());
        loginPage.clickLogin();
        Thread.sleep(800);
        String err = loginPage.getErrorMessage();
        Assert.assertTrue(err.toLowerCase().contains("password is required") ||
                          err.toLowerCase().contains("password is required."),
                          "Error message should mention password is required. Actual: " + err);
    }
}
