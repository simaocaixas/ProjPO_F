package hva.core;
import java.util.*;

import hva.core.SatisfactionStrategy;

public class Animal extends Identifier{
    
    private List<Register> _registers = new ArrayList<Register>();
    private Specie _specie;  
    private Habitat _habitat; 
    private SatisfactionStrategy _satisfactionStrategy = new AnimalSatisfactionStrategy(this);

    public Animal(Hotel hotel, String idAni, String nomeAni, Specie specie, Habitat habitat) {
        super(idAni, nomeAni, hotel);
        _specie = specie;
        _habitat = habitat;
        _specie.addAnimal(this);
        _habitat.addAnimal(this);
    }

    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction(); // Usa a estratégia para calcular a satisfação
    }

    public String healthState() {
        for (Register register : _registers) {
                return register.toString();
        }  return "";
    }
    
    public String toString() {
        return "ANIMAL"+ "|" + super.toString() + "|" + _specie.id() + "|" + (healthState().length() == 0 ? "VOID" : healthState()) + "|" + _habitat.id();
    }

    protected Specie specie() {
        return _specie;
    }

    protected Habitat habitat() {
        return _habitat;
    }
}