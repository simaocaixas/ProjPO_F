package hva.core;
import java.util.*;

public class Animal {

    private String _idANi; 
    private String _nomeAni;

    private Hotel _hotel;
    
    private List<Register> _registers = new ArrayList<Register>();
    private Specie _specie;  
    private Habitat _habitat; 

    public Animal(Hotel hotel, String idAni, String nomeAni, Specie specie, Habitat habitat) {
        _hotel = hotel;
        _idANi = idAni;
        _nomeAni = nomeAni;
        _specie = specie;
        _habitat = habitat;
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
    }

    protected String idAni() {
        return _idANi;
    }

    protected String healthState() {
        for (Register register : _registers) {
                return register.toString();
        }  return null;
    }
    
    public String aniToString() {
        return "ANIMAL"+ "|" + _idANi + "|" + _nomeAni + "|" + _specie.idSpc() + "|" + (healthState() == null ? "VOID" : healthState()) + "|" + _habitat.idHabi();
    }
}