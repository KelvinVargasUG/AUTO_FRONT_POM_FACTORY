package com.automation.stepdefinitions;

import com.automation.steps.BulkUploadSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class CargaMasivaStepDefinitions {

    @Steps
    BulkUploadSteps bulkUploadSteps;

    @Given("el administrador inicia sesión y navega a la sección de carga masiva")
    public void adminLogsInAndNavigatesToBulkUpload() {
        bulkUploadSteps.loginAndNavigateToBulkUpload();
    }

    @Then("debería ver la zona de arrastre para subir el archivo CSV")
    public void dropZoneShouldBeVisible() {
        bulkUploadSteps.verifyDropZoneIsVisible();
    }

    @And("debería ver el botón para descargar la plantilla CSV")
    public void downloadTemplateBtnShouldBeVisible() {
        bulkUploadSteps.verifyDownloadTemplateButtonIsVisible();
    }

    @When("sube un archivo CSV con productos válidos")
    public void adminUploadsCSVFile() {
        bulkUploadSteps.uploadTestCSV();
    }

    @Then("debería ver el resumen de la carga con los productos procesados")
    public void uploadSummaryShouldBeVisible() {
        bulkUploadSteps.verifyUploadSummaryIsVisible();
    }

    @When("sube un archivo CSV con cabeceras incorrectas")
    public void adminUploadsInvalidCSV() {
        bulkUploadSteps.uploadInvalidCSV();
    }

    @Then("debería ver un mensaje de error indicando el problema con el formato")
    public void shouldSeeFormatErrorMessage() {
        bulkUploadSteps.verifyUploadErrorMessageIsVisible();
    }

    @When("intenta subir un archivo que supera el tamaño máximo permitido")
    public void adminUploadsOversizedFile() {
        bulkUploadSteps.attemptUploadOversizedFile();
    }

    @Then("debería ver un mensaje indicando que el archivo es demasiado grande")
    public void shouldSeeFileSizeLimitMessage() {
        bulkUploadSteps.verifyFileSizeLimitMessageIsVisible();
    }

    @When("sube un archivo CSV con registros inválidos")
    public void adminUploadsCSVWithInvalidRecords() {
        bulkUploadSteps.uploadInvalidCSV();
    }

    @Then("debería ver el enlace para descargar el reporte de errores")
    public void shouldSeeErrorReportLink() {
        bulkUploadSteps.verifyErrorReportLinkIsVisible();
    }

    @Then("los productos cargados deberían aparecer en el catálogo de productos")
    public void uploadedProductsShouldAppearInCatalog() {
        bulkUploadSteps.navigateToCatalogAndVerifyProducts();
    }
}
