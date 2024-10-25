package hva.core;

import java.util.*;
import hva.core.exception.*;
import java.io.*;


/**
 * This class represents a hotel that has animals, species, habitats, trees, employees, vaccines and registers. <p>
 * This class provides information and functionalities to handle the operations related to the objects given above. <p>
 * This class implements interface Serializable to allow instances of this class to be serialized and deserialized.
 * @author Simao Caixas ist1109632, Antonio Faia ist1109828
 * @version 1.1
 * 
 */

public class Hotel implements Serializable {  


  private HashMap<String,Animal> _animals = new HashMap<String,Animal>(); 
  private HashMap<String,Specie> _species = new HashMap<String,Specie>(); 
  private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>(); 
  private HashMap<String,Employee> _employees = new HashMap<String,Employee>(); 
  private HashMap<String,Vaccine> _vaccines = new HashMap<String,Vaccine>(); 
  private ArrayList<Tree> _trees = new ArrayList<>();
  private ArrayList<Register> _registers = new ArrayList<>();

  @Serial
  private static final long serialVersionUID = 1L;

  private Season _season; 

  private boolean _state;


  /**
   * Constructor of hotel class
   * 
   * This method creates a new hotel object with the current season set to Spring and the state set to false.
   * 
   */
  protected Hotel() {

    _season = Season.Primavera;
    _state = false;

  }

  /**
   * Calculates the total satisfaction of all animals and employees in the hotel.
   * 
   * This method iterates over all animals and employees, summing up their individual satisfaction levels
   * to compute a total satisfaction score for the hotel.
   * 
   * @return the total satisfaction score of the hotel, which is the sum of satisfaction levels of all animals and employees
   */
  double calculateSatisfaction() {

    double total = 0;

    for (Animal animal : _animals.values()) {
      total += animal.calculateSatisfaction();      
    }

    for (Employee employee : _employees.values()) {
      total += employee.calculateSatisfaction();    
    }

    return total;
  }

  /**
   * Registers a new animal and adds it to the hotel's animal hashtable.
   * 
   * @param idAni the animal's unique ID
   * @param nameAni the animal's name
   * @param idSpc the species ID of the animal
   * @param idHabi the habitat ID where the animal is located
   * @throws SpeciesNotKnownException if the species is not found
   * @throws HabitatNotKnownException if the habitat is not found
   */
  public void registerAnimal(String idAni, String nameAni, String idSpc, String idHabi) throws SpeciesNotKnownException, HabitatNotKnownException, AnimalAlreadyThereException {

    if (containsKeyIgnoreCase(_animals,idAni)) {
      throw new AnimalAlreadyThereException(idAni);
    }
    
    Specie specie = getSpecieById(idSpc); 
    Habitat habitat = getHabitatById(idHabi); 
    Animal animal = new Animal(this, idAni, nameAni, specie, habitat);    
    _animals.put(idAni.toLowerCase(),animal); 
    habitat.addSpecie(specie);

    this.setState(true);
  }

  /**
   * Registers a new species in the hotel's species hashtable.
   * 
   * @param idSpc the species unique ID
   * @param nameSpc the species name
   * @throws SpeciesAlreadyThereException if the species already exists in the hashtable
   */
  public void registerSpecie(String idSpc, String nameSpc) throws SpeciesAlreadyThereException {    
  
    if (containsKeyIgnoreCase(_species,idSpc) | specieNameAlreadyExists(nameSpc)) {
      throw new SpeciesAlreadyThereException(idSpc);
    } 

    Specie specie = new Specie(this, idSpc, nameSpc);       
    _species.put(idSpc.toLowerCase(),specie);

    this.setState(true);
  }

