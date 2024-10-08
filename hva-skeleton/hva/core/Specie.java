package hva.core;
import java.io.Serializable;
import java.util.*;

public class Specie extends Identifier {   // COMPLETAMENTE INCOMPLETO

    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  

    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        super(idSpc, nomeSpc, hotel);
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

}
