package hva.core;
import java.util.*;

public class Animal extends Identifier{
    
    private List<Register> _registers = new ArrayList<Register>();
    private Specie _specie;  
    private Habitat _habitat; 

    public Animal(Hotel hotel, String idAni, String nomeAni, Specie specie, Habitat habitat) {
        super(idAni, nomeAni, hotel);
        _specie = specie;
        _habitat = habitat;
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
    }

    String healthState() {
        for (Register register : _registers) {
                return register.toString();
        }  return "";
    }
    
    public String toString() {
        return "ANIMAL"+ "|" + super.toString() + "|" + _specie.id() + "|" + (healthState().length() == 0 ? "VOID" : healthState()) + "|" + _habitat.id();
    }
}