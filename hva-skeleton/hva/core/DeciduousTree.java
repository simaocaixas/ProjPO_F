package hva.core;
import java.util.*;

public class DeciduousTree extends Tree {
    
    private SeasonState _state;

    private List<SeasonState> _seasonStates = new ArrayList<>(Arrays.asList(
        new SpringSeasonDeciduousState(), new SummerSeasonDeciduousState(),
        new FallSeasonDeciduousState(), new WinterSeasonDeciduousState()));

    protected DeciduousTree(Habitat habitat, String idTree, String treeName, int diff, Season season) {
        super(habitat, idTree, treeName, diff, season);
        _state = _seasonStates.get(season.getSeasonNumber());   
        habitat.addTree(this);         
    }

    public void advanceSeason(){
        _state = _state.next();
    }    

    protected String treeToString() {
        return super.treeToString() + "CADUCA" + "|" + getBiologicalCycle();
    }

}
