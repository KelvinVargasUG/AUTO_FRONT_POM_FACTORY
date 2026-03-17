package com.automation.steps;

import com.automation.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Assert;

public class LoginSteps {

    LoginPage loginPage;
    EnvironmentVariables environmentVariables;

    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        loginPage.open();
    }

    @Step("Enter credentials: {0} / {1}")
    public void enterCredentials(String email, String password) {
        loginPage.enterCredentials(email, password);
    }

    @Step("Enter valid credentials")
    public void enterValidCredentials() {
        String email = environmentVariables.getProperty("test.credentials.valid.email");
        String password = environmentVariables.getProperty("test.credentials.valid.password");
        loginPage.enterCredentials(email, password);
    }

    @Step("Enter invalid credentials")
    public void enterInvalidCredentials() {
        String email = environmentVariables.getProperty("test.credentials.invalid.email");
        String password = environmentVariables.getProperty("test.credentials.invalid.password");
        loginPage.enterCredentials(email, password);
    }

    @Step("Click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step("Verify error message is displayed")
    public void verifyErrorMessageIsDisplayed() {
        Assert.assertTrue("Error message should be visible", loginPage.isErrorMessageDisplayed());
    }

    @Step("Verify user is still on login page")
    public void verifyStillOnLoginPage() {
        Assert.assertTrue("User should remain on login page", loginPage.isOnLoginPage());
    }

    @Step("Verify user is NOT on login page (redirected to main view)")
    public void verifyRedirectedToMainView() {
        Assert.assertFalse("User should have been redirected away from login page", loginPage.isOnLoginPage());
    }
}
