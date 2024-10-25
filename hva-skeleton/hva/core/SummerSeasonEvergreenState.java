package hva.core;

/**
 * Singleton class representing the state of an evergreen tree during the summer season.
 * This class implements the TreeState interface and provides specific behavior for the summer season.
 */
public class SummerSeasonEvergreenState implements TreeState {

    // Singleton instance of SummerSeasonEvergreenState
    private static final SummerSeasonEvergreenState INSTANCE = new SummerSeasonEvergreenState();

    // Private constructor to prevent instantiation
    private SummerSeasonEvergreenState() {}

    /**
     * Returns the singleton instance of SummerSeasonEvergreenState.
     *
     * @return the singleton instance of SummerSeasonEvergreenState
     */
    public static SummerSeasonEvergreenState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the summer season.
     *
     * @return a string representing the biological state of the tree ("COMFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "COMFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the summer season.
     *
     * @return an integer representing the seasonal effort (1)
     */
    @Override
    public int seasonalEffort() {
        return 1;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of FallSeasonEvergreenState
     */
    @Override
    public TreeState nextSeason() {
        return FallSeasonEvergreenState.getInstance();
    }
}