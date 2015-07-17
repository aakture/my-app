package com.mycompany.app.security_indexes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by aakture on 7/16/15.
 */
public class ProcessIndexes {
    public static void main(String[] args) {

        try {
            String indexes = args[0];
            String file = args[1];
            String command = args[2];
            if(!"--indexes".equals(indexes)) {
                printErrorMessage();
            }
            else {
                if ("--indexlist".equals(command)) {
                    doIndexList(file);

                }
                else if ("--duplicates".equals(command)) {
                    doDuplicates(file);

                }
                else {
                    printErrorMessage();
                }
            }

        } catch (Exception ex) {
            printErrorMessage();
        }


    }

    private static void printErrorMessage() {
        System.out.println("usage: --indexes file.txt --[command]");
        System.out.println("where [command] is either duplicates or indexlist");
    }

    private static void doDuplicates(String file) {
        BufferedReader br = null;
        Map<Index, Set<Security>> indexSecuritiesMap = new HashMap<Index, Set<Security>>();

        try {
            br = new BufferedReader(new FileReader(new File(file)));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(":");
                Index index = new Index(tokens[0]);
                Set<Security> securities = parseSecurities(tokens[1]);

                indexSecuritiesMap.put(index, securities);
            }
            for(Index index : indexSecuritiesMap.keySet()) {

                for(Index otherIndex : indexSecuritiesMap.keySet()) {
                    if(index.equals(otherIndex)) {
                        continue;
                    }
                    //System.out.println("compare: " + index.getName() + " to: " + otherIndex.getName());
                    if(compareSecurities(indexSecuritiesMap.get(index), indexSecuritiesMap.get(otherIndex))) {
                        System.out.println(index.getName() + " duplicates " + otherIndex.getName());
                    }

                }
                System.out.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean compareSecurities(Set<Security> first, Set<Security> second) {
        if(first.size() != second.size()) {
            return false;
        }
        boolean areEqualAndFactors = true;
        Iterator<Security> firstIter = first.iterator();
        Iterator<Security> secondIter = second.iterator();
        float factor = 0.0f;
        if(firstIter.hasNext()) {

            Security firstSec = firstIter.next();
            Security secondSec = secondIter.next();

            if(!firstSec.getName().equals(secondSec.getName())) {
                return false;
            }
            factor = firstSec.getWeight() / secondSec.getWeight();
         }
        while(firstIter.hasNext()) {
            Security firstSec = firstIter.next();
            Security secondSec = secondIter.next();
            if(!firstSec.getName().equals(secondSec.getName())
                    || factor != firstSec.getWeight() / secondSec.getWeight()) {
                areEqualAndFactors = false;
            }
        }
        return areEqualAndFactors;


    }

    private static void doIndexList(String file) {
        BufferedReader br = null;
        Map<Index, Set<Security>> indexSecuritiesMap = new HashMap<Index, Set<Security>>();
        Set<Security> securitySet = new TreeSet<Security>();
        try {
            br = new BufferedReader(new FileReader(new File(file)));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(":");
                Index index = new Index(tokens[0]);
                Set<Security> securities = parseSecurities(tokens[1]);
                securitySet.addAll(securities);
                indexSecuritiesMap.put(index, securities);
            }
            for(Security security : securitySet) {
                System.out.print(security.getName() + ":");
                for(Index index : indexSecuritiesMap.keySet()) {
                    Set<Security> securities = indexSecuritiesMap.get(index);
                    if(securities.contains(security)) {
                        System.out.print(index.getName() + " ");
                    }

                }
                System.out.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Set<Security> parseSecurities(String securitiesString) {
        Set<Security> securities = new TreeSet<Security>();
        StringTokenizer stringTokenizer = new StringTokenizer(securitiesString, ", ");
        while(stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            String name = token.substring(0, token.indexOf("("));
            String amountString = token.substring(token.indexOf("(") + 1, token.indexOf(")"));
            Float amount = Float.valueOf(amountString);
            securities.add(new Security(name, amount));
        }
       // System.out.println("securities: " + securities);
        return securities;
    }
}
