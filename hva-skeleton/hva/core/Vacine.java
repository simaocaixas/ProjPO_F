package hva.core;

import java.util.*;

public class Vacine extends Identifier{

    private Hotel _hotel;
    private HashSet<Specie> _species;

    public Vacine(Hotel hotel, String idVacine, String VacineName, Collection<Specie> species) {
        super(idVacine, VacineName);
        _hotel = hotel;
        _species = new HashSet<Specie>(species);
    }
}
