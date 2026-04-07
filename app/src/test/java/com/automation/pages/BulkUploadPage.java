package com.automation.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class BulkUploadPage extends PageObject {

    @FindBy(css = "[data-testid='nav-bulk-upload']")
    private WebElementFacade navBulkUploadLink;

    @FindBy(css = "[data-testid='csv-drop-zone']")
    private WebElementFacade dropZone;

    @FindBy(css = "[data-testid='csv-file-input']")
    private WebElementFacade fileInput;

    @FindBy(css = "[data-testid='download-template-btn']")
    private WebElementFacade downloadTemplateButton;

    @FindBy(css = "[data-testid='upload-summary-title']")
    private WebElementFacade uploadSummaryTitle;

    @FindBy(xpath = "/html/body/div/div/div/div/div[3]/p")
    private WebElementFacade uploadErrorMessage;

    @FindBy(css = "[data-testid='upload-size-error']")
    private WebElementFacade uploadSizeError;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div/button")
    private WebElementFacade errorReportLink;

    public void clickNavBulkUpload() {
        navBulkUploadLink.click();
    }

    public boolean isDropZoneVisible() {
        return dropZone.isCurrentlyVisible();
    }

    public boolean isDownloadTemplateButtonVisible() {
        return downloadTemplateButton.isCurrentlyVisible();
    }

    public void uploadFile(String absolutePath) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement input = getDriver().findElement(By.cssSelector("[data-testid='csv-file-input']"));
        js.executeScript(
            "arguments[0].style.display='block';" +
            "arguments[0].style.visibility='visible';" +
            "arguments[0].removeAttribute('disabled');",
            input
        );
        input.sendKeys(absolutePath);
    }

    public boolean isUploadSummaryVisible() {
        try {
            waitFor(uploadSummaryTitle).waitUntilVisible();
            return uploadSummaryTitle.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUploadErrorMessageVisible() {
        try {
            waitFor(uploadErrorMessage).waitUntilVisible();
            return uploadErrorMessage.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUploadSizeErrorVisible() {
        try {
            waitFor(uploadSizeError).waitUntilVisible();
            return uploadSizeError.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorReportLinkVisible() {
        try {
            waitFor(errorReportLink).waitUntilVisible();
            return errorReportLink.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadInvalidCsvFile() {
        try {
            String csvPath = java.nio.file.Paths.get(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResource("files/cabeceras_invalidas.csv")
                      .toURI()
            ).toString();
            uploadFile(csvPath);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar el archivo CSV de cabeceras inválidas", e);
        }
    }

    public void uploadOversizedFile() {
        try {
            String csvPath = java.nio.file.Paths.get(
                Thread.currentThread()
                      .getContextClassLoader()
                      .getResource("files/mayor_al_peso_permitido.csv")
                      .toURI()
            ).toString();
            uploadFile(csvPath);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar el archivo mayor_al_peso_permitido.csv", e);
        }
    }
}
