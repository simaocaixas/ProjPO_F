package hva.core;

import hva.core.exception.*;
import java.util.*;
import java.io.*;

public class Hotel implements Serializable {  

  private HashMap<String,Animal> _animals = new HashMap<String,Animal>(); 
  private HashMap<String,Specie> _species = new HashMap<String,Specie>(); 
  private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>(); 
  private HashMap<String,Employee> _employees = new HashMap<String,Employee>(); 
  private HashMap<String,Vaccine> _Vaccines = new HashMap<String,Vaccine>(); 
  private HashMap<String,Tree> _trees = new HashMap<String,Tree>();

  @Serial
  private static final long serialVersionUID = 1L;

  private Season _season; 

  private boolean _state;

  public Hotel() {

    _season = Season.Primavera;
    _state = false;

  }

  public void registerAnimal(String idAni, String nameAni, String idSpc, String idHabi) throws SpeciesNotKnownException, HabitatNotKnownException {

    Specie specie = getSpecieById(idSpc); 
    Habitat habitat = getHabitatById(idHabi); 
    Animal animal = new Animal(this, idAni, nameAni, specie, habitat);    
    _animals.put(idAni,animal); 
    this.setState(true);

  }

  public void registerSpecie(String idSpc, String nameSpc) throws SpeciesAlreadyThereException {    
  
    if (containsKeyIgnoreCase(_species,idSpc)) {
      throw new SpeciesAlreadyThereException(idSpc);
    }

    Specie specie = new Specie(this, idSpc, nameSpc);       
    _species.put(idSpc,specie);
    this.setState(true);

  }

  public void registerVaccine(String idVac, String nameVac, String[] species) throws SpeciesNotKnownException {

    List<Specie> speciesSet = new ArrayList<Specie>();
    for (String idSpc : species) {
      Specie specie = getSpecieById(idSpc);
      speciesSet.add(specie);
    }

    Vaccine Vaccine = new Vaccine(this, idVac, nameVac, speciesSet);
    _Vaccines.put(idVac,Vaccine);
    this.setState(true);  

  }

  public void registerTree(String idHabi, String idTree, String nameTree, int age, int diff, String Type) throws HabitatNotKnownException, TreeAlreadyThereException{

    Habitat habitat = getHabitatById(idHabi);
      
    if (containsKeyIgnoreCase(_trees,idTree)) {
      throw new TreeAlreadyThereException(idTree);
    }
      
    if (Type == "PERENE") {
      EvergreenTree tree = new EvergreenTree(habitat, idTree, nameTree, diff, season());
      _trees.put(idTree,tree);
    } else {
      DeciduousTree tree = new DeciduousTree(habitat, idTree, nameTree, diff, season());
      _trees.put(idTree,tree);
    }

    this.setState(true);  
  }

  public void newResponsability(String idEmp, String idSomething) throws EmployeeNotKnownException, ResponsabilityNotThereException {
    
    if (!containsKeyIgnoreCase(_employees,idEmp)) {
      throw new EmployeeNotKnownException(idEmp);
    } 

    Employee employee = getEmployeeById(idEmp);
    employee.addResponsibility(idSomething); 

    this.setState(true);
  }

  public Habitat registerHabitat(String idHabi, String nameHabi, int area) throws HabitatAlreadyThereException {
            
    if(containsKeyIgnoreCase(_habitats,idHabi)) {
      throw new HabitatAlreadyThereException(idHabi);
    }

    Habitat habitat = new Habitat(this, idHabi, nameHabi, area);
    _habitats.put(idHabi, habitat);
    this.setState(true);
    return habitat;
  }

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

  private boolean containsKeyIgnoreCase(Map<String, ?> map, String key) {
    for (String k : map.keySet()) {
        if (k.equalsIgnoreCase(key)) {
            return true;
        }
    }
    return false;
  }

  private <T, E extends Throwable> T getById(Map<String, T> map, String id, E exception) throws E {
    for (String key : map.keySet()) {
        if (key.equalsIgnoreCase(id)) {
            return map.get(key);
        }
    }
    throw exception;
  }

  public Season season() {
    return _season;
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
    return getAllEntities(_Vaccines);
  }

  public <T extends Identifier> List<T> getAllEntities(Map<String, T> entityMap) {
    List<T> entityList = new ArrayList<>(entityMap.values());
    entityList.sort(Comparator.comparing(T::id, String.CASE_INSENSITIVE_ORDER));
    return Collections.unmodifiableList(entityList);
  }

  public void setState(Boolean state) {
    _state = state;
  }

  public boolean getState() {
    return _state;
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
