package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  /** The current zoo hotel */ // Should we initialize this field?
  private Hotel _hotel = new Hotel();
  private String _fileName = "";
  
  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
      try {
        saveAs(_fileName);
      } catch (IOException ece) {
        throw new IOException(_fileName);
      }
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
  public void saveAs(String filename)  throws FileNotFoundException, MissingFileAssociationException, IOException { // AQUI
    _fileName = filename;

    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(_fileName));
      oos.writeObject(_hotel);
      oos.close(); 
    } catch (IOException ece) {
      throw new IOException(_fileName);
    } 
  }


  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try {
      FileInputStream file = new FileInputStream(filename);
      ObjectInputStream objIn = new ObjectInputStream(file);
      _hotel = (Hotel) objIn.readObject();
      objIn.close();
      _hotel.setState(false);
    } catch (IOException e) {
        throw new UnavailableFileException(filename);    // REVER ISTO REVER ISTO REVER ISTO REVER ISTO 
    } catch (ClassNotFoundException e) {
        throw new UnavailableFileException(filename);   // REVER ISTO REVER ISTO REVER ISTOREVER ISTOREVER ISTO
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

  public void newHotel() {
    _hotel = new Hotel();
    _fileName = "";
  }

  public boolean hasUnsavedChanges() {
    return _hotel.getState();
  }
  
  public void setHotelState(boolean bool) {

    _hotel.setState(bool);
  }

  public void FileNameCheck() throws FileNameAlreadyExistsExceptionCore {
    if (_fileName.length() != 0) {
      throw new FileNameAlreadyExistsExceptionCore();
    } 
  }

  public String getFileName() {
    return _fileName;
  }
}
