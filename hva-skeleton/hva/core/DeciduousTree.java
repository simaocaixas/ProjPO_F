package hva.core;


/**
 * Represents a deciduous tree, which is a type of tree that sheds its leaves 
 * annually, particularly during autumn. The tree's state is updated according 
 * to the seasons, starting with the spring state by default.
 */
public class DeciduousTree extends Tree {
    
    /**
     * Constructs a DeciduousTree with an associated habitat.
     * 
     * @param habitat the habitat in which the tree is located
     * @param idTree the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age the age of the tree
     * @param diff the difficulty or growth factor for the tree
     * @param season the current season of the tree
     */
    protected DeciduousTree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(habitat, idTree, treeName, age, diff, season);
        setState(SpringSeasonDeciduousState.getInstance());
        updateTreeState();
        habitat.addTree(this);         
    }

    /**
     * Constructs a DeciduousTree without an associated habitat.
     * 
     * @param idTree the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age the age of the tree
     * @param diff the difficulty or growth factor for the tree
     * @param season the current season of the tree
     */
    protected DeciduousTree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName, age, diff, season);
        setState(SpringSeasonDeciduousState.getInstance());
        updateTreeState();         
    }

    /**
     * Returns a string representation of the EvergreenTree object.
     * The format is: super.toString()|CADUCA|biological state.
     * 
     * @return a string representation of the tree
     */
    public String toString() {
        return super.toString() + "CADUCA" + "|" + getState().biologicalState();
    }

}
