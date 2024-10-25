package hva.core;

/**
 * Singleton class representing the state of an evergreen tree during the winter season.
 * This class implements the TreeState interface and provides specific behavior for the winter season.
 */
public class WinterSeasonEvergreenState implements TreeState {

    // Singleton instance of WinterSeasonEvergreenState
    private static final WinterSeasonEvergreenState INSTANCE = new WinterSeasonEvergreenState();

    // Private constructor to prevent instantiation
    private WinterSeasonEvergreenState() {}

    /**
     * Returns the singleton instance of WinterSeasonEvergreenState.
     *
     * @return the singleton instance of WinterSeasonEvergreenState
     */
    public static WinterSeasonEvergreenState getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the biological state of the tree during the winter season.
     *
     * @return a string representing the biological state of the tree ("LARGARFOLHAS")
     */
    @Override
    public String biologicalState() {
        return "LARGARFOLHAS";
    }

    /**
     * Returns the seasonal effort required by the tree during the winter season.
     *
     * @return an integer representing the seasonal effort (2)
     */
    @Override
    public int seasonalEffort() {
        return 2;
    }

    /**
     * Returns the next state of the tree for the upcoming season.
     *
     * @return the singleton instance of SpringSeasonEvergreenState
     */
    @Override
    public TreeState nextSeason() {
        return SpringSeasonEvergreenState.getInstance(); 
    }
}