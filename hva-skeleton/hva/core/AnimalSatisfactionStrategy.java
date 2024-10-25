package hva.core;

import java.io.Serializable;

/**
 * Represents a strategy for calculating the satisfaction level of an animal within its habitat.
 * This class implements the SatisfactionStrategy interface, defining the criteria 
 * for determining the satisfaction of an animal based on various factors related to its species 
 * and habitat.
 */
public class AnimalSatisfactionStrategy implements SatisfactionStrategy, Serializable {
    
    private Animal animal;

    /**
     * Constructs an AnimalSatisfactionStrategy with the specified animal.
     *
     * @param animal the animal for which the satisfaction will be calculated
     */
    AnimalSatisfactionStrategy(Animal animal) {
        this.animal = animal;
    }
    
    /**
     * Calculates the satisfaction level of the animal.
     * The calculation is based on the number of animals of the same species,
     * the number of animals of different species, the habitat area, population density,
     * and habitat suitability for the species.
     *
     * @return the calculated satisfaction level of the animal
     */
    public double calculateSatisfaction() {
        
        double Satisfaction = 20;

        int sameSpc = animal.habitat().countSameSpc(animal.specie()) - 1;  // -1 porque nao conta o proprio animal LOL
        int diffSpc = animal.habitat().countDiffSpc(animal.specie());
        int population = animal.habitat().getAllAnimals().size();
        int area = animal.habitat().area();
        int suitability = animal.habitat().suitability(animal.specie());

        Satisfaction += (3 * sameSpc) - (2 * diffSpc) + (area / population) + suitability;
        return Satisfaction;
    }
}
