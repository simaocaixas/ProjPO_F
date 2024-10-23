package hva.core;

public class SummerSeasonEvergreenState implements TreeState {
    
    public String biologicalState() {
        return "COMFOLHAS";
    }

    public int seasonalEffort() {
        return 1;
    }

    public TreeState nextSeason() {
        return new FallSeasonEvergreenState();
    }
}
