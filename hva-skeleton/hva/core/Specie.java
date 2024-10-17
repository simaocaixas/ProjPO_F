package hva.core;
import java.util.*;

public class Specie extends Identifier {  

    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  

    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        super(idSpc, nomeSpc, hotel);
    }

    void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

}
