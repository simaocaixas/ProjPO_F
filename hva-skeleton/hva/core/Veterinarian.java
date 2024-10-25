package hva.core;

import java.util.*;
import hva.core.exception.*;


/**
 * Represents a veterinarian in the system who is responsible for managing species
 * and vaccinating animals. This class extends the Employee class and implements
 * functionalities related to species management and animal vaccination.
 */
public class Veterinarian extends Employee {

    private HashMap<String, Specie> _species = new HashMap<>();
    private SatisfactionStrategy _satisfactionStrategy = new VetSatisfactionStrategy(this);
    private List<Register> _registers = new ArrayList<>();

    /**
     * Constructs a Veterinarian with the specified ID, name, and hotel.
     *
     * @param idEmp the ID of the veterinarian
     * @param nameEmp the name of the veterinarian
     * @param hotel the hotel associated with the veterinarian
     */
    Veterinarian(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    /**
     * Adds a responsibility for managing a species identified by the given ID.
     * If the species does not exist, a ResponsabilityNotThereException is thrown.
     *
     * @param idSpc the ID of the species to manage
     * @throws ResponsabilityNotThereException if the species is not recognized
     */
    void addResponsibility(String idSpc) throws ResponsabilityNotThereException {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
            specie.addVet(this);
        } catch (SpeciesNotKnownException ece) {
            throw new ResponsabilityNotThereException(idSpc);
        }
    }

    /**
     * Removes the responsibility for managing a species identified by the given ID.
     * If the species is not managed by the veterinarian, a ResponsabilityNotThereException is thrown.
     *
     * @param idSpc the ID of the species to remove
     * @throws ResponsabilityNotThereException if the species is not managed by this veterinarian
     */
    void removeResponsibility(String idSpc) throws ResponsabilityNotThereException {
        if (_species.containsKey(idSpc)) {
            _species.remove(idSpc);
        } else {
            throw new ResponsabilityNotThereException(idSpc);
        }
    }

    /**
     * Calculates the satisfaction score of the veterinarian based on the species they manage.
     *
     * @return the calculated satisfaction score
     */
    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction();
    }

    /**
     * Gets the IDs of all species managed by this veterinarian.
     *
     * @return a set of species IDs
     */
    Set<String> getSpeciesIds() {
        return Collections.unmodifiableSet(_species.keySet());
    }

    /**
     * Gets the collection of species managed by this veterinarian.
     *
     * @return a collection of species
     */
    Collection<Specie> getSpecies() {
        return Collections.unmodifiableCollection(_species.values());
    }

    /**
     * Converts the IDs of species managed by this veterinarian to a comma-separated string.
     *
     * @return a string of species IDs
     */
    String speciesIdsToString() {
        StringBuilder sb = new StringBuilder();
        
        Set<String> sortedKeys = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        sortedKeys.addAll(_species.keySet());
        
        for (String idSpc : sortedKeys) {
            sb.append(idSpc).append(",");
        }
        
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        
        return sb.toString();
    }

    /**
     * Vaccinates the specified animal with the given vaccine.
     * If the veterinarian is not authorized to vaccinate the animal,
     * a VetNotAuthorizedException is thrown.
     *
     * @param animal the animal to vaccinate
     * @param vaccine the vaccine to use for vaccination
     * @throws VetNotAuthorizedException if the veterinarian is not authorized for the animal's species
     */
    void vaccinateAnimal(Animal animal, Vaccine vaccine) throws VetNotAuthorizedException {
        if (!_species.containsKey(animal.specie().id())) {
            throw new VetNotAuthorizedException(id(), animal.specie().id());
        }

        Hotel hotel = hotel();
        Damage dmg;

        if (vaccine.hasSpecie(animal.specie())) {
            dmg = Damage.NORMAL;
        } else {
            int dmgInt = vaccine.calculateDamage(animal.specie());
            dmg = getDamageFromInt(dmgInt);
        }

        Register register = new Register(dmg, this, vaccine, animal);
        hotel.addRegister(register);
        animal.addRegister(register);
        _registers.add(register);
        vaccine.addRegister(register);
    }

    /**
     * Gets the damage type based on the integer value representing damage severity.
     *
     * @param dmgInt the integer value representing damage severity
     * @return the corresponding Damage enum value
     */
    Damage getDamageFromInt(int dmgInt) {
        Damage dmg;
        
        if (dmgInt == 0) {
            dmg = Damage.CONFUSÃO;
        } else if (dmgInt >= 1 && dmgInt <= 4) {
            dmg = Damage.ACIDENTE;
        } else {
            dmg = Damage.ERRO;
        }
        
        return dmg;
    }

    /**
     * Gets all vaccination registers associated with this veterinarian.
     *
     * @return an unmodifiable list of registers
     */
    public List<Register> getAllRegisters() {
        return Collections.unmodifiableList(_registers);
    }

    /**
     * Returns a string representation of the veterinarian,
     * including their ID, name, and managed species.
     *
     * @return a string representation of the veterinarian
     */
    @Override
    public String toString() {
        return "VET" + super.toString() + (_species.size() == 0 ? "" : "|" + speciesIdsToString());
    }
}
