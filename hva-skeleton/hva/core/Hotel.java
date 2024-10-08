package hva.core;

import hva.app.exception.*;
import hva.core.*;
import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {  

  // FIXME define attributes  
  HashMap<String,Animal> _animals = new HashMap<String,Animal>(); 
  HashMap<String,Specie> _species = new HashMap<String,Specie>(); 
  HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>(); 
  HashMap<String,Employee> _employees = new HashMap<String,Employee>(); 
  HashMap<String,Vacine> _vacines = new HashMap<String,Vacine>(); 
  HashMap<String,Tree> _trees = new HashMap<String,Tree>(); 

  // FIXME define contructor(s)
  // FIXME define more methods

  @Serial
  private static final long serialVersionUID = 1L;

  public void registerAnimal(String idAni, String nameAni, String idSpc, String idHabi) throws SpeciesNotKnown, HabitatNotKnown {

    Specie specie = getSpecieById(idSpc); 
    Habitat habitat = getHabitatById(idHabi); 
    Animal animal = new Animal(this, idAni, nameAni, specie, habitat);    
    _animals.put(idAni,animal); 

  }

  public void registerSpecie(String idSpc, String nameSpc) throws SpeciesAlreadyThere {    
    
    if (_species.containsKey(idSpc)) {
      throw new SpeciesAlreadyThere(idSpc);
    }

    Specie specie = new Specie(this, idSpc, nameSpc);       
    _species.put(idSpc,specie);

  }

  public void registerVacine(String idVac, String nameVac, Collection<String> species) throws SpeciesNotKnown {
      
      HashSet<Specie> speciesSet = new HashSet<Specie>();
      for (String idSpc : species) {
        Specie specie = getSpecieById(idSpc);
        speciesSet.add(specie);
      }

      Vacine vacine = new Vacine(this, idVac, nameVac, speciesSet);
      _vacines.put(idVac,vacine);
    }

  public void newResponsability(String idEmp, String idSomething) throws EmployeeNotKnown, ResponsabilityNotThere{
    
    if (!_employees.containsKey(idEmp)) {
      throw new EmployeeNotKnown(idEmp);
    } 

    Employee employee = getemployeeById(idEmp);
    employee.addResponsibility(idSomething); 
}

  public Habitat registerHabitat(String idHabi, String nameHabi, int area) throws HabitatAlreadyThere {
            
    // AQUI
    if(_habitats.containsKey(idHabi)) {
      throw new HabitatAlreadyThere(idHabi);
    }

    Habitat habitat = new Habitat(this, idHabi, nameHabi, area);
    _habitats.put(idHabi, habitat);
    return habitat;
}

  public void registerEmployee(String idEmp, String nameEmp, String type) throws EmployeeAlreadyThere {
    
    if (_employees.containsKey(idEmp)) {
      throw new EmployeeAlreadyThere(idEmp);
    } 

    if (type.equals("VET")) {
      Veterinarian veterinarian = new Veterinarian(idEmp, nameEmp, this);
      _employees.put(idEmp, veterinarian);
    } else {
      ZooKeeper zooKeeper = new ZooKeeper(idEmp, nameEmp,this);
      _employees.put(idEmp,zooKeeper);
    }
}

  public void changeHabitat(String idHabi, int area) throws HabitatNotKnown {
      Habitat habitat = getHabitatById(idHabi);
      habitat.changeHabitat(habitat, area);
  }
  
  public Specie getSpecieById(String idSpc) throws SpeciesNotKnown {

    if (!_species.containsKey(idSpc)) {
      throw new SpeciesNotKnown(idSpc);
    } else {
      return _species.get(idSpc);
    }
  }

  public Tree getTreeById(String idTree) throws TreeNotKnown {

    if (!_trees.containsKey(idTree)) {
      throw new TreeNotKnown(idTree);
    } else {
      return _trees.get(idTree);
    }
  }

  public Habitat getHabitatById(String idHabi) throws HabitatNotKnown {

    if (!_habitats.containsKey(idHabi)) {
      throw new HabitatNotKnown(idHabi);
    } else {
      return _habitats.get(idHabi);
    }
  }

  public Employee getemployeeById(String idEmp) throws EmployeeNotKnown {

    if (!_employees.containsKey(idEmp)) {
      throw new EmployeeNotKnown(idEmp);
    } else {
      return _employees.get(idEmp);
    }
  }

  public List<Animal> getAllAnimals() {
    List<Animal> animalList = new ArrayList<>(_animals.values());
    animalList.sort(Comparator.comparing(animal -> animal.id()));
    return Collections.unmodifiableList(animalList);
}

  public List<Employee> getAllEmployees() {
    List<Employee> employeeList = new ArrayList<>(_employees.values());
    employeeList.sort(Comparator.comparing(employeee -> employeee.id()));
    return Collections.unmodifiableList(employeeList);
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