  /**
   * Changes the influence of a species in a habitat based on the given influence type.
   * 
   * @param idHabi the ID of the habitat
   * @param idSpc the ID of the species
   * @param influence the type of influence ("POS" for positive, "NEG" for negative, any other value for neutral)
   * @throws HabitatNotKnownException if the habitat with the specified ID is not found
   * @throws SpeciesNotKnownException if the species with the specified ID is not found
   */
  public void changeHabitatInfluence(String idHabi, String idSpc, String influence) throws HabitatNotKnownException, SpeciesNotKnownException {
    
    Habitat habitat = getHabitatById(idHabi);
    Specie specie = getSpecieById(idSpc);

    switch (influence) {
      case "POS":
        habitat.changeAdequacy(specie,20);
        setState(true);
        break;
      case "NEG":
        habitat.changeAdequacy(specie,-20);
        setState(true);
        break;
      default:
        habitat.changeAdequacy(specie,0);
        setState(true);
        break;
    }
  }

  /**
   * Registers a new vaccine and associates it with the species it can vaccinate.
   * 
   * @param idVac the vaccines unique ID
   * @param nameVac the vaccine name
   * @param species an array of species IDs that be vaccinated by this vaccine
   * @throws SpeciesNotKnownException if any species is not found
   */
  public void registerVaccine(String idVac, String nameVac, String species) throws SpeciesNotKnownException {

    List<Specie> speciesSet = new ArrayList<>();

    if (!species.isEmpty()) {
        List<String> speciesIdSet = stringToList(species);
        for (String idSpc : speciesIdSet) {
            Specie specie = getSpecieById(idSpc);
            speciesSet.add(specie);
        }
    }

    Vaccine vaccine = new Vaccine(this, idVac, nameVac, speciesSet);
    _vaccines.put(idVac.toLowerCase(), vaccine);
    this.setState(true);  
}

  /**
   * Registers a new tree and adds it to the hotel's tree hashtable.
   * 
   * @param idHabi the habitat unique ID
   * @param idTree the trees unique ID
   * @param nameTree the tree name
   * @param age the tree's age
   * @param diff the cleaning difficulty level of the tree
   * @param Type the type of tree, either evergreen or deciduous
   * @throws HabitatNotKnownException if the habitat is not found
   * @throws TreeAlreadyThereException if the tree already exists in the hashtable
   */
  public void registerTree(String idHabi, String idTree, String nameTree, int age, int diff, String type) throws HabitatNotKnownException, TreeAlreadyThereException{

    Habitat habitat = getHabitatById(idHabi);
      
    if (containsKeyIgnoreCase(_trees, idTree)) {
      throw new TreeAlreadyThereException(idTree);
    }
      
    if (type.equals("PERENE")) {
      EvergreenTree tree = new EvergreenTree(habitat, idTree, nameTree, age, diff, season());
      _trees.add(tree);
    } else {
      DeciduousTree tree = new DeciduousTree(habitat, idTree, nameTree, age, diff, season());
      _trees.add(tree);
    }
    this.setState(true);  
  }

  /**
   * Creates a tree (Evergreen or Deciduous) and adds it to the hotel's tree list.
   * 
   * @param idTree the tree's unique ID
   * @param nameTree the tree's name
   * @param age the tree's age
   * @param diff the difficulty level of maintaining/cleaning the tree
   * @param type the type of the tree, either "PERENE" (Evergreen) or "CADUCA" (Deciduous)
   * @throws TreeAlreadyThereException if a tree with the same ID already exists
   */
  void createTree(String idTree, String nameTree, int age, int diff, String type) throws TreeAlreadyThereException{

    if (containsKeyIgnoreCase(_trees, idTree)) {
      throw new TreeAlreadyThereException(idTree);
    }

    if (type.equals("PERENE")) {
      EvergreenTree tree = new EvergreenTree(idTree, nameTree, age, diff, season());
      _trees.add(tree);
    } else {
      DeciduousTree tree = new DeciduousTree(idTree, nameTree, age, diff, season());
      _trees.add(tree);
    }
    this.setState(true);  
  }

