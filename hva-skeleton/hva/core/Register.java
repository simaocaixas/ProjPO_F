package hva.core;
/**
 * Represents a medical register for an animal, including details about any damage,
 * the veterinarian who treated the animal, the vaccine administered, and the animal itself.
 */
public class Register { 
    
    private Damage _damage;
    private Veterinarian _veterinarian;
    private Vaccine _vaccine;
    private Animal _animal;

    /**
     * Constructs a Register object with the specified damage, veterinarian, vaccine, and animal.
     *
     * @param damage      the damage associated with the register
     * @param veterinarian the veterinarian who treated the animal
     * @param vaccine     the vaccine administered to the animal
     * @param animal      the animal to which this register pertains
     */
    Register(Damage damage, Veterinarian veterinarian, Vaccine vaccine, Animal animal) {
        _damage = damage;
        _veterinarian = veterinarian;
        _vaccine = vaccine;
        _animal = animal;
    }

    /**
     * Returns the damage associated with the register.
     *
     * @return the damage
     */
    public Damage damage() {
        return _damage;
    }

    /**
     * Returns a string representation of the register, formatted as:
     * REGISTO-VACINA|vaccine ID|veterinarian ID|animal species ID.
     *
     * @return a string representation of the register
     */
    public String toString() {
        return "REGISTO-VACINA" + "|" + _vaccine.id() + "|" + _veterinarian.id() + "|" + _animal.specie().id();
    }
}
