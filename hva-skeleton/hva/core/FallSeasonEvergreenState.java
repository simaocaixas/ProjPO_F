package hva.core;

/**
 * Singleton class representing the state of an evergreen tree during the fall season.
 * This class implements the TreeState interface and provides specific behavior for the fall season.
 */
public class FallSeasonEvergreenState implements TreeState {

    // Singleton instance of FallSeasonEvergreenState
    private static final FallSeasonEvergreenState INSTANCE = new FallSeasonEvergreenState();

    // Private constructor to prevent instantiation
    private FallSeasonEvergreenState() {}

    /**
     * Returns the singleton instance of FallSeasonEvergreenState.
     *
     * @return the singleton instance of FallSeasonEvergreenState
     */
    public static FallSeasonEvergreenState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the fall season.
     *
     * @return a string representing the biological state of the tree ("COMFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "COMFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the fall season.
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
     * @return the singleton instance of WinterSeasonEvergreenState
     */
    @Override
    public TreeState nextSeason() {
        return WinterSeasonEvergreenState.getInstance(); 
    }
}