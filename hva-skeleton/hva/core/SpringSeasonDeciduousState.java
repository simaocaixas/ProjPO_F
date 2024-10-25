package hva.core;

/**
 * Singleton class representing the state of a deciduous tree during the spring season.
 * This class implements the TreeState interface and provides specific behavior for the spring season.
 */
public class SpringSeasonDeciduousState implements TreeState {

    // Singleton instance of SpringSeasonDeciduousState
    private static final SpringSeasonDeciduousState INSTANCE = new SpringSeasonDeciduousState();

    // Private constructor to prevent instantiation
    private SpringSeasonDeciduousState() {}

    /**
     * Returns the singleton instance of SpringSeasonDeciduousState.
     *
     * @return the singleton instance of SpringSeasonDeciduousState
     */
    public static SpringSeasonDeciduousState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the spring season.
     *
     * @return a string representing the biological state of the tree ("GERARFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "GERARFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the spring season.
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
     * @return the singleton instance of SummerSeasonDeciduousState
     */
    @Override
    public TreeState nextSeason() {
        return SummerSeasonDeciduousState.getInstance(); 
    }
}