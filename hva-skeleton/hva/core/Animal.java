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

    public void transfer(Habitat habitat) {
        _habitat.removeAnimal(this);
        _habitat = habitat;
        _habitat.addAnimal(this);
    }

    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction(); // Usa a estratégia para calcular a satisfação
    }

    public String healthState() {
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
    
    public String toString() {
        return "ANIMAL"+ "|" + super.toString() + "|" + _specie.id() + "|" + (healthState().length() == 0 ? "VOID" : healthState()) + "|" + _habitat.id();
    }

    public Specie specie() {
        return _specie;
    }

    public Habitat habitat() {
        return _habitat;
    }

    public void addRegister(Register register) {
        _registers.add(register);
    }

    public List<Register> getAllRegisters() {
        return Collections.unmodifiableList(_registers);
    }
}