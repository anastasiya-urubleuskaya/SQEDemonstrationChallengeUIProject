package com.sample.test.demo.tests;

import com.sample.test.demo.constants.PaymentMethods;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.model.Order;
import com.sample.test.demo.pageobject.SamplePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResetOrderTest extends BaseTest {

    @Test(dataProvider = "orderData")
    public void placeOrderTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentMethods paymentMethod) {
        samplePage = new SamplePage(driver);
        samplePage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        samplePage.setPickupInformation(name, email, phone);
        samplePage.selectPayment(paymentMethod);
        samplePage.resetOrder();
        Order resetOrder = new Order(PizzaTypes.CHOOSE_PIZZA.getDisplayName(), PizzaToppings.TOPPINGS1.getDisplayName(), PizzaToppings.TOPPINGS2.getDisplayName(),
                0, 0.0, "", "", "", PaymentMethods.NONE);
        log.info(resetOrder.toString());
        Assert.assertEquals(samplePage.getOrderInfo(), resetOrder);
    }

    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
                {PizzaTypes.SMALL_NOTOPPINGS, null, null, 2, "Nastya", "test@gmail.com", "+375298887727", PaymentMethods.CCPAYMENT},
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, null, null, "+375298887727", PaymentMethods.CASHPAYMENT},
                {PizzaTypes.MEDIUM_TWOTOPPINGS, null, PizzaToppings.MUSHROOMS, 2, "Olga", "test@gmail.com", null, null}
        };
    }
}