  /**
   * Assigns a new responsibility to an employee.
   * 
   * @param idEmp the employee's unique ID
   * @param idResponsability the ID of a responsability
   * @throws EmployeeNotKnownException if the employee is not found
   * @throws ResponsabilityNotThereException if the responsibility does not exist
   */
  public void newResponsability(String idEmp, String idResponsability) throws EmployeeNotKnownException, ResponsabilityNotThereException {
    
    if (!containsKeyIgnoreCase(_employees,idEmp)) {
      throw new EmployeeNotKnownException(idEmp);
    } 

    Employee employee = getEmployeeById(idEmp);
    employee.addResponsibility(idResponsability); 

    this.setState(true);
  }

  /**
   * Removes a specific responsibility from an employee.
   * 
   * @param idEmp the ID of the employee
   * @param idResponsability the ID of the responsibility to be removed
   * @throws EmployeeNotKnownException if the employee is not found
   * @throws ResponsabilityNotThereException if the responsibility is not found for the given employee
   */
  public void removeResponsability(String idEmp, String idResponsability) throws EmployeeNotKnownException, ResponsabilityNotThereException {
    
    if (!containsKeyIgnoreCase(_employees,idEmp)) {
      throw new EmployeeNotKnownException(idEmp);
    } 

    Employee employee = getEmployeeById(idEmp);
    employee.removeResponsibility(idResponsability); 

    this.setState(true);
  }

  /**
   * Registers a new habitat in the hotel's habitat hashtable.
   * 
   * @param idHabi the habitat's unique ID
   * @param nameHabi the habitat's name
   * @param area the area size of the habitat
   * @throws HabitatAlreadyThereException if a habitat with the same ID already exists in the hashtable
   */
  public void registerHabitat(String idHabi, String nameHabi, int area) throws HabitatAlreadyThereException {
            
    if(containsKeyIgnoreCase(_habitats,idHabi)) {
      throw new HabitatAlreadyThereException(idHabi);
    }

    Habitat habitat = new Habitat(this, idHabi, nameHabi, area);
    _habitats.put(idHabi.toLowerCase(), habitat);
    this.setState(true);
  }

  /**
   * Registers a new employee and adds them to the hotel's employee hashtable.
   * 
   * @param idEmp the ID of the employee
   * @param nameEmp the name of the employee
   * @param type the type of employee, either "VET" for Veterinarian or any other string for ZooKeeper
   * @throws EmployeeAlreadyThereException if an employee with the same ID already exists
   */
  public void registerEmployee(String idEmp, String nameEmp, String type) throws EmployeeAlreadyThereException {
    
    if (containsKeyIgnoreCase(_employees, idEmp)) {
      throw new EmployeeAlreadyThereException(idEmp);
    } 

    if (type.equals("VET")) {
      Veterinarian veterinarian = new Veterinarian(idEmp, nameEmp, this);
      _employees.put(idEmp.toLowerCase(), veterinarian);
    } else {
      ZooKeeper zooKeeper = new ZooKeeper(idEmp, nameEmp,this);
      _employees.put(idEmp.toLowerCase(),zooKeeper);
    }
    this.setState(true);
  
  }

  /**
   * Vaccinates an animal using a specific vaccine administered by a veterinarian.
   * 
   * @param idVac the ID of the vaccine
   * @param idAni the ID of the animal to vaccinate
   * @param idVet the ID of the veterinarian administering the vaccine
   * @throws AnimalNotKnownException if the animal is not found
   * @throws VaccineNotKnownException if the vaccine is not found
   * @throws EmployeeNotKnownException if the veterinarian is not found
   * @throws VetNotAuthorizedException if the employee is not a veterinarian authorized to vaccinate
   */
  public void vaccineAnimal(String idVac, String idAni, String idVet) throws AnimalNotKnownException, VaccineNotKnownException, EmployeeNotKnownException, VetNotAuthorizedException {
    
    Animal animal = getAnimalById(idAni);
    Vaccine vaccine = getVaccineById(idVac);
    Employee employee = getEmployeeById(idVet);

    if (employee instanceof Veterinarian ) {
      Veterinarian veterinarian = (Veterinarian) employee;
      veterinarian.vaccinateAnimal(animal, vaccine);
    } else {
      throw new EmployeeNotKnownException(idVet);
    } 

    this.setState(true);
  }

