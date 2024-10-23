package hva.core;
import java.util.*;

public class Specie extends Identifier {  

    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  
    private HashMap<String,Veterinarian> _veterinarians = new HashMap<String,Veterinarian>();  

    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        super(idSpc, nomeSpc, hotel);
    }

    void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

    void addVet(Veterinarian vet) {
        _veterinarians.put(vet.id(), vet);
    }

    int numberOfVeterinarians() {
        return _veterinarians.size();
    }

    int numberOfANimals() {
        return _animals.size();
    }
}
