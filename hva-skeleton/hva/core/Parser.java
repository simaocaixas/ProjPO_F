package hva.core;

import java.io.*;
import hva.core.exception.*;

/**
 * Esta solução assume que a classe Hotel já tem a seguinte funcionalidade

public class Hotel {
  public void registerAnimal(animalId, name, habitatId, speciesId) throws OneOrMoreCoreExceptions { ... }
  * public void registerSpecies(speciesId, name) throws OneOrMoreCoreExceptions { ... }
  * public void registerEmployee(employeeId, name, empType) throws OneOrMoreCoreExceptions { ... }
  * public void addResponsibility(employeeId, responsibility) throws OneOrMoreCoreExceptions { ... }
  * public void registerVaccine(vaccineId, name, String[] speciesIds) throws someCoreExceptionsOneOrMoreCoreExceptions { ... }
  * public void createTree(TreeId, name, type, age, diff) throws OneOrMoreCoreExceptions { ... }
  * public Habitat registerHabitat(habitatId, name, area) throws OneOrMoreCoreExceptions { ... }

Note-se que esta funcionalidade pode ser utilizada na concretização de alguns dos comandos.
Caso Hotel não tenha esta funcionalidade, então deverão substituir a invocação destes métodos
na classe Parser por uma ou mais linhas com uma funcionalidade semelhante.
Cada um destes métodos pode lançar uma ou mais excepções que irão corresponder aos erros que
podem acontecer ao nível do domínio surante a concretização da funcionalidade em causa.
**/

public class Parser {
    private Hotel _hotel;

    Parser(Hotel h) {
        _hotel = h;
    }

    /**
     * Parses a file containing data entries for species, animals, habitats, employees, vaccines, and trees.
     * Each line in the file should represent a specific entity and is parsed based on the type of entry.
     * Supported entries include species, animals, habitats, veterinarians, zookeepers, vaccines, and trees.
     * 
     * @param filename the name of the file to parse
     * @throws IOException if an I/O error occurs when reading the file
     * @throws UnrecognizedEntryException if the file contains an unrecognized or malformed entry
     */
    public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }

        _hotel.setState(true);
    }

    /**
     * Parses a single line from the file and delegates the parsing to the appropriate handler
     * based on the type of entry (e.g., species, animal, habitat, etc.).
     * 
     * @param line the line to be parsed
     * @throws UnrecognizedEntryException if the entry type is not recognized
     */
    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");
        switch (components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            case "ANIMAL" -> parseAnimal(components);
            case "HABITAT" -> parseHabitat(components);
            case "VETERINÁRIO" -> parseEmployee(components, "VET");
            case "TRATADOR" -> parseEmployee(components, "TRT");
            case "VACINA" -> parseVaccine(components);
            case "ÁRVORE" -> parseTree(components);
            default -> throw new UnrecognizedEntryException("tipo de entrada inválido: " + components[0]);
        }
    }

    /**
     * Parses an animal entry from the components array. The format is: ANIMAL|id|name|speciesId|habitatId.
     * 
     * @param components the components of the entry to parse
     * @throws UnrecognizedEntryException if there is an issue with the entry format or data
     */
    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String speciesId = components[3];
            String habitatId = components[4];

            _hotel.registerAnimal(id, name, speciesId, habitatId);
        } catch (SpeciesNotKnownException | HabitatNotKnownException | AnimalAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    /**
     * Parses a species entry from the components array. The format is: ESPÉCIE|id|name.
     * 
     * @param components the components of the entry to parse
     * @throws UnrecognizedEntryException if the species already exists or if the entry is malformed
     */
    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            _hotel.registerSpecie(id, name);
        } catch (SpeciesAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    /**
     * Parses an employee entry from the components array. The format is: 
     * EMPLOYEE_TYPE|id|name|responsibility1,responsibility2,... (optional).
     * 
     * @param components the components of the entry to parse
     * @param empType the type of employee (e.g., "VET" for veterinarian, "TRT" for caretaker)
     * @throws UnrecognizedEntryException if there is an issue with the entry format or data
     */
    private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerEmployee(id, name, empType);

            if (components.length == 4) {
                for (String responsibility : components[3].split(","))
                    _hotel.newResponsability(components[1], responsibility);
            }
        } catch (EmployeeNotKnownException | EmployeeAlreadyThereException | ResponsabilityNotThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    /**
     * Parses a vaccine entry from the components array. The format is: 
     * VACINA|id|name|idSpecies1,...,idSpeciesN.
     * 
     * @param components the components of the entry to parse
     * @throws UnrecognizedEntryException if there is an issue with the entry format or if any species ID is not known
     */
    private void parseVaccine(String[] components) throws UnrecognizedEntryException{
        
        try {
        String id = components[1];
        String name = components[2];
        String speciesIds = components.length == 4 ? components[3] : "";

        _hotel.registerVaccine(id, name, speciesIds);
        } catch (SpeciesNotKnownException e) {
        throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    /**
     * Parses a habitat entry from the components array. The format is: 
     * HABITAT|id|name|area|treeId1,treeId2,... (optional).
     * 
     * @param components the components of the entry to parse
     * @throws UnrecognizedEntryException if there is an issue with the entry format, or if the habitat already exists
     */
    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int area = Integer.parseInt(components[3]);

            _hotel.registerHabitat(id, name, area);
            Habitat hab = _hotel.getHabitatById(id);

            if (components.length == 5) {
                String[] listOfTree = components[4].split(",");
                for (String treeKey : listOfTree) {
                    try {
                        Tree tree = _hotel.getTreeById(treeKey);
                        hab.addTree(tree);
                    } catch (TreeNotKnownException e) {
                        throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
                    }
                }
            }
        } catch (HabitatAlreadyThereException | HabitatNotKnownException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        
        }
    }

    /**
     * Parses a tree entry from the components array. The format is:
     * ÁRVORE|id|name|age|difficulty|type.
     * 
     * @param components the components of the entry to parse
     * @throws UnrecognizedEntryException if there is an issue with the entry format or if the tree already exists
     */
    private void parseTree(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int age = Integer.parseInt(components[3]);
            int diff = Integer.parseInt(components[4]);
            String type = components[5];

            _hotel.createTree(id, name, age, diff, type);
        } catch (TreeAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

}

