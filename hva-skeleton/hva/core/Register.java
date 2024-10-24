package hva.core;

public class Register { 
    
    private Damage _damage;
    private Veterinarian _veterinarian;
    private Vaccine _vaccine;
    private Animal _animal;

    public Register(Damage damage, Veterinarian veterinarian, Vaccine vaccine, Animal animal) {
        _damage = damage;
        _veterinarian = veterinarian;
        _vaccine = vaccine;
        _animal = animal;
    }

    public Damage damage() {
        return _damage;
    }

    public String toString() {
        return "REGISTO-VACINA" + "|" + _vaccine.id() + "|" + _veterinarian.id() + "|" + _animal.specie().id();
    }
}
