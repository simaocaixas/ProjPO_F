package hva.core;

public enum Season {
    Primavera(0), Verao(1), Outono(2), Inverno(3);

    public final int _numberSeason;

    private Season(int numberSeason) {
        this._numberSeason = numberSeason;
    }

    public int getSeasonNumber() {
        return _numberSeason;
    }
}