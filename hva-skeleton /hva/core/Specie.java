package hva.core;
import java.util.*;

public class Specie {   // COMPLETAMENTE INCOMPLETO
    

    private String _idSpc; 
    private String _nomeSpc;

    private Hotel _hotel;
    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  

    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        _hotel = hotel;
        _idSpc = idSpc;
        _nomeSpc = nomeSpc; 
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.idAni(), animal);
    }

    protected String idSpc() {
        return _idSpc;
    }
}
