package hva.core;

import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.*;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  // FIXME define attributes  
  HashMap<String,Animal> _animals = new HashMap<String,Animal>(); 
  HashMap<String,Specie> _species = new HashMap<String,Specie>(); 
  HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>(); 

  // FIXME define contructor(s)
  // FIXME define more methods

  public void registerAnimal(String idAni, String nomeAni, String idSpc, String idHabi) {
    Specie specie = getSpecieById(idSpc); 
    Habitat habitat = getHabitatById(idHabi); 
    Animal animal = new Animal(this, idAni, nomeAni, specie, habitat);    
    _animals.put(idAni,animal); 
  }
  public void registerSpicie(String idSpc, String nomeSpc) {    
    Specie specie = new Specie(this, idSpc, nomeSpc);       
    _species.put(idSpc,specie); 
  }

  public Specie getSpecieById(String idSpc) throws UnknownSpeciesKeyException {

    if (!_species.containsKey(idSpc)) {
      throw new UnknownSpeciesKeyException(idSpc);
    } else {
      return _species.get(idSpc);
    }

  }

  public Habitat getHabitatById(String idHabi) {
    return _habitats.get(idHabi);
  }

  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}
