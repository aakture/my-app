package com.mycompany.app.purchase_sums;

/**
 * Created by aakture on 7/16/15.
 */
public class PurchaseSum implements Comparable<PurchaseSum> {
    private final String name;
    private Integer sum = 0;

    public PurchaseSum(String name) {
        this.name = name;
    }

    public void  addTransaction(PurchaseTransaction purchaseTransaction) {
        sum += purchaseTransaction.getAmount();
    }

    public Integer getSum() {
        return sum;
    }

    public String getName() {
        return name;
    }

    public int compareTo(PurchaseSum o) {
        if(getSum() < o.getSum()) {
            return 1;
        }
        if(getSum() > o.getSum()) {
            return -1;
        }
        return getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseSum that = (PurchaseSum) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return !(sum != null ? !sum.equals(that.sum) : that.sum != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append(name).append(" ").append("$").append(sum);
        return sb.toString();
    }
}
