package hva.core;
import java.util.*;

public class Habitat extends Identifier {              // COMPLETAMENTE INCOMPLETO

    private int _area;

    private Set<String> _notSuitables = new HashSet<>();
    private Set<String> _suitables = new HashSet<>();

    private List<Animal> _animals = new ArrayList<>();

    private HashMap<String, Tree> _trees = new HashMap<String, Tree>();


    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        super(idHabi, nome, hotel);
        _area = area;
    }

    public String habitatToString() {

        StringBuilder sb = new StringBuilder();

        int treeCount = _trees.size(); 
        int index = 0; 

        sb.append(onlyhabitatToString());
        for (Tree tree : _trees.values()) {
            sb.append(tree.treeToString()); 
            index++;
            if (index < treeCount) {
                sb.append("\n");
            }
        } 

        return sb.toString();
    }

    public List<Animal> getAllAnimals() {
        _animals.sort(Comparator.comparing(Animal::id, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(_animals);
    }

    String onlyhabitatToString() {
        return "HABITAT" + "|" + super.toString() + "|" + area() + "|" + numberOfTrees() + (numberOfTrees() == 0 ? "" : "\n");
    }

    void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    void addTree(Tree tree) {
        _trees.put(tree.id(),tree);
    }

    int area() {
        return _area;
    }

    int numberOfTrees() {
        return _trees.size();
    }

    void changeHabitat(Habitat habitat, int area) {

        _area = area;
    }
}
