package hva.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvergreenTree extends Tree {

    private Map<String, String> _biologicalCycle = new HashMap<>(Map.of(
        "Primavera", "LARGARFOLHAS",
        "Verao", "GERARFOLHAS",
        "Outono", "COMFOLHAS",
        "Inverno", "COMFOLHAS"
    ));

    public EvergreenTree(Habitat habitat, String idTree, String treeName, int diff, Season season) {
        super(habitat, idTree, treeName, diff, season);            
    }

    protected String getBiologicalCycle() {
        return _biologicalCycle.get(seasonName());
    }

}
