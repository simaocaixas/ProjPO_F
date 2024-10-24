package hva.core;
import java.util.*;

import hva.core.Register;
import hva.core.exception.*;

public class Veterinarian extends Employee {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();
    private SatisfactionStrategy _satisfactionStrategy = new VetSatsifactionStrategy(this);
    private List<Register> _registers = new ArrayList<Register>();

    public Veterinarian(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    public void addResponsibility(String idSpc) throws ResponsabilityNotThereException {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
            specie.addVet(this);
        } catch (SpeciesNotKnownException ece) {
            throw new ResponsabilityNotThereException(idSpc);
        }
    }

    public void removeResponsibility(String idSpc) throws ResponsabilityNotThereException {
        if (_species.containsKey(idSpc)) {
            _species.remove(idSpc);
        } else {
            throw new ResponsabilityNotThereException(idSpc);
        }
    }

    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction();
    }

    protected Set<String> getSpeciesIds() {
        return Collections.unmodifiableSet(_species.keySet());
    }

    public Collection<Specie> getSpecies() {
        return Collections.unmodifiableCollection(_species.values());
    }

    protected String speciesIdsToString() {
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

    public void vaccinateAnimal(Animal animal, Vaccine vaccine) throws VetNotAuthorizedException {
   
        if(!_species.containsKey(animal.specie().id())) {
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
    
    public Damage getDamageFromInt(int dmgInt) {
        
        Damage dmg;
        
        if (dmgInt == 0) {
            dmg = Damage.CONFUSÃO;
        } else if(dmgInt >= 1 && dmgInt <= 4) {
            dmg = Damage.ACIDENTE;
        } else {
            dmg = Damage.ERRO;
        }
        
        return dmg;
    }

    public List<Register> getAllRegisters() {
        return Collections.unmodifiableList(_registers);
    }

    @Override
    public String toString() {
        return "VET" + super.toString() + (_species.size() == 0 ? "" : "|" + speciesIdsToString());
    }
    
}