  /**
   * Transfers an animal from its current habitat to a new one.
   * 
   * @param idAni the ID of the animal to transfer
   * @param idHabi the ID of the new habitat for the animal
   * @throws AnimalNotKnownException if the animal is not found
   * @throws HabitatNotKnownException if the new habitat is not found
   */
  public void transferAnimal(String idAni, String idHabi) throws AnimalNotKnownException, HabitatNotKnownException{
      
      Animal animal = getAnimalById(idAni);
      Habitat habitat = getHabitatById(idHabi);
  
      animal.transfer(habitat);
      this.setState(true);
  }

  /**
   * Advances the hotel's current season to the next one. 
   * Each tree within the hotel will also update its internal season.
   */
  void nextSeason() {
    _season = _season.nextSeason();
  
    for (Tree tree: _trees) {
      tree.advanceSeason();
    }

    this.setState(true);
  }

  /**
   * Converts a string of items separated by commas into a list of strings.
   * 
   * @param items a string containing items separated by commas
   * @return a list of strings, each representing an item from the input string
   */
  private List<String> stringToList(String items) {

    List<String> list = new ArrayList<>();
    String[] itemsArray = items.split(",");
    for (String item : itemsArray) {
      list.add(item);
    }
    return list;
  } 

  /**
   * Adds a new register to the hotel's register list.
   * 
   * @param register the register to add
   */
  void addRegister(Register register) {
    _registers.add(register);
  }


  /**
   * Modifies the area of an existing habitat.
   * 
   * @param idHabi the ID of the habitat to be changed
   * @param area the new area for the habitat
   * @throws HabitatNotKnownException if the habitat with the specified ID is not found
   */
  public void changeHabitat(String idHabi, int area) throws HabitatNotKnownException {
    Habitat habitat = getHabitatById(idHabi);
    habitat.changeHabitat(habitat, area);
    this.setState(true);
  }

  /**
   * Retrieves an animal by its ID.
   * 
   * This method fetches the animal with the specified ID from the internal collection of animals.
   * If the animal is not found, an AnimalNotKnownException is thrown.
   * 
   * @param idAni the ID of the animal to retrieve
   * @return the Animal with the given ID
   * @throws AnimalNotKnownException if no animal with the specified ID is found
   */
  public Animal getAnimalById(String idAni) throws AnimalNotKnownException {
    if (getById(_animals, idAni) != null) {
      return getById(_animals, idAni);
    } else {
      throw new AnimalNotKnownException(idAni);
    }
  }

  /**
   * Retrieves a species by its ID.
   * 
   * This method fetches the species with the specified ID from the internal collection of species.
   * If the species is not found, a SpeciesNotKnownException is thrown.
   * 
   * @param idSpc the ID of the species to retrieve
   * @return the Specie with the given ID
   * @throws SpeciesNotKnownException if no species with the specified ID is found
   */
  public Specie getSpecieById(String idSpc) throws SpeciesNotKnownException {
    if (getById(_species, idSpc) != null) {
      return getById(_species, idSpc);
    } else {
      throw new SpeciesNotKnownException(idSpc);
    }
  }

  /**
   * Retrieves a tree by its ID.
   * 
   * This method fetches the tree with the specified ID from the internal collection of trees.
   * If the tree is not found, a TreeNotKnownException is thrown.
   * 
   * @param idTree the ID of the tree to retrieve
   * @return the Tree with the given ID
   * @throws TreeNotKnownException if no tree with the specified ID is found
   */
  public Tree getTreeById(String idTree) throws TreeNotKnownException {
    if (getById(_trees, idTree) != null) {
      return getById(_trees, idTree);
    } else {
      throw new TreeNotKnownException(idTree);
    }
  }

