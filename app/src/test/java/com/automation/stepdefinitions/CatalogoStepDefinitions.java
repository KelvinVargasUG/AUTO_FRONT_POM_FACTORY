package com.automation.stepdefinitions;

import com.automation.steps.AdminSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class CatalogoStepDefinitions {

    @Steps
    AdminSteps adminSteps;

    @Given("el administrador inicia sesión y navega al catálogo de productos")
    public void adminLogsInAndNavigatesToCatalog() {
        adminSteps.loginAndNavigateToAdmin();
    }

    @When("completa el formulario con datos válidos y crea el producto")
    public void adminFillsFormAndCreatesProduct() {
        long ts = System.currentTimeMillis();
        adminSteps.createProduct(
            "Producto POM " + ts,
            "HOT_DISH",
            "Prueba POM",
            "1500"
        );
    }

    @Then("debería ver el producto en la lista del catálogo")
    public void productShouldBeVisibleInCatalog() {
        adminSteps.verifyProductListIsVisible();
    }

    @When("el catálogo contiene productos registrados")
    public void catalogHasProducts() {

    }

    @Then("debería ver los productos listados con sus botones de acción")
    public void productsShouldBeListedWithButtons() {
        adminSteps.verifyProductsWithActionButtons();
    }

    @When("hace clic en editar el primer producto y cambia el nombre")
    public void adminClicksEditAndChangesName() {
        long ts = System.currentTimeMillis();
        adminSteps.editFirstProductName("Editado POM " + ts);
    }

    @Then("debería ver el catálogo actualizado")
    public void catalogShouldBeUpdated() {
        adminSteps.verifyProductListAfterEdit();
    }

    @When("hace clic en desactivar el primer producto de la lista")
    public void adminDeactivatesFirstProduct() {
        adminSteps.deactivateFirstProduct();
    }

    @Then("el producto debería estar listado como inactivo")
    public void productShouldBeInactive() {
        adminSteps.verifyProductListAfterDeactivation();
    }

    @When("intenta crear un producto sin completar el campo nombre")
    public void adminTriesToCreateWithoutName() {
        adminSteps.createProductWithEmptyName();
    }

    @Then("debería ver un mensaje de validación del campo requerido")
    public void shouldSeeValidationError() {
        adminSteps.verifyValidationErrorIsVisible();
    }

    @When("intenta guardar la edición borrando el nombre del primer producto")
    public void adminEditWithEmptyName() {
        adminSteps.editFirstProductWithEmptyName();
    }

    @Then("el producto desactivado no debería aparecer en el catálogo público")
    public void deactivatedProductNotInPublicCatalog() {
        adminSteps.verifyNoInactiveBadgesInPublicCatalog();
    }

}
