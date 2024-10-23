package hva.core;

public class EvergreenTree extends Tree {

    public EvergreenTree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(habitat, idTree, treeName, age, diff, season);
        setState(new SpringSeasonEvergreenState());
        updateTreeState();
        habitat.addTree(this);  
    }
    
    public EvergreenTree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName, age, diff, season);
        setState(new SpringSeasonEvergreenState());
        updateTreeState();
    }
    
    public String toString() {
        return super.toString() + "PERENE" + "|" + getState().biologicalState();
    }
}
