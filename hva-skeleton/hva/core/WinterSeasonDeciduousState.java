package hva.core;

public class WinterSeasonDeciduousState implements SeasonState{
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(0);
        tree.setBiologicalCycle("SEMFOLHAS");
    }

    public SeasonState next() {
        return new SpringSeasonDeciduousState();
    }
}
