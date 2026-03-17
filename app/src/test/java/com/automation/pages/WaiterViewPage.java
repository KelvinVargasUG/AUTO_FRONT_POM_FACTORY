package com.automation.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Alert;

import java.util.List;

public class WaiterViewPage extends PageObject {

    @FindBy(css = "[data-testid='table-grid']")
    private WebElementFacade tableSelector;

    @FindBy(css = "[data-testid='table-item-1']")
    private WebElementFacade firstTable;

    @FindBy(css = "[data-testid='add-order-btn']")
    private WebElementFacade addOrderButton;

    @FindBy(css = "[data-testid='customer-name-input']")
    private WebElementFacade customerNameInput;

    @FindBy(css = "[data-testid='customer-email-input']")
    private WebElementFacade customerEmailInput;

    @FindBy(css = "[data-testid='send-to-kitchen-btn']")
    private WebElementFacade sendToKitchenButton;

    @FindBy(css = "[data-testid^='table-item-']")
    private List<WebElementFacade> tableItems;

    public boolean isTableSelectorVisible() {
        return tableSelector.isCurrentlyVisible();
    }

    public void selectFirstTable() {
        firstTable.click();
        waitABit(1000);
    }

    public void clickAddOrder() {
        try {
            Alert alert = getDriver().switchTo().alert();
            alert.dismiss();
            waitABit(500);
        } catch (Exception ignored) {
        }
        addOrderButton.click();
    }

    public void enterCustomerName(String name) {
        customerNameInput.clear();
        customerNameInput.sendKeys(name);
    }

    public void enterCustomerEmail(String email) {
        customerEmailInput.clear();
        customerEmailInput.sendKeys(email);
    }

    public void clickSendToKitchen() {
        sendToKitchenButton.click();
    }

    public boolean isSendToKitchenButtonVisible() {
        try {
            return sendToKitchenButton.isCurrentlyVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public int getTableCount() {
        return tableItems.size();
    }
}
