package hva.core;


public class AnimalSatisfactionStrategy implements SatisfactionStrategy {
    
    private Animal animal;

    public AnimalSatisfactionStrategy(Animal animal) {
        this.animal = animal;
    }
    
    public double calculateSatisfaction() {
        
        int sameSpc = animal.habitat().countSameSpc(animal.specie());
        int diffSpc = animal.habitat().countDiffSpc(animal.specie());
        int population = animal.habitat().getAllAnimals().size();
        int area = animal.habitat().area();
        int suitability = animal.habitat().suitability(animal.specie());

        return 20 + 3 * sameSpc - 2 * diffSpc + (area / population) + suitability;
    }
}
