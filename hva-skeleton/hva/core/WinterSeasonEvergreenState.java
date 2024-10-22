package hva.core;

public class WinterSeasonEvergreenState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(2);
        tree.setBiologicalCycle("LARGARFOLHAS");
    }

    public SeasonState next() {
        return new SpringSeasonEvergreenState();
    }
}