  /**
   * Retrieves a habitat by its ID.
   * 
   * This method fetches the habitat with the specified ID from the internal collection of habitats.
   * If the habitat is not found, a HabitatNotKnownException is thrown.
   * 
   * @param idHabi the ID of the habitat to retrieve
   * @return the Habitat with the given ID
   * @throws HabitatNotKnownException if no habitat with the specified ID is found
   */
  public Habitat getHabitatById(String idHabi) throws HabitatNotKnownException {
    if (getById(_habitats, idHabi) != null) {
      return getById(_habitats, idHabi);
    } else {
      throw new HabitatNotKnownException(idHabi);
    }
  }

  /**
   * Retrieves an employee by their ID.
   * 
   * This method fetches the employee with the specified ID from the internal collection of employees.
   * If the employee is not found, an EmployeeNotKnownException is thrown.
   * 
   * @param idEmp the ID of the employee to retrieve
   * @return the Employee with the given ID
   * @throws EmployeeNotKnownException if no employee with the specified ID is found
   */
  public Employee getEmployeeById(String idEmp) throws EmployeeNotKnownException {
    if (getById(_employees, idEmp) != null) {
      return getById(_employees, idEmp);
    } else {
      throw new EmployeeNotKnownException(idEmp);
    }
  }

  /**
   * Retrieves a vaccine by its ID.
   * 
   * This method fetches the vaccine with the specified ID from the internal collection of vaccines.
   * If the vaccine is not found, a VaccineNotKnownException is thrown.
   * 
   * @param idVac the ID of the vaccine to retrieve
   * @return the Vaccine with the given ID
   * @throws VaccineNotKnownException if no vaccine with the specified ID is found
   */
  public Vaccine getVaccineById(String idVac) throws VaccineNotKnownException {
    if (getById(_vaccines, idVac) != null) {
      return getById(_vaccines, idVac);
    } 
    throw new VaccineNotKnownException(idVac);
  }

  /**
   * Checks if a given key exists in the map, ignoring case sensitivity.
   * 
   * @param map the map to check
   * @param key the key to search for (case-insensitive)
   * @return true if the map contains the key, otherwise returns false
   */
  private boolean containsKeyIgnoreCase(Map<String, ?> map, String key) {
    if (map.containsKey(key.toLowerCase())) {
      return true;
    }
    return false;
  }

