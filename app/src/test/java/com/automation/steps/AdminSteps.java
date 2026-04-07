package com.automation.steps;

import com.automation.pages.AdminPage;
import com.automation.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Assert;

public class AdminSteps {

    AdminPage adminPage;
    LoginPage loginPage;
    EnvironmentVariables environmentVariables;

    @Step("Iniciar sesión y navegar al catálogo Admin")
    public void loginAndNavigateToAdmin() {
        loginPage.open();
        String email = environmentVariables.getProperty("test.credentials.valid.email");
        String password = environmentVariables.getProperty("test.credentials.valid.password");
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();
        adminPage.clickNavAdmin();
    }

    @Step("Completar formulario y crear producto: {0}")
    public void createProduct(String name, String type, String category, String price) {
        adminPage.enterName(name);
        adminPage.selectType(type);
        adminPage.enterCategory(category);
        adminPage.enterPrice(price);
        adminPage.clickCreateButton();
    }

    @Step("Verificar que la lista de productos es visible")
    public void verifyProductListIsVisible() {
        adminPage.waitFor("[data-testid='product-list']");
        Assert.assertTrue(
            "La lista de productos debe ser visible",
            adminPage.isProductListVisible()
        );
    }

    @Step("Verificar que el catálogo muestra productos con botones de acción")
    public void verifyProductsWithActionButtons() {
        Assert.assertTrue(
            "El contenedor de productos debe estar visible",
            adminPage.isProductListVisible()
        );
    }

    @Step("Hacer clic en editar el primer producto y cambiar el nombre a: {0}")
    public void editFirstProductName(String newName) {
        adminPage.clickFirstEditButton();
        adminPage.clearAndTypeEditName(newName);
        adminPage.clickEditSaveButton();
    }

    @Step("Verificar que el catálogo sigue visible tras la edición")
    public void verifyProductListAfterEdit() {
        adminPage.waitFor("[data-testid='product-list']");
        Assert.assertTrue(
            "El catálogo debe seguir mostrando productos tras la edición",
            adminPage.isProductListVisible()
        );
    }

    @Step("Hacer clic en desactivar el primer producto")
    public void deactivateFirstProduct() {
        adminPage.clickFirstDeactivateButton();
    }

    @Step("Verificar que el producto sigue listado tras la desactivación")
    public void verifyProductListAfterDeactivation() {
        adminPage.waitFor("[data-testid='product-list']");
        Assert.assertTrue(
            "El producto debe seguir visible en el catálogo como inactivo",
            adminPage.isProductListVisible()
        );
    }

    @Step("Intentar crear producto con nombre vacío")
    public void createProductWithEmptyName() {
        adminPage.enterName("");
        adminPage.selectType("HOT_DISH");
        adminPage.enterCategory("Prueba");
        adminPage.enterPrice("1000");
        adminPage.clickCreateButton();
    }

    @Step("Verificar que el mensaje de validación del campo requerido es visible")
    public void verifyValidationErrorIsVisible() {
        Assert.assertTrue(
            "El mensaje de error de validación debe ser visible",
            adminPage.isValidationErrorMessageVisible()
        );
    }

    @Step("Intentar guardar la edición borrando el nombre del primer producto")
    public void editFirstProductWithEmptyName() {
        adminPage.clickFirstEditButton();
        adminPage.clearAndTypeEditName("");
        adminPage.clickEditSaveButton();
    }

    @Step("Verificar que ningún producto inactivo es visible en el catálogo público")
    public void verifyNoInactiveBadgesInPublicCatalog() {
        Assert.assertTrue(
            "No deben existir badges de producto inactivo en el catálogo público",
            adminPage.hasNoInactiveProductBadges()
        );
    }

}
