package hva.core;

public class FallSeasonEvergreenState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(1);
        tree.setBiologicalCycle("COMFOLHAS");
    }

    public SeasonState next() {
        return new WinterSeasonEvergreenState();
    }

}
