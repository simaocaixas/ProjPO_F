package hva.core;

/**
 * Represents the four seasons of the year: Spring, Summer, Autumn, and Winter.
 * Each season is associated with a unique number for identification.
 */
public enum Season {

    Primavera(0), Verao(1), Outono(2), Inverno(3);

    private final int _numberSeason;

    /**
     * Constructs a Season enum with the specified season number.
     *
     * @param numberSeason the unique number associated with the season
     */
    private Season(int numberSeason) {
        this._numberSeason = numberSeason;
    }

    /**
     * Returns the unique number associated with the season.
     *
     * @return the season number
     */
    public int getSeasonNumber() {
        return _numberSeason;
    }

    /**
     * Retrieves the Season enum corresponding to the given season number.
     *
     * @param valor the number representing the season
     * @return the corresponding Season enum, or null if no match is found
     */
    public static Season fromValor(int valor) {
        for (Season estacao : Season.values()) {
            if (estacao.getSeasonNumber() == valor) {
                return estacao;
            }
        }
        return null;  
    }

    /**
     * Returns the next season in the annual cycle.
     *
     * @return the next Season enum
     */
    public Season nextSeason() {
        if (this.equals(Inverno)) {
            return Season.Primavera;
        } else {
            return Season.fromValor(this._numberSeason + 1);
        }
    }
}

