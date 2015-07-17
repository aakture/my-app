package com.mycompany.app.purchase_sums;


/**
 * Created by aakture on 7/16/15.
 */
public class PurchaseTransaction {
    private final String name;
    private final Integer amount;

    public PurchaseTransaction(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PurchaseTransaction{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
