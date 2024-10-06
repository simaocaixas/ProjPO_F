package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.*;

import hva.core.exception.ImportFileException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import hva.core.exception.UnrecognizedEntryException;

// FIXME import classes

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel = new Hotel();
  private String _fileName = null;
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    // FIXME implement serialization method
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException { // AQUI
    _fileName = filename;
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(_fileName + ".txt"));
      oos.writeObject(_hotel);
      oos.close(); 
    } catch (IOException e) {
      System.err.println("Exceção Lançada: IOException.");
    }
  }
  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException { // AQUI
    try {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename + ".txt"));
      _hotel = (Hotel) ois.readObject(); 
      ois.close();
    } catch (IOException e) {
        System.err.println("Lançada Exceção: IOException.");
    } catch (ClassNotFoundException e) {
      System.err.println("Lançada Exceção: ClassNotFoundException.");
    }
  }
  
  /**
   * Read text input file and initializes the current zoo hotel (which should be empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  } 

  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  public String getFileName() {
    return _fileName;
  }
}
