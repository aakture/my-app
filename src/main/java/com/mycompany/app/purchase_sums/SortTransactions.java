package com.mycompany.app.purchase_sums;

import java.io.*;
import java.util.*;


/**
 * Created by aakture on 7/16/15.
 */
public class SortTransactions {
    public static void main(String[] args) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("/Users/aakture/projects/my-app/src/main/java/com/mycompany/app/purchase_sums/input.txt")));

            String line = null;
            List<PurchaseTransaction> purchaseTransactions = new ArrayList<PurchaseTransaction>();
            while ((line = br.readLine()) != null) {

                String[] tokens = line.split(" ");
                PurchaseTransaction purchaseTransaction = new PurchaseTransaction(tokens[0], parseInt(tokens[1]));
                System.out.println(purchaseTransaction);
                purchaseTransactions.add(purchaseTransaction);
            }

            Map<String, PurchaseSum> purchaseSumsMap = processTransactions(purchaseTransactions);

            TreeSet<PurchaseSum> sortedPurchaseSums = new TreeSet<PurchaseSum>();
            sortedPurchaseSums.addAll(purchaseSumsMap.values());

            System.out.println("sorted: " + sortedPurchaseSums);
            for(PurchaseSum purchaseSum : sortedPurchaseSums) {
                System.out.println(purchaseSum);
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(br != null) {
                    br.close();
                }
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Map<String, PurchaseSum> processTransactions(List<PurchaseTransaction> purchaseTransactions) {
        Map<String, PurchaseSum> purchaseSumsMap = new HashMap<String, PurchaseSum>();
        for(PurchaseTransaction purchaseTransaction : purchaseTransactions) {
            PurchaseSum purchaseSum =  purchaseSumsMap.get(purchaseTransaction.getName());
            if(purchaseSum == null) {
                PurchaseSum purchSum = new PurchaseSum(purchaseTransaction.getName());
                purchSum.addTransaction(purchaseTransaction);
                purchaseSumsMap.put(purchaseTransaction.getName(), purchSum);
            }
            else {
                purchaseSum.addTransaction(purchaseTransaction);

            }
        }
        System.out.println("purchaseSumMap has: "  +purchaseSumsMap);
        return purchaseSumsMap;
    }

    private static Integer parseInt(String token) {
        return new Integer(token.substring(token.indexOf("$") + 1, token.length()));
    }
}
