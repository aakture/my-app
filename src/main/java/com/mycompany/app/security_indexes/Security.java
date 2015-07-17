package com.mycompany.app.security_indexes;

/**
 * Created by aakture on 7/16/15.
 */
public class Security implements Comparable<Security> {
    private String name;
    private Float weight;

    public Security(String name, Float weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Security{");
        sb.append("name='").append(name).append('\'');
        sb.append(", weight=").append(weight);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public Float getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Security security = (Security) o;

        return name.equals(security.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public int compareTo(Security o) {
        return name.compareTo(o.getName());
    }
}
