package kz.education.kstu.personalworksix.model;

public class ListPrice {
    private float amount;
    private String currencyCode;

    public ListPrice() {
    }

    public ListPrice(float amount, String currency) {
        this.amount = amount;
        this.currencyCode = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currencyCode;
    }

    public void setCurrency(String currency) {
        this.currencyCode = currency;
    }
}
