package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.enterEmail("moe.faridd@gmail.com");  // Update with your real test account
        loginPage.enterPassword("Ironmaiden99!");
        loginPage.clickLogin();

        String text = loginPage.getLoggedInText();
        Assert.assertTrue(text.contains("Logged in as"), "Login was not successful!");
    }
}
