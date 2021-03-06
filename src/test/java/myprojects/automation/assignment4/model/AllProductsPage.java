package myprojects.automation.assignment4.model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AllProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ProductData productData;

    private By nextPageButton = By.xpath("//a[@rel='next']");

    public AllProductsPage(WebDriver driver, WebDriverWait wait, ProductData productData) {
        this.driver = driver;
        this.wait = wait;
        this.productData = productData;
    }

    public void clickOnCreatedProduct() {
        try {
            assertProductDisplayCheck();
            openCreatedProduct();
        } catch (NoSuchElementException err0) {
            wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
            driver.findElement(nextPageButton).click();
            try {
                assertProductDisplayCheck();
                openCreatedProduct();
            } catch (NoSuchElementException err1) {
                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
                driver.findElement(nextPageButton).click();
                try {
                    assertProductDisplayCheck();
                    openCreatedProduct();
                } catch (NoSuchElementException err2) {
                    wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));
                    driver.findElement(nextPageButton).click();
                }
            }
        }

    }

    private void assertProductDisplayCheck() {
        String createdProductName = driver.findElement(By.xpath("//*[contains(text(),'" + productData.getName() + "')]")).getText();
        Assert.assertEquals(createdProductName, productData.getName(), "Product hasn't been found on the page.");
    }

    private void openCreatedProduct() {
        WebElement element1 = driver.findElement(By.xpath("//*[contains(text(),'" + productData.getName() + "')]"));
        wait.until(ExpectedConditions.elementToBeClickable(element1));
        element1.click();
    }
}
