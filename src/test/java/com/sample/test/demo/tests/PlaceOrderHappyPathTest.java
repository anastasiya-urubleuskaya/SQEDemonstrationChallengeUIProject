package com.sample.test.demo.tests;

import com.sample.test.demo.constants.PaymentMethods;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pageobject.SamplePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlaceOrderHappyPathTest extends BaseTest {

    public static final String SUCCESS_MESSAGE = "Thank you for your order! TOTAL: %s %s";

    @Test(dataProvider = "orderData")
    public void placeOrderTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentMethods paymentMethod) {
        double expectedCost = pizzaQuantity * pizzaType.getCost();
        samplePage = new SamplePage(driver);
        samplePage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        samplePage.setPickupInformation(name, email, phone);
        samplePage.selectPayment(paymentMethod);
        samplePage.placeOrder();
        Assert.assertEquals(samplePage.getDialogMessage(), String.format(SUCCESS_MESSAGE, expectedCost, pizzaType.getDisplayName()));
    }

    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
                {PizzaTypes.SMALL_NOTOPPINGS, null, null, 2, "Nastya", "test@gmail.com", "+375298887727", PaymentMethods.CCPAYMENT},
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, "Jack", null, "+375298887727", PaymentMethods.CASHPAYMENT},
                {PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.ITALIANHAM, PizzaToppings.MUSHROOMS, 2, "Olga", "test@gmail.com", "+375298887727", PaymentMethods.CCPAYMENT},
                {PizzaTypes.LARGE_NOTOPPINGS, null, null, 1, "Roma", "test@gmail.com", "+375298887727", PaymentMethods.CASHPAYMENT},
                {PizzaTypes.LARGE_TWOTOPPINGS, PizzaToppings.MANGOS, PizzaToppings.PARMASAN, 1, "Ivan", null, "+375298887727", PaymentMethods.CCPAYMENT}
        };
    }

    @AfterMethod
    public void setDown(){
        samplePage.closeDialog();
    }
}
