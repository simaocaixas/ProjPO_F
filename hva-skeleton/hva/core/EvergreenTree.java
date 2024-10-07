package hva.core;

import java.util.ArrayList;
import java.util.List;

public class EvergreenTree extends Tree {

    private List<String> _biologicalCycle = new ArrayList<>(List.of("LARGARFOLHAS", "GERARFOLHAS", "COMFOLHAS", "COMFOLHAS"));

    public EvergreenTree(Habitat habitat, String idTree, String treeName, int diff) {
        super(habitat, idTree, treeName, diff);            
    }
}
