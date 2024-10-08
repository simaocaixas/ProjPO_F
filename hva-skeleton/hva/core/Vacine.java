package hva.core;

import java.io.Serializable;
import java.util.*;

public class Vacine extends Identifier {

    private HashSet<Specie> _species;

    public Vacine(Hotel hotel, String idVacine, String VacineName, Collection<Specie> species) {
        super(idVacine, VacineName, hotel);
        _species = new HashSet<Specie>(species);
    }
}
