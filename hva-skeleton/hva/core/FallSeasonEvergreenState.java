package hva.core;

import hva.core.Season;

public class FallSeasonEvergreenState implements TreeState {
    
    public String biologicalState() {
        return "COMFOLHAS";
    }

    public int seasonalEffort() {
        return 1;
    }

    public TreeState nextSeason() {
        return new WinterSeasonEvergreenState();
    }
}
