package hva.core;
import java.util.*;

import hva.core.Specie;

public class Habitat extends Identifier {              // COMPLETAMENTE INCOMPLETO

    private int _area;

    private HashMap<String, Integer> _adequacy = new HashMap<>();

    private List<Animal> _animals = new ArrayList<>();

    private List<Tree> _trees = new ArrayList<>();


    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        super(idHabi, nome, hotel);
        _area = area;
    }

    public String habitatToString() {

        StringBuilder sb = new StringBuilder();

        int treeCount = numberOfTrees(); 
        int index = 0; 

        sb.append(onlyHabitatToString());
        for (Tree tree : _trees) {
            sb.append(tree.toString()); 
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

    public List<Tree> getAllTrees() {
        _trees.sort(Comparator.comparing(Tree::id, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(_trees);
    }

    public String onlyHabitatToString() {
        return "HABITAT" + "|" + super.toString() + "|" + area() + "|" + numberOfTrees() + (numberOfTrees() == 0 ? "" : "\n");
    }

    public void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    public void addSpecie(Specie specie) {
        if (!_adequacy.containsKey(specie.id())) {
            _adequacy.put(specie.id(),0);
        }
    }

    public void changeAdequacy(Specie specie, int adequacy) {
        _adequacy.put(specie.id(), adequacy);
    }

    public int suitability(Specie idSpc) {
        
        if (_adequacy.containsKey(idSpc.id())) {
            return _adequacy.get(idSpc.id());
        }
        return 0;
    }

    public void addTree(Tree tree) {
        _trees.add(tree);
    }

    public int area() {
        return _area;
    }

    public int numberOfTrees() {
        return _trees.size();
    }

    public void changeHabitat(Habitat habitat, int area) {
        _area = area;
    }

    public int countSameSpc(Specie specie) {
        int count = 0;

        for (Animal animal : _animals) {
            if (animal.specie().id().equals(specie.id())) {
                count++;
            }
        }
        return count;
    }

    public int countDiffSpc(Specie specie) {
        return _animals.size() - countSameSpc(specie);
    }
    
}
