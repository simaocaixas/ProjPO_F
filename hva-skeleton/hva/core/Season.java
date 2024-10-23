package hva.core;

public enum Season {

    Primavera(0), Verao(1), Outono(2), Inverno(3);

    private final int _numberSeason;

    private Season(int numberSeason) {
        this._numberSeason = numberSeason;
    }

    public int getSeasonNumber() {
        return _numberSeason;
    }

    public static Season fromValor(int valor) {
        for (Season estacao : Season.values()) {
            if (estacao.getSeasonNumber() == valor) {
                return estacao;
            }
        }
        return null;  
    }

    public Season nextSeason() {
        if (this.equals(Inverno)) {
            return Season.Primavera;
        } else {
            return Season.fromValor(this._numberSeason + 1);
        }
    }
}