  /**
   * Checks if a species with the given name already exists.
   * 
   * This method iterates through the collection of species and compares the provided name
   * (case-insensitive) with the names of the existing species. If a match is found, it returns true.
   * 
   * @param name the name of the species to check for
   * @return true if a species with the given name already exists, false otherwise
   */
  private boolean specieNameAlreadyExists(String name) {
    for (Specie specie : _species.values()) {
      if (specie.name().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a given key exists in the map, ignoring case sensitivity.
   * 
   * @param map the map to check
   * @param key the key to search for (case-insensitive)
   * @return true if the map contains the key, otherwise returns false
   */
  public <T extends Identifier> boolean containsKeyIgnoreCase(List<T> list, String key) {
    for (T item : list) {
        if (item.id().equalsIgnoreCase(key)) {  
            return true;
        }
    }
    return false;
  }

  /**
   * Retrieves an entity from a map by its ID, ignoring case sensitivity.
   * If the entity is not found, it throws a custom exception.
   * 
   * @param <T> the type of entity to retrieve
   * @param <E> the type of exception to throw if the entity is not found
   * @param map the map containing entities
   * @param id  the ID of the entity to retrieve (case-insensitive)
   * @param exception the exception to throw if the entity is not found
   * @return the entity corresponding to the given ID
   * @throws E the exception thrown if the entity is not found
   */
  private <T> T getById(Map<String, T> map, String id) {
    if (containsKeyIgnoreCase(map, id.toLowerCase())) {
      return map.get(id.toLowerCase());

    }
    return null;
  }

  /**
   * Retrieves an entity from a list by its ID, ignoring case sensitivity.
   * If the entity is not found, it throws a custom exception.
   * 
   * @param <T> the type of entity to retrieve
   * @param <E> the type of exception to throw if the entity is not found
   * @param list the list containing entities
   * @param id the ID of the entity to retrieve (case-insensitive)
   * @param exception the exception to throw if the entity is not found
   * @return the entity corresponding to the given ID
   * @throws E the exception thrown if the entity is not found
   */
  private <T extends Identifier> T getById(List<T> list, String id) {
    for (T item : list) {
        if (item.id().equalsIgnoreCase(id)) {  
            return item;
        }
    }
    return null;
  }

  /**
   * Retrieves the current season in the hotel.
   * 
   * @return the current season, of type Season (enum)
   */

  Season season() {
    return _season;
  }

  /**
   * Updates the internal state of the hotel.
   * 
   * @param state a boolean representing the new state of the hotel
   */

  void setState(Boolean state) {
    _state = state;
  }

  /**
   * Retrieves the current state of the hotel.
   * 
   * @return true if the hotel state is has active changes, false otherwise
   */

  boolean getState() {
    return _state;
  }

  /**
 * Retrieves a unmodifiable list of all species.
 *
 * @return a unmodifiable list containing all species.
 */
 public List<Specie> getAllSpecies() {
    return getAllEntities(_species);
 }

 /**
  * Retrieves a unmodifiable list of all trees.
  *
  * @return a unmodifiable list containing all trees.
  */
  private List<Tree> getAllTrees() {
    return getAllEntities(_trees);
 }

/**
 * Retrieves a unmodifiable list of all animals.
 *
 * @return a unmodifiable list containing all animals.
 */
 public List<Animal> getAllAnimals() {
    return getAllEntities(_animals);
 }

 /**
  * Retrieves a unmodifiable list of all employees.
  *
  * @return a unmodifiable list containing all employees.
  */
 public List<Employee> getAllEmployees() {
    return getAllEntities(_employees);
 }

 /**
 * Retrieves a unmodifiable list of all habitats.
 *
 * @return a unmodifiable list containing all habitats.
 */
 public List<Habitat> getAllHabitats() {
    return getAllEntities(_habitats);
 }

/**
 * Retrieves a unmodifiable list of all vaccines.
 *
 * @return a unmodifiable list containing all vaccines.
 */
 public List<Vaccine> getAllVaccines() {
    return getAllEntities(_vaccines);
}

/**
 * Retrieves an unmodifiable list of all registers.
 *
 * @return an unmodifiable list containing all registers.
 */
public List<Register> getAllRegisters() {
    return Collections.unmodifiableList(_registers);
}

/**
  * Retrieves all entities from the specified hashmap, sorts them by their IDs (case-insensitive), 
  * and returns an unmodifiable list.
  * 
  * @param <T> the type of entities stored in the map, which must implement interface Identifier 
  * @param entityMap the map containing entities with string keys
  * @return an unmodifiable list of all entities, sorted by their IDs in a case-insensitive manner
  */
private <T extends Identifier> List<T> getAllEntities(Map<String, T> entityMap) {
  List<T> entityList = new ArrayList<>(entityMap.values());
  entityList.sort(Comparator.comparing(T::id, String.CASE_INSENSITIVE_ORDER));
  return Collections.unmodifiableList(entityList);
}

private <T extends Identifier> List<T> getAllEntities(List <T> entityList) {
    entityList.sort(Comparator.comparing(T::id, String.CASE_INSENSITIVE_ORDER));
    return Collections.unmodifiableList(entityList);
}
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
    
  }
}
