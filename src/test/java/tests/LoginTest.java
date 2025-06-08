package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Automatically downloads the driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testValidLogin() {
        driver.get("https://automationexercise.com");

        // Click on "Signup / Login"
        driver.findElement(By.linkText("Signup / Login")).click();

        // Enter email and password
        driver.findElement(By.name("email")).sendKeys("moe.faridd@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Ironmaiden99!");

        // Click login button
        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

        // Verify login success
        String welcomeText = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]")).getText();
        System.out.println("Login Success: " + welcomeText);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
