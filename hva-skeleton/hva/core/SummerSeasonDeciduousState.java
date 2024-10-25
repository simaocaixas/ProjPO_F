package hva.core;
/**
 * Singleton class representing the state of a deciduous tree during the summer season.
 * This class implements the TreeState interface and provides specific behavior for the summer season.
 */
public class SummerSeasonDeciduousState implements TreeState {

    // Singleton instance of SummerSeasonDeciduousState
    private static final SummerSeasonDeciduousState INSTANCE = new SummerSeasonDeciduousState();

    // Private constructor to prevent instantiation
    private SummerSeasonDeciduousState() {}

    /**
     * Returns the singleton instance of SummerSeasonDeciduousState.
     *
     * @return the singleton instance of SummerSeasonDeciduousState
     */
    public static SummerSeasonDeciduousState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the summer season.
     *
     * @return a string representing the biological state of the tree ("COMFOLHAS")
     */
    public String biologicalState() {
        return "COMFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the summer season.
     *
     * @return an integer representing the seasonal effort (2)
     */
    public int seasonalEffort() {
        return 2;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of FallSeasonDeciduousState
     */
    public TreeState nextSeason() {
        return FallSeasonDeciduousState.getInstance();
    }
}