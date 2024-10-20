package hva.core;
import java.util.*;
import hva.core.exception.*;

public class Veterinarian extends Employee {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();

    public Veterinarian(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    public void addResponsibility(String idSpc) throws ResponsabilityNotThereException {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
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

    Set<String> getSpeciesIds() {
        return Collections.unmodifiableSet(_species.keySet());
    }

    String speciesIdsToString() {
        StringBuilder sb = new StringBuilder();
        for (String idSpc : _species.keySet()) {
            sb.append(idSpc).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public String toString() {
        return "VET" + super.toString() + (_species.size() == 0 ? "" : "|" + speciesIdsToString());
    }
    
}
