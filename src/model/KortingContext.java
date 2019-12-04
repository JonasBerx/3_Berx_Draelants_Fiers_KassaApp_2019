package model;

import java.util.ArrayList;

public class KortingContext {
    ArrayList<KortingStrategy> kortingen;
    private KortingFactory factory = new KortingFactory();

    public KortingContext(ArrayList<String> type) {
        factory.create(type);
        kortingen = factory.getKortingen();
    }

    public ArrayList<KortingStrategy> getKortingen() {
        return kortingen;
    }




}
