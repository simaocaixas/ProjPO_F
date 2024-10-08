package hva.core;
import java.util.*;

public class Specie extends Identifier {   // COMPLETAMENTE INCOMPLETO

    private Hotel _hotel;
    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  

    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        super(idSpc, nomeSpc);
        _hotel = hotel;
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }
    
}
