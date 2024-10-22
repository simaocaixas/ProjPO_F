package hva.core;

public class SummerSeasonEvergreenState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(1);
        tree.setBiologicalCycle("COMFOLHAS");
    }

    public SeasonState next() {
        return new FallSeasonEvergreenState();
    }
}
