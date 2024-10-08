package hva.core;

import java.util.*;

public class Vacine {
   
    private String _idVacine;
    private String _VacineName;

    private Hotel _hotel;
    private HashSet<Specie> _species;

    public Vacine(Hotel hotel, String idVacine, String VacineName, Collection<Specie> species) {
        _idVacine = idVacine;
        _VacineName = VacineName;
        _hotel = hotel;
        _species = new HashSet<Specie>(species);
    }
}
