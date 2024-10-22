package hva.core;

public class SummerSeasonDeciduousState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(2);
        tree.setBiologicalCycle("COMFOLHAS");
    }

    public SeasonState next() {
        return new FallSeasonDeciduousState();
    }
}
