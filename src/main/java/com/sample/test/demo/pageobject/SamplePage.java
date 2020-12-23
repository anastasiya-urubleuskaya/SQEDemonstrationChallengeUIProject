package com.sample.test.demo.pageobject;

import com.sample.test.demo.constants.PaymentMethods;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.model.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePage extends BasePage {

    public SamplePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "pizza1Pizza")
    private WebElement pizza1;

    @FindBy(xpath = "//select[@class='toppings1']")
    private WebElement pizza1Toppings1;

    @FindBy(xpath = "//select[@class='toppings2']")
    private WebElement pizza1Toppings2;

    @FindBy(id = "pizza1Qty")
    private WebElement pizza1Quantity;

    @FindBy(id = "pizza1Cost")
    private WebElement pizza1Cost;

    @FindBy(id = "ccpayment")
    private WebElement radioCreditCard;

    @FindBy(id = "cashpayment")
    private WebElement radioCash;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "placeOrder")
    private WebElement placeOrderButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    @FindBy(id = "dialog")
    private WebElement dialog;

    @FindBy(xpath = "//div[@id='dialog']/p")
    private WebElement dialogText;

    @FindBy(css = "div.ui-dialog button[title=\"Close\"]")
    private WebElement dialogCloseButton;

    public void choosePizza(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int quantity) {
        if (pizzaType != null) {
            selectFromDropDown(pizza1, pizzaType.getDisplayName());
        }
        if (pizzaTopping1 != null) {
            selectFromDropDown(pizza1Toppings1, pizzaTopping1.getDisplayName());
        }
        if (pizzaTopping2 != null) {
            selectFromDropDown(pizza1Toppings2, pizzaTopping2.getDisplayName());
        }
        pizza1Quantity.clear();
        pizza1Quantity.sendKeys(String.valueOf(quantity));
    }

    //    TODO: find way to get quantity
    public String getPizzaQuantity() {
        return pizza1Quantity.getAttribute("value");
    }

    //    TODO: find way to get cost
    public String getPizzaCost() {
        return pizza1Cost.getAttribute("value");
//        return String.valueOf(js.executeScript("return arguments[0].value;", pizza1Cost));
    }

    public String getName() {
        return nameInput.getAttribute("value");
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    public void setPickupInformation(String name, String email, String phone) {
        if (name != null) {
            nameInput.click();
            nameInput.sendKeys(name);
        }
        if (email != null) {
            emailInput.click();
            emailInput.sendKeys(email);
        }
        if (phone != null) {
            phoneInput.click();
            phoneInput.sendKeys(phone);
        }
    }

    public void selectPayment(PaymentMethods paymentMethod) {
        if (paymentMethod == null) return;
        switch (paymentMethod) {
            case CCPAYMENT:
                radioCreditCard.click();
                break;
            case CASHPAYMENT:
                radioCash.click();
                break;
            default:
                log.info("Invalid payment method");
        }
    }

    public PaymentMethods getPaymentMethod() {
        if (String.valueOf(js.executeScript("return arguments[0].checked;", radioCreditCard)).equals("true")) {
            return PaymentMethods.CCPAYMENT;
        } else if (String.valueOf(js.executeScript("return arguments[0].checked;", radioCash)).equals("true")) {
            return PaymentMethods.CASHPAYMENT;
        } else {
            return PaymentMethods.NONE;
        }
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

    public void resetOrder() {
        resetButton.click();
    }

    public String getDialogMessage() {
        return dialog.getText();
    }

    public void closeDialog() {
        dialogCloseButton.click();
    }

    public Order getOrderInfo() {
        Order order = new Order(selectedDropDownValue(pizza1), selectedDropDownValue(pizza1Toppings1), selectedDropDownValue(pizza1Toppings2),
                Integer.parseInt(getPizzaQuantity()), Double.parseDouble(getPizzaCost()), getName(), getEmail(), getPhone(),getPaymentMethod());
        return order;
    }
}
