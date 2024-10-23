package hva.core;

import java.io.Serializable;

public class AnimalSatisfactionStrategy implements SatisfactionStrategy, Serializable {
    
    private Animal animal;

    public AnimalSatisfactionStrategy(Animal animal) {
        this.animal = animal;
    }
    
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
