package hva.core;

import java.util.*;

/**
 * Represents an animal that belongs to a species and resides in a habitat within a hotel.
 * Each animal is associated with a satisfaction strategy, which calculates the animal's
 * satisfaction based on various factors such as habitat suitability, population density, 
 * and species compatibility. Additionally, the animal maintains a list of registers 
 * that track its health state over time.
 * 
 * Key functionality includes transferring the animal between habitats, calculating its 
 * satisfaction level, and managing its health state records. The animal is uniquely 
 * identified by an ID and has a name associated with it.
 */
public class Animal extends Identifier{
    
    private List<Register> _registers = new ArrayList<Register>();
    private Specie _specie;  
    private Habitat _habitat; 
    private SatisfactionStrategy _satisfactionStrategy = new AnimalSatisfactionStrategy(this);

    /**
     * Constructs an Animal object with the specified hotel, ID, name, species, and habitat.
     * Adds the animal to the given species and habitat.
     *
     * @param hotel   the hotel where the animal resides
     * @param idAni   the unique identifier of the animal
     * @param nomeAni the name of the animal
     * @param specie  the species of the animal
     * @param habitat the habitat of the animal
     */
    Animal(Hotel hotel, String idAni, String nomeAni, Specie specie, Habitat habitat) {
        super(idAni, nomeAni, hotel);
        _specie = specie;
        _habitat = habitat;
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
    }

    /**
     * Transfers the animal to a new habitat.
     * Removes the animal from the current habitat and adds it to the new one.
     *
     * @param habitat the new habitat to transfer the animal to
    */
    void transfer(Habitat habitat) {
        _habitat.removeAnimal(this);
        _habitat = habitat;
        _habitat.addAnimal(this);
    }


    /**
     * Calculates the satisfaction level of the animal.
     *
     * @return the satisfaction level as a double value
     */
    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction();
    }


    /**
     * Retrieves the health state of the animal based on its registers.
     * Each register's damage is concatenated into a comma-separated string.
     *
     * @return a string representing the health state of the animal
     */
    private String healthState() {
        StringBuilder sb = new StringBuilder();

        for (Register register : _registers) {
            sb.append(register.damage());
            sb.append(",");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
    
    /**
     * Returns a string representation of the Animal object.
     * The format is: ANIMAL|[super.toString()]|[species ID]|[health state or VOID if empty]|[habitat ID].
     *
     * @return a string representing the animal
     */
    public String toString() {
        return "ANIMAL"+ "|" + super.toString() + "|" + _specie.id() + "|" + (healthState().length() == 0 ? "VOID" : healthState()) + "|" + _habitat.id();
    }

    /**
     * Gets the species of the animal.
     *
     * @return the species of the animal
     */
    public Specie specie() {
        return _specie;
    }

    /**
     * Gets the habitat of the animal.
     *
     * @return the habitat of the animal
     */
    Habitat habitat() {
        return _habitat;
    }

    /**
     * Adds a register to the list of registers for the animal.
     *
     * @param register the register to be added
     */
    void addRegister(Register register) {
        _registers.add(register);
    }

    /**
     * Returns an unmodifiable list of all registers for the animal.
     *
     * @return a list of registers
     */
    public List<Register> getAllRegisters() {
        return Collections.unmodifiableList(_registers);
    }
}