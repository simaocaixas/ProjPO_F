package hva.core;

/**
 * Singleton class representing the state of a deciduous tree during the winter season.
 * This class implements the TreeState interface and provides specific behavior for the winter season.
 */
public class WinterSeasonDeciduousState implements TreeState {

    // Singleton instance of WinterSeasonDeciduousState
    private static final WinterSeasonDeciduousState INSTANCE = new WinterSeasonDeciduousState();

    // Private constructor to prevent instantiation
    private WinterSeasonDeciduousState() {}

    /**
     * Returns the singleton instance of WinterSeasonDeciduousState.
     *
     * @return the singleton instance of WinterSeasonDeciduousState
     */
    public static WinterSeasonDeciduousState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the winter season.
     *
     * @return a string representing the biological state of the tree ("SEMFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "SEMFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the winter season.
     *
     * @return an integer representing the seasonal effort (0)
     */
    @Override
    public int seasonalEffort() {
        return 0;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of SpringSeasonDeciduousState
     */
    @Override
    public TreeState nextSeason() {
        return SpringSeasonDeciduousState.getInstance(); 
    }
}