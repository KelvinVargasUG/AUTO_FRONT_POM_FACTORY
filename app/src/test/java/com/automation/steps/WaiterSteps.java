package com.automation.steps;

import com.automation.pages.WaiterViewPage;
import net.serenitybdd.annotations.Step;
import org.junit.Assert;

public class WaiterSteps {

    WaiterViewPage waiterViewPage;

    @Step("Verify waiter dashboard is displayed")
    public void verifyWaiterDashboardIsDisplayed() {
        Assert.assertTrue("Table selector should be visible", waiterViewPage.isTableSelectorVisible());
    }

    @Step("Select first available table")
    public void selectFirstTable() {
        waiterViewPage.selectFirstTable();
    }

    @Step("Click add order button")
    public void clickAddOrder() {
        waiterViewPage.clickAddOrder();
    }

    @Step("Enter customer name: {0}")
    public void enterCustomerName(String name) {
        waiterViewPage.enterCustomerName(name);
    }

    @Step("Enter customer email: {0}")
    public void enterCustomerEmail(String email) {
        waiterViewPage.enterCustomerEmail(email);
    }

    @Step("Click send to kitchen")
    public void clickSendToKitchen() {
        waiterViewPage.clickSendToKitchen();
    }

    @Step("Verify order was processed")
    public void verifyOrderProcessed() {
        Assert.assertFalse(
                "Send to kitchen button should no longer be visible after submitting",
                waiterViewPage.isSendToKitchenButtonVisible()
        );
    }
}
