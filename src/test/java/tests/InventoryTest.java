package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.InventoryPage;
import utils.DataDriven;
import com.google.gson.JsonObject;
import org.testng.Assert;

public class InventoryTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private DataDriven dd;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        String path = System.getProperty("user.dir") + "/src/test/resources/testData.json";
        dd = new DataDriven(path);
    }

    @Test
    public void inventoryElementsAfterLogin() throws InterruptedException {
        loginPage.open();
        JsonObject valid = dd.getObject("valid");
        loginPage.enterUsername(valid.get("username").getAsString());
        loginPage.enterPassword(valid.get("password").getAsString());
        loginPage.clickLogin();
        Thread.sleep(1500);

        // Title
        String title = inventoryPage.getTitle();
        Assert.assertEquals(title, "Swag Labs", "Page title should be 'Swag Labs' but was: " + title);

        // Cart icon
        Assert.assertTrue(inventoryPage.isCartDisplayed(), "Cart icon should be displayed");

        // Product count = 6
        Assert.assertEquals(inventoryPage.productCount(), 6, "There should be 6 products on inventory page");
    }
}
