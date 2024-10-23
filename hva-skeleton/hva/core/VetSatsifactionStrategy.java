package hva.core;

import java.io.Serializable;

public class VetSatsifactionStrategy implements SatisfactionStrategy, Serializable {
    
    Veterinarian _veterinarian;

    public VetSatsifactionStrategy(Veterinarian vet) {
        _veterinarian = vet;
    }

    public double calculateSatisfaction() {
        
        double satisfaction = 20;

        for (Specie specie : _veterinarian.getSpecies()) {

            int population = specie.numberOfANimals();
            int numVeterinarians = specie.numberOfVeterinarians();

            satisfaction -= (double) population / numVeterinarians;        
        }

        return satisfaction;

    }   
}
