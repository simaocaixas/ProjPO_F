package hva.core;

public class FallSeasonDeciduousState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(5);
        tree.setBiologicalCycle("LARGARFOLHAS");
    }

    public SeasonState next() {
        return new WinterSeasonDeciduousState();
    }
}
