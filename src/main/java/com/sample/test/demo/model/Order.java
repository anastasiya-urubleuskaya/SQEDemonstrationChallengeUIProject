package com.sample.test.demo.model;

import com.sample.test.demo.constants.PaymentMethods;

public class Order {

    private String pizzaType;
    private String pizzaTopping1;
    private String pizzaTopping2;
    private int quantity;
    private double cost;
    private String name;
    private String email;
    private String phone;
    private PaymentMethods paymentMethod;

    public Order(String pizzaType, String pizzaTopping1, String pizzaTopping2, int quantity, double cost, String name, String email, String phone, PaymentMethods paymentMethod) {
        this.pizzaType = pizzaType;
        this.pizzaTopping1 = pizzaTopping1;
        this.pizzaTopping2 = pizzaTopping2;
        this.quantity = quantity;
        this.cost = cost;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Order order = (Order) o;
        return pizzaType.equals(order.pizzaType) &&
                pizzaTopping1.equals(order.pizzaTopping1) &&
                pizzaTopping2.equals(order.pizzaTopping2) &&
                quantity == order.quantity &&
                cost == order.cost &&
                name.equals(order.name) &&
                email.equals(order.email) &&
                phone.equals(order.phone) &&
                paymentMethod == order.paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pizzaType='" + pizzaType + '\'' +
                ", pizzaTopping1='" + pizzaTopping1 + '\'' +
                ", pizzaTopping2='" + pizzaTopping2 + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
