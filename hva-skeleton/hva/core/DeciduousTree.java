package hva.core;

import java.util.*;
public class DeciduousTree extends Tree {
    
    private Map<String, String> _biologicalCycle = new HashMap<>(Map.of(
        "Primavera", "SEMFOLHAS",
        "Verao", "GERARFOLHAS",
        "Outono", "COMFOLHAS",
        "Inverno", "LARGARFOLHAS"
    ));

    protected DeciduousTree(Habitat habitat, String idTree, String treeName, int diff, Season season) {
        super(habitat, idTree, treeName, diff, season);   
        habitat.addTree(this);         
    }

    @Override
    protected String getBiologicalCycle() {
        return _biologicalCycle.get(seasonName());
    }

    protected String treeToString() {
        return super.treeToString() + getBiologicalCycle();
    }

}
