package com.automation.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.annotations.DefaultUrl;
import org.openqa.selenium.Keys;

import java.util.List;

@DefaultUrl("/admin")
public class AdminPage extends PageObject {

    @FindBy(css = "[data-testid='nav-admin']")
    private WebElementFacade navAdminLink;

    @FindBy(id = "name")
    private WebElementFacade nameInput;

    @FindBy(id = "type")
    private WebElementFacade typeSelect;

    @FindBy(id = "category")
    private WebElementFacade categoryInput;

    @FindBy(id = "price")
    private WebElementFacade priceInput;

    @FindBy(css = "[data-testid='create-product-button']")
    private WebElementFacade createButton;

    @FindBy(css = "[data-testid='product-list']")
    private WebElementFacade productList;

    @FindBy(css = "[data-testid^='toggle-status-']")
    private List<WebElementFacade> deactivateButtons;

    @FindBy(css = "[data-testid^='edit-product-']")
    private List<WebElementFacade> editButtons;

    @FindBy(id = "edit-name")
    private WebElementFacade editNameInput;

    @FindBy(id = "edit-type")
    private WebElementFacade editTypeSelect;

    @FindBy(id = "edit-category")
    private WebElementFacade editCategoryInput;

    @FindBy(id = "edit-price")
    private WebElementFacade editPriceInput;

    @FindBy(id = "edit-status")
    private WebElementFacade editStatusSelect;

    @FindBy(xpath = "//*[@id='edit-name']/ancestor::form//button[@type='submit']")
    private WebElementFacade editSaveButton;

    @FindBy(css = "[data-testid='form-validation-error']")
    private WebElementFacade validationErrorMessage;

    @FindBy(css = "[data-testid='toggle-error-message']")
    private WebElementFacade toggleErrorMessage;

    @FindBy(css = "[data-testid='product-status-inactive']")
    private List<WebElementFacade> inactiveProductBadges;

    public void clickNavAdmin() {
        navAdminLink.click();
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void selectType(String type) {
        typeSelect.selectByValue(type);
    }

    public void enterCategory(String category) {
        categoryInput.clear();
        categoryInput.sendKeys(category);
    }

    public void enterPrice(String price) {
        priceInput.clear();
        priceInput.sendKeys(price);
    }

    public void clickCreateButton() {
        createButton.click();
    }

    public boolean isProductListVisible() {
        waitFor(productList).waitUntilVisible();
        return productList.isCurrentlyVisible();
    }

    public void clickFirstDeactivateButton() {
        waitFor(deactivateButtons.get(0)).click();
    }

    public void clickFirstEditButton() {
        waitFor(editButtons.get(0)).click();
    }

    public void clearAndTypeEditName(String name) {
        waitFor(editNameInput);
        editNameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        if (!name.isEmpty()) {
            editNameInput.sendKeys(name);
        }
    }

    public void clickEditSaveButton() {
        editSaveButton.click();
    }

    public boolean isValidationErrorMessageVisible() {
        try {
            waitFor(validationErrorMessage).waitUntilVisible();
            return validationErrorMessage.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isToggleErrorMessageVisible() {
        try {
            waitFor(toggleErrorMessage);
            return toggleErrorMessage.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasNoInactiveProductBadges() {
        return inactiveProductBadges.isEmpty();
    }
}
