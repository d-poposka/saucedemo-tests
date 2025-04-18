package com.vestel.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceDemoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        System.out.println("Opened SauceDemo website.");
    }

    @Test
    public void fullTestFlow() {
        System.out.println("Starting login process...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
        System.out.println("Logged in successfully.");

        System.out.println("Adding product to cart...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        System.out.println("Product added to cart.");

        System.out.println("Verifying product in cart...");
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
        Assert.assertTrue(cartItem.isDisplayed(), "Cart item is not displayed!");
        System.out.println("Product is visible in the cart.");

        System.out.println("Proceeding to checkout...");
        driver.findElement(By.id("checkout")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Dara");
        driver.findElement(By.id("last-name")).sendKeys("Poposka");
        driver.findElement(By.id("postal-code")).sendKeys("1000");
        driver.findElement(By.id("continue")).click();
        System.out.println("Checkout information entered.");

        System.out.println("Verifying checkout summary...");
        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("summary_info")));
        Assert.assertTrue(summary.isDisplayed(), "Checkout summary is not visible!");
        System.out.println("Checkout summary verified.");

        System.out.println("Finishing checkout...");
        driver.findElement(By.id("finish")).click();
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(confirmation.getText(), "Thank you for your order!", "Order confirmation message is incorrect!");
        System.out.println("Order completed and confirmed.");

        System.out.println("Logging out...");
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        Assert.assertTrue(loginButton.isDisplayed(), "Login button not displayed after logout!");
        System.out.println("Successfully logged out and returned to login page.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing the browser...");
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
