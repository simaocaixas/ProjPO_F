package hva.core;
import java.util.*;

public class Habitat extends Identifier {              // COMPLETAMENTE INCOMPLETO

    private int _area;

    private Set<String> _notSuitables = new HashSet<>();
    private Set<String> _suitables = new HashSet<>();
    private HashMap<String, Animal> _animals = new HashMap<String, Animal>();
    private HashMap<String, Tree> _trees = new HashMap<String, Tree>();


    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        super(idHabi, nome, hotel);
        _area = area;
    }

    public String habitatToString() {

        StringBuilder sb = new StringBuilder();

        int treeCount = _trees.size(); 
        int index = 0; 

        sb.append(OnlyhabitatToString());
        for (Tree tree : _trees.values()) {
            sb.append(tree.treeToString()); 
            index++;
            if (index < treeCount) {
                sb.append("\n");
            }
        } 

        return sb.toString();
    }

    protected List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>(_animals.values());
        animals.sort(Comparator.comparing(Animal::id, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(animals);
    }
    
    protected String OnlyhabitatToString() {
        return "HABITAT" + "|" + super.toString() + "|" + area() + "|" + numberOfTrees() + (numberOfTrees() == 0 ? "" : "\n");
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.id(),animal);
    }

    protected void addTree(Tree tree) {
        _trees.put(tree.id(),tree);
    }

    protected int area() {
        return _area;
    }

    protected int numberOfTrees() {
        return _trees.size();
    }

    protected void changeHabitat(Habitat habitat, int area) {

        _area = area;
    }
}
