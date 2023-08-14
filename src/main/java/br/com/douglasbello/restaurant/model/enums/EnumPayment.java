package br.com.douglasbello.restaurant.model.enums;

public enum EnumPayment {
    MASTERCARD("MASTERCARD"),
    VISA("VISA"),
    CASH("CASH");

    public String paymentType;

    EnumPayment(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
