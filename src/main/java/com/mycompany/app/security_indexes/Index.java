package com.mycompany.app.security_indexes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by aakture on 7/16/15.
 */
public class Index {
    private final String name;
    Set<Security> securities = new HashSet<Security>();
    public Index(String name) {
        this.name = name;
    }
    public void addSecurity(Security security) {
        securities.add(security);
    }

    public String getName() {
        return name;
    }

    public Set<Security> getSecurities() {
        return securities;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Index{");
        sb.append("name='").append(name).append('\'');
        sb.append(", securities=").append(securities);
        sb.append('}');
        return sb.toString();
    }
}
