package model;

public class CardItems {
    private  String currency;
    private double currencyValue;

    public CardItems(String currency, double currencyValue) {
        this.currency = currency;
        this.currencyValue = currencyValue;
    }

    public String getCurrency() {
        return currency;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }
}
