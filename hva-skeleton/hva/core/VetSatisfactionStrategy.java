package hva.core;

import java.io.Serializable;
/**
 * Represents a strategy for calculating the satisfaction of a veterinarian based
 * on the number of animals and the number of veterinarians for each species they oversee.
 * This class implements the SatisfactionStrategy interface and provides a specific
 * algorithm to derive satisfaction scores.
 */
public class VetSatisfactionStrategy implements SatisfactionStrategy, Serializable {

    private Veterinarian _veterinarian;

    /**
     * Constructs a VetSatisfactionStrategy object with the specified veterinarian.
     *
     * @param vet the veterinarian for whom satisfaction will be calculated
     */
    VetSatisfactionStrategy(Veterinarian vet) {
        _veterinarian = vet;
    }

    /**
     * Calculates the satisfaction score for the veterinarian based on the species they oversee.
     * The initial satisfaction score is set to 20. The score is then adjusted based on the
     * ratio of the number of animals to the number of veterinarians for each species.
     *
     * @return the calculated satisfaction score
     */
    public double calculateSatisfaction() {
        double satisfaction = 20;

        for (Specie specie : _veterinarian.getSpecies()) {
            int population = specie.numberOfAnimals();
            int numVeterinarians = specie.numberOfVeterinarians();

            satisfaction -= (double) population / numVeterinarians;        
        }

        return satisfaction;
    }
}
