package hva.core;

import java.util.*;
/**
 * Represents a vaccine that can be administered to specific species of animals.
 * The Vaccine class holds information about the vaccine's ID, name, associated
 * species, and registers of vaccinations.
 */
public class Vaccine extends Identifier {

    private List<Specie> _species = new ArrayList<Specie>();
    private List<Register> _registers = new ArrayList<>();

    /**
     * Constructs a Vaccine object with the specified hotel, ID, name, and list of species.
     *
     * @param hotel       the hotel associated with the vaccine
     * @param idVaccine   the unique identifier for the vaccine
     * @param vaccineName the name of the vaccine
     * @param species     the list of species that can receive this vaccine
     */
    Vaccine(Hotel hotel, String idVaccine, String vaccineName, List<Specie> species) {
        super(idVaccine, vaccineName, hotel);
        _species = species;
    }

    /**
     * Returns a sorted string representation of the IDs of the species associated with the vaccine.
     * The species IDs are separated by commas and prefixed by a pipe symbol.
     *
     * @return a string containing the sorted species IDs
     */
    String speciedIdSorted() {
        StringBuilder sb = new StringBuilder();
        String prefix = "|";

        _species.sort(Comparator.comparing(specie -> specie.id()));
        for (Specie specie : _species) {
            sb.append(prefix);
            sb.append(specie.id());
            prefix = ",";
        }
        return sb.toString();
    }

    /**
     * Checks if the specified species is associated with this vaccine.
     *
     * @param specie the species to check
     * @return true if the species is associated with the vaccine, false otherwise
     */
    public boolean hasSpecie(Specie specie) {
        return _species.contains(specie);
    }

    /**
     * Calculates the damage score based on the name of the specified species compared
     * to the species associated with the vaccine. If no species are associated with the
     * vaccine, it returns the length of the species' name.
     *
     * @param specie the species to calculate the damage score for
     * @return the maximum name difference score
     */
    int calculateDamage(Specie specie) {
        int differ, majorDiffer = 0;

        if (_species.isEmpty()) {
            return specie.name().length();
        }

        for (Specie s : _species) {
            differ = calculateNameDifference(specie.name(), s.name());
            if (differ > majorDiffer) {
                majorDiffer = differ;
            }
        }

        return majorDiffer;
    }

    /**
     * Calculates the name difference between two given names based on the number
     * of matching characters. The difference is determined by the length of the
     * longer name minus the number of matching characters.
     *
     * @param name   the first name
     * @param name2  the second name
     * @return the calculated name difference
     */
    private int calculateNameDifference(String name, String name2) {
        char[] str = name.toCharArray();
        char[] str2 = name2.toCharArray();
        int count = 0, biggerName;

        for (char c : str) {
            for (int i = 0; i < str2.length; i++) {
                if (c == str2[i]) {
                    str2[i] = 0; // Mark this character as matched
                    count++;
                }
            }
        }
        biggerName = name.length() > name2.length() ? name.length() : name2.length();

        return biggerName - count;
    }

    /**
     * Adds a vaccination register to the list of registers associated with this vaccine.
     *
     * @param register the register to add
     */
    void addRegister(Register register) {
        _registers.add(register);
    }

    /**
     * Returns a string representation of the Vaccine object, including its ID,
     * name, the number of registers, and the sorted IDs of associated species.
     * The format is: VACINA|super.toString()|size of registers|species list sorted
     *
     * @return a string representing the vaccine
     */
    public String toString() {
        return "VACINA" + "|" + super.toString() + "|" + _registers.size() + speciedIdSorted();
    }
}

