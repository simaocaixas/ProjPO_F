package hva.core;
/**
 * Abstract class representing a tree, which extends the Identifier class.
 * This class holds information about the tree's age, difficulty, season,
 * habitat, and its current state. It also provides methods to manage the tree's
 * seasonal changes and calculate cleaning efforts.
 */
public abstract class Tree extends Identifier {

    private Season _season;
    private final Season _birthSeason; 
    private int _age; 
    private int _diff;
    private TreeState _state;
    private Habitat _habitat;
    
    /**
     * Constructs a Tree object with the specified habitat, ID, name, age,
     * difficulty, and season.
     *
     * @param habitat  the habitat where the tree is located
     * @param idTree   the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age      the age of the tree
     * @param diff     the difficulty level associated with the tree
     * @param season   the current season of the tree
     */
    Tree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName);
        _age = age;
        _habitat = habitat;
        _diff = diff;
        _season = season;
        _birthSeason = season;
    }

    /**
     * Constructs a Tree object with the specified ID, name, age, difficulty,
     * and season. This constructor is intended for trees without a specified habitat.
     *
     * @param idTree   the unique identifier of the tree
     * @param treeName the name of the tree
     * @param age      the age of the tree
     * @param diff     the difficulty level associated with the tree
     * @param season   the current season of the tree
     */
    Tree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName);
        _age = age;
        _diff = diff;
        _season = season;
        _birthSeason = season;
    }

    /**
     * Calculates the cleaning effort required for the tree based on its
     * difficulty, seasonal effort, and age.
     *
     * @return the cleaning effort as a double value
     */
    double cleaningEffort() {
        return _diff * _state.seasonalEffort() * Math.log(_age + 1);
    }

    /**
     * Updates the age of the tree if the current season matches the birth season.
     */
    void updateTreeAge() {
        if (_birthSeason.equals(_season)) {
            _age++;
        }
    }

    /**
     * Returns the age of the tree.
     *
     * @return the age of the tree
     */
    int age() {
        return _age;
    }

    /**
     * Returns the difficulty level of the tree.
     *
     * @return the difficulty level
     */
    int diff() {
        return _diff;
    }

    /**
     * Sets the age of the tree to the specified value.
     *
     * @param age the new age to set for the tree
     */
    void setAge(int age) {
        _age = age;
    }

    /**
     * Returns the current season of the tree.
     *
     * @return the current season
     */
    Season season() {
        return _season;
    }

    /**
     * Returns the name of the current season as a string.
     *
     * @return the name of the current season
     */
    String seasonName() {
        return _season.name();
    }

    /**
     * Returns the current state of the tree.
     *
     * @return the current TreeState object
     */
    TreeState getState() {
        return _state;
    }

    /**
     * Sets the state of the tree to the specified TreeState object.
     *
     * @param state the new state to set for the tree
     */
    void setState(TreeState state) {
        _state = state;
    }

    /**
     * Advances the tree to the next season and updates its state.
     */
    void advanceSeason() {
        _season = _season.nextSeason();
        _state = _state.nextSeason();
        updateTreeAge();
    }

    /**
     * Updates the state of the tree according to the current season.
     */
    void updateTreeState() {
        int i = 0;
        int seasonInteger = season().getSeasonNumber();

        while (i++ < seasonInteger) {
            _state.nextSeason();
        }
    }

    /**
     * Returns a string representation of the Tree object.
     * The format is: ÁRVORE|super.toString()|age|difficulty|.
     *
     * @return a string representing the tree
     */
    public String toString() {
        return "ÁRVORE" + "|" + super.toString() + "|" + age() + "|" + diff() + "|";
    }
}
