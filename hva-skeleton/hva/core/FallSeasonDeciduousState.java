package hva.core;

/**
 * Singleton class representing the state of a deciduous tree during the fall season.
 * This class implements the TreeState interface and provides specific behavior for the fall season.
 */
public class FallSeasonDeciduousState implements TreeState {

    // Singleton instance of FallSeasonDeciduousState
    private static final FallSeasonDeciduousState INSTANCE = new FallSeasonDeciduousState();

    // Private constructor to prevent instantiation
    private FallSeasonDeciduousState() {}

    /**
     * Returns the singleton instance of FallSeasonDeciduousState.
     *
     * @return the singleton instance of FallSeasonDeciduousState
     */
    public static FallSeasonDeciduousState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the fall season.
     *
     * @return a string representing the biological state of the tree ("LARGARFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "LARGARFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the fall season.
     *
     * @return an integer representing the seasonal effort (5)
     */
    @Override
    public int seasonalEffort() {
        return 5;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of WinterSeasonDeciduousState
     */
    @Override
    public TreeState nextSeason() {
        return WinterSeasonDeciduousState.getInstance(); 
    }
}