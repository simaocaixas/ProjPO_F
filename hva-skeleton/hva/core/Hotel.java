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
  HashMap<String,Vaccine> _Vaccines = new HashMap<String,Vaccine>(); 
  HashMap<String,Tree> _trees = new HashMap<String,Tree>(); 

  // FIXME define contructor(s)
  // FIXME define more methods

  @Serial
  private static final long serialVersionUID = 1L;

  private Season _season = Season.Primavera; 

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

  public void registerVaccine(String idVac, String nameVac, String[] species) throws SpeciesNotKnown {
      
      List<Specie> speciesSet = new ArrayList<Specie>();
      for (String idSpc : species) {
        Specie specie = getSpecieById(idSpc);
        speciesSet.add(specie);
      }

      Vaccine Vaccine = new Vaccine(this, idVac, nameVac, speciesSet);
      _Vaccines.put(idVac,Vaccine);
    }

  public void registerTree(String idHabi, String idTree, String nameTree, int age, int diff, String Type) throws HabitatNotKnown, TreeAlreadyThere{

      Habitat habitat = getHabitatById(idHabi);
      
      if (_trees.containsKey(idTree)) {
        throw new TreeAlreadyThere(idTree);
      }
      
      if (Type == "PERENE") {
        EvergreenTree tree = new EvergreenTree(habitat, idTree, nameTree, diff, season());
        _trees.put(idTree,tree);
      } else {
        DeciduousTree tree = new DeciduousTree(habitat, idTree, nameTree, diff, season());
        _trees.put(idTree,tree);
      }
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

  public Season season() {
    return _season;
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

  public List<Habitat> getAllHabitats() {
    List<Habitat> habitatsList = new ArrayList<>(_habitats.values());
    habitatsList.sort(Comparator.comparing(habitat -> habitat.id()));
    return Collections.unmodifiableList(habitatsList);
  }

  public List<Vaccine> getAllVaccines() {
    List<Vaccine> VaccineList = new ArrayList<>(_Vaccines.values());
    VaccineList.sort(Comparator.comparing(Vaccine -> Vaccine.id()));
    return Collections.unmodifiableList(VaccineList);
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
