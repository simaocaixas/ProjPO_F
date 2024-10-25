package hva.core;

import java.util.*;
/**
 * Represents a species of animals within a hotel environment. 
 * This class maintains a collection of animals and veterinarians associated with the species.
 */
public class Specie extends Identifier {  

    private HashMap<String,Animal> _animals = new HashMap<String,Animal>();  
    private HashMap<String,Veterinarian> _veterinarians = new HashMap<String,Veterinarian>();  

    /**
     * Constructs a Specie object with the specified hotel, species ID, and species name.
     *
     * @param hotel   the hotel where the species resides
     * @param idSpc   the unique identifier of the species
     * @param nomeSpc the name of the species
     */
    public Specie(Hotel hotel, String idSpc, String nomeSpc) { 
        super(idSpc, nomeSpc, hotel);
    }

    /**
     * Adds an animal to the species' collection.
     *
     * @param animal the animal to be added
     */
    void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

    /**
     * Adds a veterinarian to the species' collection.
     *
     * @param vet the veterinarian to be added
     */
    void addVet(Veterinarian vet) {
        _veterinarians.put(vet.id(), vet);
    }

    /**
     * Returns the number of veterinarians associated with this species.
     *
     * @return the number of veterinarians
     */
    int numberOfVeterinarians() {
        return _veterinarians.size();
    }

    /**
     * Returns the number of animals associated with this species.
     *
     * @return the number of animals
     */
    int numberOfAnimals() {
        return _animals.size();
    }
}
