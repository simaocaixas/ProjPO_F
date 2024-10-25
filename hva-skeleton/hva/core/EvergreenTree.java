package hva.core;

/**
 * Represents an evergreen tree, which is a type of tree that retains its leaves throughout the year.
 * An EvergreenTree can either be created in a specific habitat or outside of it. The tree's state
 * is automatically set to a spring season state, and its biological state is updated.
 */
public class EvergreenTree extends Tree {

    /**
     * Constructs an EvergreenTree with the specified habitat, tree ID, name, age, difficulty level, and season.
     * The tree's state is initialized to the spring season state and is added to the habitat.
     * 
     * @param habitat  the habitat where the tree is located
     * @param idTree   the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age      the age of the tree
     * @param diff     the difficulty level associated with the tree
     * @param season   the current season for the tree
     */
    EvergreenTree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(habitat, idTree, treeName, age, diff, season);
        setState(SpringSeasonEvergreenState.getInstance());
        updateTreeState();
        habitat.addTree(this);  
    }

    /**
     * Constructs an EvergreenTree with the specified tree ID, name, age, difficulty level, and season.
     * The tree's state is initialized to the spring season state but is not tied to a specific habitat.
     * 
     * @param idTree   the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age      the age of the tree
     * @param diff     the difficulty level associated with the tree
     * @param season   the current season for the tree
     */
    EvergreenTree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName, age, diff, season);
        setState(SpringSeasonEvergreenState.getInstance());
        updateTreeState();
    }

    /**
     * Returns a string representation of the EvergreenTree object.
     * The format is: super.toString()|PERENE|biological state.
     * 
     * @return a string representing the evergreen tree
     */
    public String toString() {
        return super.toString() + "PERENE" + "|" + getState().biologicalState();
    }
}
