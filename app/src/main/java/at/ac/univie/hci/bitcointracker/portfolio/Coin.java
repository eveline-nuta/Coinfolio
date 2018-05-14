package at.ac.univie.hci.bitcointracker.portfolio;

public class Coin {

    private String name;
    private String amount;
    private String price;
    private String currency;


    public Coin() {
    }

    public Coin(String name, String currency, String amount) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
