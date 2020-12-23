package com.sample.test.demo.tests;

import com.sample.test.demo.constants.ErrorMessages;
import com.sample.test.demo.constants.PaymentMethods;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pageobject.SamplePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlaceOrderNegativeTest extends BaseTest {

//as there is no any AC defined for this functionality, I assumed that in case of any validation error or missing required field, dialog window with message should appear
//of course validation message should appear directly under input fields and in that case approach with dataProvider wouldn't feet, as we would have to check different fields(locators)
    @Test(dataProvider = "orderData")
    public void placeOrderInvalidDataTest(PizzaTypes pizzaType, PizzaToppings pizzaTopping1, PizzaToppings pizzaTopping2, int pizzaQuantity, String name, String email, String phone, PaymentMethods paymentMethod, String expectedErrorMsg) {
        samplePage = new SamplePage(driver);
        samplePage.choosePizza(pizzaType, pizzaTopping1, pizzaTopping2, pizzaQuantity);
        samplePage.setPickupInformation(name, email, phone);
        samplePage.selectPayment(paymentMethod);
        samplePage.placeOrder();
        Assert.assertEquals(samplePage.getDialogMessage(), expectedErrorMsg);
    }

    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
//                no pizza selected
                {null, null, null, 2, "Nastya", "test@gmail.com", "+375298887727", PaymentMethods.CCPAYMENT, ErrorMessages.NO_PIZZA_SELECTED.getMessage()},
//                name and phone fields left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, null, null, null, PaymentMethods.CASHPAYMENT, ErrorMessages.NAME_AND_PHONE_FIELDS_REQUIRED.getMessage()},
//                name field left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, null, null, "+375298887727", PaymentMethods.CCPAYMENT, ErrorMessages.NAME_FIELD_REQUIRED.getMessage()},
//                phone field left blank
                {PizzaTypes.SMALL_ONETOPPINGS, PizzaToppings.EXTRACHEESE, null, 3, "Jack", null, null, PaymentMethods.CASHPAYMENT, ErrorMessages.PHONE_FIELD_REQUIRED.getMessage()},
//                negative number as quantity entered
                {PizzaTypes.MEDIUM_TWOTOPPINGS, PizzaToppings.ITALIANHAM, PizzaToppings.MUSHROOMS, -2, "Olga", "test@gmail.com", "+375298887727", PaymentMethods.CCPAYMENT, ErrorMessages.INVALID_QUANTITY.getMessage()},
//                no payment selected
                {PizzaTypes.LARGE_NOTOPPINGS, null, null, 1, "Roma", "test@gmail.com", "+375298887727", null, ErrorMessages.NO_PAYMENT_SELECTED.getMessage()},
//                no toppings selected for pizza with toppings
                {PizzaTypes.LARGE_TWOTOPPINGS, null, null, 1, "Ivan", null, "+375298887727", PaymentMethods.CASHPAYMENT, ErrorMessages.MISSING_TOPPINGS_SELECTION.getMessage()}
//                there also must be tests on pickup info validation(valid chars for name, needed format for email and phone)
        };
    }

    @AfterMethod
    public void setDown(){
        samplePage.closeDialog();
    }
}
