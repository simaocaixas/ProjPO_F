package hva.core;
import java.util.*;

public class EvergreenTree extends Tree {
    
    private SeasonState _state;

    private List<SeasonState> _seasonStates = new ArrayList<>(Arrays.asList(
        new SpringSeasonEvergreenState(), new SummerSeasonEvergreenState(),
        new FallSeasonEvergreenState(), new WinterSeasonEvergreenState()));

    public EvergreenTree(Habitat habitat, String idTree, String treeName, int diff, Season season) {
        super(habitat, idTree, treeName, diff, season);
        _state = _seasonStates.get(season.getSeasonNumber());       
        habitat.addTree(this);  
    }

    public void advanceSeason(){
        _state = _state.next();
    }
        
    public String treeToString() {
        return super.treeToString() + "PERENE" + "|" + getBiologicalCycle();
    }
}
