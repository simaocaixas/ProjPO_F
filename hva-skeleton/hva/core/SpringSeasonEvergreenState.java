package hva.core;

/**
 * Singleton class representing the state of an evergreen tree during the spring season.
 * This class implements the TreeState interface and provides specific behavior for the spring season.
 */
public class SpringSeasonEvergreenState implements TreeState {

    // Singleton instance of SpringSeasonEvergreenState
    private static final SpringSeasonEvergreenState INSTANCE = new SpringSeasonEvergreenState();

    // Private constructor to prevent instantiation
    private SpringSeasonEvergreenState() {}

    /**
     * Returns the singleton instance of SpringSeasonEvergreenState.
     *
     * @return the singleton instance of SpringSeasonEvergreenState
     */
    public static SpringSeasonEvergreenState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the spring season.
     *
     * @return a string representing the biological state of the tree ("GERARFOLHAS")
     */
    public String biologicalState() {
        return "GERARFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the spring season.
     *
     * @return an integer representing the seasonal effort (1)
     */
    public int seasonalEffort() {
        return 1;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of SummerSeasonEvergreenState
     */
    public TreeState nextSeason() {
        return SummerSeasonEvergreenState.getInstance(); 
    }
}