package hva.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Habitat {              // COMPLETAMENTE INCOMPLETO

    private String _nome;
    private String _idHabi;
    private int _area;

    private Set<String> _notSutables = new HashSet<>();
    private Set<String> _sutables = new HashSet<>();
    private HashMap<String, Animal> _animals = new HashMap<String, Animal>();
    private Hotel _hotel;

    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        _hotel = hotel;
        _idHabi = idHabi;
        _nome = nome;
        _area = area;
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.idAni(),animal);
    }

    protected String idHabi() {
        return _idHabi;
    }
}
