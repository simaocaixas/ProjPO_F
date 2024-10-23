package hva.core;

public class WinterSeasonEvergreenState implements TreeState{
    
    public String biologicalState() {
        return "LARGARFOLHAS";
    }

    public int seasonalEffort() {
        return 2;
    }

    public TreeState nextSeason() {
        return new SpringSeasonEvergreenState();
    }
}