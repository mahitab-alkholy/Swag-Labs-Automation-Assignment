package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    private WebDriver driver;
    private By cartIcon = By.className("shopping_cart_link");
    private By products = By.className("inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isCartDisplayed() {
        try {
            return driver.findElement(cartIcon).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public int productCount() {
        List<WebElement> items = driver.findElements(products);
        return items.size();
    }
}
