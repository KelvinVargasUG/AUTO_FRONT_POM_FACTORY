package com.automation.stepdefinitions;

import com.automation.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class LoginStepDefinitions {

    @Steps
    LoginSteps loginSteps;

    @Given("el usuario navega a la página de login")
    public void userNavigatesToLoginPage() {
        loginSteps.navigateToLoginPage();
    }

    @When("ingresa credenciales válidas")
    public void userEntersValidCredentials() {
        loginSteps.enterValidCredentials();
    }

    @When("ingresa credenciales inválidas")
    public void userEntersInvalidCredentials() {
        loginSteps.enterInvalidCredentials();
    }

    @And("pulsa el botón de login")
    public void userClicksLoginButton() {
        loginSteps.clickLoginButton();
    }

    @Then("accede al sistema y es redirigido a la vista principal")
    public void userIsRedirectedToMainView() {
        loginSteps.verifyRedirectedToMainView();
    }

    @Then("se muestra un mensaje de error")
    public void errorMessageIsDisplayed() {
        loginSteps.verifyErrorMessageIsDisplayed();
    }

    @And("el usuario permanece en la página de login")
    public void userRemainsOnLoginPage() {
        loginSteps.verifyStillOnLoginPage();
    }
}
