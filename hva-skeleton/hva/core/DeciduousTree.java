package hva.core;

public class DeciduousTree extends Tree {
    
    protected DeciduousTree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(habitat, idTree, treeName, age, diff, season);
        setState(new SpringSeasonDeciduousState());
        updateTreeState();
        habitat.addTree(this);         
    }

    protected DeciduousTree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName, age, diff, season);
        setState(new SpringSeasonDeciduousState());
        updateTreeState();         
    }

    public String toString() {
        return super.toString() + "CADUCA" + "|" + getState().biologicalState();
    }

}
