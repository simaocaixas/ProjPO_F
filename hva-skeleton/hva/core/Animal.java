package hva.core;
import java.util.*;

public class Animal extends Identifier{

    private Hotel _hotel;
    
    private List<Register> _registers = new ArrayList<Register>();
    private Specie _specie;  
    private Habitat _habitat; 

    public Animal(Hotel hotel, String idAni, String nomeAni, Specie specie, Habitat habitat) {
        super(idAni, nomeAni);
        _hotel = hotel;
        _specie = specie;
        _habitat = habitat;
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
    }

    protected String healthState() {
        for (Register register : _registers) {
                return register.toString();
        }  return null;
    }
    
    public String aniToString() {
        return "ANIMAL"+ "|" + super.id() + "|" + super.name() + "|" + _specie.id() + "|" + (healthState() == null ? "VOID" : healthState()) + "|" + _habitat.id();
    }
}