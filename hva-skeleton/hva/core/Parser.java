package hva.core;

import hva.core.exception.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

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

    public void parseFile(String filename) throws IOException, UnrecognizedEntryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split("\\|");
        switch (components[0]) {
            case "ESPÉCIE" -> parseSpecies(components);
            case "ANIMAL" -> parseAnimal(components);
            case "HABITAT" -> parseHabitat(components);
            case "VETERINÁRIO" -> parseEmployee(components, "VET");
            case "TRATADOR" -> parseEmployee(components, "TRT");
            case "VACINA" -> parseVaccine(components);
            default -> throw new UnrecognizedEntryException("tipo de entrada inválido: " + components[0]);
        }
    }

    // Parse a line with format ANIMAL|id|nome|idEspécie|idHabitat
    private void parseAnimal(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            String speciesId = components[3];
            String habitatId = components[4];

            _hotel.registerAnimal(id, name, speciesId, habitatId);
        } catch (SpeciesNotKnownException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        } catch (HabitatNotKnownException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    private void parseSpecies(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            _hotel.registerSpecie(id, name);
        } catch (SpeciesAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    private void parseEmployee(String[] components, String empType) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];

            _hotel.registerEmployee(id, name, empType);

            if (components.length == 4) {
                for (String responsibility : components[3].split(","))
                    _hotel.newResponsability(components[1], responsibility);
            }
        } catch (EmployeeNotKnownException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        } catch (EmployeeAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        } catch (ResponsabilityNotThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    // Parse a line with format VACINA|id|nome|idEspécie1,...,idEspécieN
    private void parseVaccine(String[] components) throws UnrecognizedEntryException{
        try {
        String id = components[1];
        String name = components[2];
        String[] speciesIds = components.length == 4 ? components[3].split(",") : new String[0];
        _hotel.registerVaccine(id, name, speciesIds);
        } catch (SpeciesNotKnownException e) {
        throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }

    private void parseHabitat(String[] components) throws UnrecognizedEntryException {
        try {
            String id = components[1];
            String name = components[2];
            int area = Integer.parseInt(components[3]);

            Habitat hab = _hotel.registerHabitat(id, name, area);

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
        } catch (HabitatAlreadyThereException e) {
            throw new UnrecognizedEntryException("Invalid entry: " + e.getMessage());
        }
    }
}

/**
 * Nota: O bloco catch presente nos vários métodos parse desta classe deverá ter em conta
 * as várias excepções que podem acontecer no contexto do bloco try em questão.
 * Estas excepções do domínio têm que ser definidas por cada grupo e devem representar situações
 * de erro específicas do domínio.
 **/