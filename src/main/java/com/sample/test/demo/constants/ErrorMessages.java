package com.sample.test.demo.constants;


public enum ErrorMessages {
    NO_PIZZA_SELECTED("Please, select pizza"),
    NAME_AND_PHONE_FIELDS_REQUIRED("Missing name\nMissing phone number"),
    NAME_FIELD_REQUIRED("Missing name"),
    PHONE_FIELD_REQUIRED("Missing phone number"),
    INVALID_QUANTITY("Invalid value for quantity"),
    NO_PAYMENT_SELECTED("Please, select desired way of payment"),
    MISSING_TOPPINGS_SELECTION("Toppings for pizza not selected");

    private String message;

    private ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
