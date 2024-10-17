package hva.core;

import hva.core.Employee;
import hva.core.exception.*;
import java.util.*;
import java.io.*;

/**
 * This class represents a hotel that has animals, species, habitats, trees, employees, vaccines and registers. <p>
 * This class provides information and functionalities to handle the operations related to the objects given above. <p>
 * This class implements interface Serializable to allow instances of this class to be serialized and deserialized.
 * @author Simao Caixas ist1109632, Antonio Faia ist1109828
 * @version 1.0
 * 
 */

public class Hotel implements Serializable {  


  private HashMap<String,Animal> _animals = new HashMap<String,Animal>(); 
  private HashMap<String,Specie> _species = new HashMap<String,Specie>(); 
  private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>(); 
  private HashMap<String,Employee> _employees = new HashMap<String,Employee>(); 
  private HashMap<String,Vaccine> _vaccines = new HashMap<String,Vaccine>(); 
  private HashMap<String,Tree> _trees = new HashMap<String,Tree>();

  @Serial
  private static final long serialVersionUID = 1L;

  private Season _season; 

  private boolean _state;

  public Hotel() {

    _season = Season.Primavera;
    _state = false;

  }

  public double claculateSatisfaction() {

    double total = 0;

    for (Animal animal : _animals.values()) {
    ///   total += animal.calculateSatisfaction();      <--- TO DO! 
    }

    for (Employee employee : _employees.values()) {
    ///   total += employee.calculateSatisfaction();    <--- TO DO!
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
  public void registerAnimal(String idAni, String nameAni, String idSpc, String idHabi) throws SpeciesNotKnownException, HabitatNotKnownException {

    Specie specie = getSpecieById(idSpc); 
    Habitat habitat = getHabitatById(idHabi); 
    Animal animal = new Animal(this, idAni, nameAni, specie, habitat);    
    _animals.put(idAni,animal); 
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
  
    if (containsKeyIgnoreCase(_species,idSpc)) {
      throw new SpeciesAlreadyThereException(idSpc);
    }

    Specie specie = new Specie(this, idSpc, nameSpc);       
    _species.put(idSpc,specie);
    this.setState(true);

  }

  /**
   * Registers a new vaccine and associates it with the species it can vaccinate.
   * 
   * @param idVac the vaccines unique ID
   * @param nameVac the vaccine name
   * @param species an array of species IDs that be vaccinated by this vaccine
   * @throws SpeciesNotKnownException if any species is not found
   */
  public void registerVaccine(String idVac, String nameVac, String[] species) throws SpeciesNotKnownException {

    List<Specie> speciesSet = new ArrayList<Specie>();
    for (String idSpc : species) {
      Specie specie = getSpecieById(idSpc);
      speciesSet.add(specie);
    }

    Vaccine vaccine = new Vaccine(this, idVac, nameVac, speciesSet);
    _vaccines.put(idVac,vaccine);
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
      
    if (containsKeyIgnoreCase(_trees,idTree)) {
      throw new TreeAlreadyThereException(idTree);
    }
      
    if (type.equals("PERENE")) {
      EvergreenTree tree = new EvergreenTree(habitat, idTree, nameTree, diff, season());
      _trees.put(idTree,tree);
    } else {
      DeciduousTree tree = new DeciduousTree(habitat, idTree, nameTree, diff, season());
      _trees.put(idTree,tree);
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
    _habitats.put(idHabi, habitat);
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
      _employees.put(idEmp, veterinarian);
    } else {
      ZooKeeper zooKeeper = new ZooKeeper(idEmp, nameEmp,this);
      _employees.put(idEmp,zooKeeper);
    }
    this.setState(true);
  
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

  public Specie getSpecieById(String idSpc) throws SpeciesNotKnownException {
    return getById(_species, idSpc, new SpeciesNotKnownException(idSpc));
  }

  public Tree getTreeById(String idTree) throws TreeNotKnownException {
    return getById(_trees, idTree, new TreeNotKnownException(idTree));
  }

  public Habitat getHabitatById(String idHabi) throws HabitatNotKnownException {
    return getById(_habitats, idHabi, new HabitatNotKnownException(idHabi));
  }

  public Employee getEmployeeById(String idEmp) throws EmployeeNotKnownException {
    return getById(_employees, idEmp, new EmployeeNotKnownException(idEmp));
  }

  /**
   * Checks if a given key exists in the map, ignoring case sensitivity.
   * 
   * @param map the map to check
   * @param key the key to search for (case-insensitive)
   * @return true if the map contains the key, otherwise returns false
   */
  private boolean containsKeyIgnoreCase(Map<String, ?> map, String key) {
    if (map.containsKey(key)) {
      return true;
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
  private <T, E extends Throwable> T getById(Map<String, T> map, String id, E exception) throws E {
    if (map.containsKey(id.equalsIgnoreCase()))
    }
    throw exception;
  }
  }

  public Season season() {
    return _season;
  }

  public void setState(Boolean state) {
    _state = state;
  }

  public boolean getState() {
    return _state;
  }

  public List<Tree> getAllTrees() {
    return getAllEntities(_trees);
  }

  public List<Animal> getAllAnimals() {
    return getAllEntities(_animals);
  }

  public List<Employee> getAllEmployees() {
    return getAllEntities(_employees);
  }

  public List<Habitat> getAllHabitats() {
    return getAllEntities(_habitats);
  }

  public List<Vaccine> getAllVaccines() {
    return getAllEntities(_vaccines);
  }

  /**
   * Retrieves all entities from the specified hashmap, sorts them by their IDs (case-insensitive), 
   * and returns an unmodifiable list.
   * 
   * @param <T> the type of entities stored in the map, which must implement interface Identifier 
   * @param entityMap the map containing entities with string keys
   * @return an unmodifiable list of all entities, sorted by their IDs in a case-insensitive manner
   */
  public <T extends Identifier> List<T> getAllEntities(Map<String, T> entityMap) {
    List<T> entityList = new ArrayList<>(entityMap.values());
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
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
    
  }
}
