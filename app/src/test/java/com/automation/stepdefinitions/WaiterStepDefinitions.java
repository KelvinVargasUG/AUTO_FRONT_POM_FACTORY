package com.automation.stepdefinitions;

import com.automation.steps.LoginSteps;
import com.automation.steps.WaiterSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class WaiterStepDefinitions {

    @Steps
    LoginSteps loginSteps;

    @Steps
    WaiterSteps waiterSteps;

    @Given("el usuario inicia sesión y accede a la vista de mesas")
    public void userLogsInAndAccessesWaiterView() {
        loginSteps.navigateToLoginPage();
        loginSteps.enterValidCredentials();
        loginSteps.clickLoginButton();
        waiterSteps.verifyWaiterDashboardIsDisplayed();
    }

    @When("selecciona la primera mesa disponible")
    public void userSelectsFirstTable() {
        waiterSteps.selectFirstTable();
    }

    @And("agrega una nueva orden")
    public void userClicksAddOrder() {
        waiterSteps.clickAddOrder();
    }

    @And("ingresa el nombre del cliente {string}")
    public void userEntersCustomerName(String name) {
        waiterSteps.enterCustomerName(name);
    }

    @And("ingresa el email del cliente {string}")
    public void userEntersCustomerEmail(String email) {
        waiterSteps.enterCustomerEmail(email);
    }

    @And("envía la orden a la cocina")
    public void userSendsToKitchen() {
        waiterSteps.clickSendToKitchen();
    }

    @Then("el sistema procesa la orden correctamente")
    public void systemProcessesOrder() {
        waiterSteps.verifyOrderProcessed();
    }
}
