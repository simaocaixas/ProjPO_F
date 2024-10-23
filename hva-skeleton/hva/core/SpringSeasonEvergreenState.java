package hva.core;

public class SpringSeasonEvergreenState implements TreeState { 
    
    public String biologicalState() {
        return "GERARFOLHAS";
    }

    public int seasonalEffort() {
        return 1;
    }

    public TreeState nextSeason() {
        return new SummerSeasonEvergreenState();
    }
}
