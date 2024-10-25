package hva.core;

import java.util.*;

/**
 * Represents a habitat within the zoo, managing the animals, trees, and 
 * zookeepers associated with it. The habitat has an area and is responsible 
 * for maintaining the adequacy of various species that inhabit it.
 */
public class Habitat extends Identifier {

    private int _area;
    private HashMap<String, Integer> _adequacy = new HashMap<>();
    private List<ZooKeeper> _zookeepers = new ArrayList<>();
    private List<Animal> _animals = new ArrayList<>();
    private List<Tree> _trees = new ArrayList<>();

    /**
     * Constructs a Habitat with the specified attributes.
     *
     * @param hotel the hotel managing the habitat
     * @param idHabi the unique identifier for the habitat
     * @param nome the name of the habitat
     * @param area the area of the habitat
     */
    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        super(idHabi, nome, hotel);
        _area = area;
    }

    /**
     * Returns the number of zookeepers assigned to this habitat.
     *
     * @return the number of zookeepers
     */
    int getNumberOfZookeepers() {
        return _zookeepers.size();
    }

    /**
     * Removes the specified animal from the habitat.
     *
     * @param animal the animal to remove
     */
    void removeAnimal(Animal animal) {
        _animals.remove(animal);
    }

    /**
     * Adds a zookeeper to this habitat.
     *
     * @param zookeeper the zookeeper to add
     */
    void addZooKeeper(ZooKeeper zookeeper) {
        _zookeepers.add(zookeeper);
    }

    /**
     * Returns a string representation of the habitat and its trees.
     *
     * @return a formatted string of habitat details and trees
     */
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

    /**
     * Returns the total population of animals in this habitat.
     *
     * @return the number of animals
     */
    int population() {
        return _animals.size();
    }

    /**
     * Returns an unmodifiable list of all animals in the habitat, sorted by ID.
     *
     * @return a sorted list of animals
     */
    public List<Animal> getAllAnimals() {
        _animals.sort(Comparator.comparing(Animal::id, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(_animals);
    }

    /**
     * Returns an unmodifiable list of all trees in the habitat, sorted by ID.
     *
     * @return a sorted list of trees
     */
    public List<Tree> getAllTrees() {
        _trees.sort(Comparator.comparing(Tree::id, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(_trees);
    }

    /**
     * Returns a string representation of the habitat's basic details.
     *
     * @return a formatted string with habitat information
     */
    String onlyHabitatToString() {
        return "HABITAT" + "|" + super.toString() + "|" + area() + "|" + numberOfTrees() + (numberOfTrees() == 0 ? "" : "\n");
    }

    /**
     * Adds an animal to the habitat.
     *
     * @param animal the animal to add
     */
    void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    /**
     * Adds a species to the habitat, initializing its adequacy if not already present.
     *
     * @param specie the species to add
     */
    void addSpecie(Specie specie) {
        if (!_adequacy.containsKey(specie.id())) {
            _adequacy.put(specie.id(), 0);
        }
    }

    /**
     * Updates the adequacy value for the specified species.
     *
     * @param specie the species to update
     * @param adequacy the new adequacy value
     */
    void changeAdequacy(Specie specie, int adequacy) {
        _adequacy.put(specie.id(), adequacy);
    }

    /**
     * Returns the adequacy value for the specified species.
     *
     * @param idSpc the species to check
     * @return the adequacy value, or 0 if the species is not present
     */
    int suitability(Specie idSpc) {
        return _adequacy.getOrDefault(idSpc.id(), 0);
    }

    /**
     * Adds a tree to the habitat.
     *
     * @param tree the tree to add
     */
    void addTree(Tree tree) {
        _trees.add(tree);
    }

    /**
     * Returns the area of the habitat.
     *
     * @return the habitat area
     */
    int area() {
        return _area;
    }

    /**
     * Returns the number of trees in the habitat.
     *
     * @return the number of trees
     */
    int numberOfTrees() {
        return _trees.size();
    }

    /**
     * Changes the area of the habitat.
     *
     * @param habitat the habitat being modified (not used in this implementation)
     * @param area the new area value
     */
    void changeHabitat(Habitat habitat, int area) {
        _area = area;
    }

    /**
     * Counts the number of animals in the habitat that belong to the specified species.
     *
     * @param specie the species to count
     * @return the count of animals of the specified species
     */
    int countSameSpc(Specie specie) {
        int count = 0;

        for (Animal animal : _animals) {
            if (animal.specie().id().equals(specie.id())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of animals in the habitat that do not belong to the specified species.
     *
     * @param specie the species to check against
     * @return the count of animals of different species
     */
    int countDiffSpc(Specie specie) {
        return _animals.size() - countSameSpc(specie);
    }
}

