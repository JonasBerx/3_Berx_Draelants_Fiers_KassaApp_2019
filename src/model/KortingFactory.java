package model;

import java.util.ArrayList;

public class KortingFactory {
    ArrayList<KortingStrategy> kortingen = new ArrayList<>();


    void create(ArrayList<String> types) {
        for (String t : types) {
            System.out.println(t);
            switch (t) {
                case "GROUP":
                    kortingen.add(new GroupDiscount());
                    break;
                case "THRESHOLD":
                    kortingen.add(new ThresholdDiscount());
                    break;
                case "EXPENSIVE":
                    kortingen.add(new ExpensiveDiscount());
                    break;
            }
        }

    }

    public ArrayList<KortingStrategy> getKortingen() {
        return kortingen;
    }

}
