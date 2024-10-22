package hva.core;

public class SpringSeasonDeciduousState implements SeasonState{
    
    public void changeSeasonInfo(Tree tree) {    
        tree.setSeasonEffort(1);
        tree.setBiologicalCycle("GERARFOLHAS");
    }

    public SeasonState next() {
        return new SummerSeasonDeciduousState();
    }
}
