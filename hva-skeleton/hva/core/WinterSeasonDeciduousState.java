package hva.core;

public class WinterSeasonDeciduousState implements TreeState {

    public String biologicalState() {
        return "SEMFOLHAS";
    }

    public int seasonalEffort() {
        return 0;
    }

    public TreeState nextSeason() {
        return new SpringSeasonDeciduousState();
    }
}