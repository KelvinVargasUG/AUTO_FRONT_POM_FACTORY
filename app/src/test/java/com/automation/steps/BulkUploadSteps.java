package com.automation.steps;

import com.automation.pages.BulkUploadPage;
import com.automation.pages.AdminPage;
import com.automation.pages.LoginPage;
import net.serenitybdd.annotations.Step;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Assert;

import java.nio.file.Paths;

public class BulkUploadSteps {

    BulkUploadPage bulkUploadPage;
    AdminPage adminPage;
    LoginPage loginPage;
    EnvironmentVariables environmentVariables;

    @Step("Iniciar sesión y navegar a Carga Masiva")
    public void loginAndNavigateToBulkUpload() {
        loginPage.open();
        String email = environmentVariables.getProperty("test.credentials.valid.email");
        String password = environmentVariables.getProperty("test.credentials.valid.password");
        loginPage.enterCredentials(email, password);
        loginPage.clickLoginButton();
        bulkUploadPage.clickNavBulkUpload();
    }

    @Step("Verificar que la zona de arrastre CSV es visible")
    public void verifyDropZoneIsVisible() {
        Assert.assertTrue(
            "La zona de carga CSV debe ser visible",
            bulkUploadPage.isDropZoneVisible()
        );
    }

    @Step("Verificar que el botón de descarga de plantilla es visible")
    public void verifyDownloadTemplateButtonIsVisible() {
        Assert.assertTrue(
            "El botón de descarga de plantilla debe ser visible",
            bulkUploadPage.isDownloadTemplateButtonVisible()
        );
    }

    @Step("Subir el archivo CSV de prueba")
    public void uploadTestCSV() {
        try {
            String csvPath = Paths.get(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResource("files/productos_prueba.csv")
                      .toURI()
            ).toString();
            bulkUploadPage.uploadFile(csvPath);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar el archivo CSV de prueba", e);
        }
    }

    @Step("Verificar que el panel de resumen de carga es visible")
    public void verifyUploadSummaryIsVisible() {
        Assert.assertTrue(
            "El panel de resumen de la carga debe ser visible",
            bulkUploadPage.isUploadSummaryVisible()
        );
    }

    @Step("Subir archivo CSV con cabeceras incorrectas")
    public void uploadInvalidCSV() {
        bulkUploadPage.uploadInvalidCsvFile();
    }

    @Step("Verificar que el mensaje de error de formato CSV es visible")
    public void verifyUploadErrorMessageIsVisible() {
        Assert.assertTrue(
            "El mensaje de error de formato debe ser visible",
            bulkUploadPage.isUploadErrorMessageVisible()
        );
    }

    @Step("Intentar subir un archivo que supera el tamaño máximo permitido")
    public void attemptUploadOversizedFile() {
        bulkUploadPage.uploadOversizedFile();
    }

    @Step("Verificar que el mensaje de límite de tamaño es visible")
    public void verifyFileSizeLimitMessageIsVisible() {
        Assert.assertTrue(
            "El mensaje de límite de tamaño debe ser visible",
            bulkUploadPage.isUploadSizeErrorVisible()
        );
    }

    @Step("Verificar que el enlace de descarga del reporte de errores es visible")
    public void verifyErrorReportLinkIsVisible() {
        Assert.assertTrue(
            "El enlace de descarga de errores debe ser visible",
            bulkUploadPage.isErrorReportLinkVisible()
        );
    }

    @Step("Navegar al catálogo y verificar que los productos cargados son visibles")
    public void navigateToCatalogAndVerifyProducts() {
        adminPage.clickNavAdmin();
        Assert.assertTrue(
            "Los productos cargados deben aparecer en el catálogo",
            adminPage.isProductListVisible()
        );
    }
}
