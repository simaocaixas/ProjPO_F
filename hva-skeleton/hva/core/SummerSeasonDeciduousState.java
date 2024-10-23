package hva.core;

import hva.core.Season;
import hva.core.SeasonState;

public class SummerSeasonDeciduousState implements TreeState {
    
    public String biologicalState() {
        return "COMFOLHAS";
    }

    public int seasonalEffort() {
        return 2;
    }

    public TreeState nextSeason() {
        return new FallSeasonDeciduousState();
    }
}
