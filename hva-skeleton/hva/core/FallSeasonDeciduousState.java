package hva.core;

public class FallSeasonDeciduousState implements TreeState {
    
    public String biologicalState() {
        return "LARGARFOLHAS";
    }

    public int seasonalEffort() {
        return 5;
    }

    public TreeState nextSeason() {
        return new WinterSeasonDeciduousState();
    }
}
