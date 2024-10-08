package hva.core;
import java.io.Serializable;
import java.util.*;

import hva.app.exception.*;
import hva.core.exception.*;

public class Veterinarian extends Employee {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();

    public Veterinarian(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    @Override
    public void addResponsibility(String idSpc) throws ResponsabilityNotThere {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
        } catch (SpeciesNotKnown ece) {
            throw new ResponsabilityNotThere(idSpc);
        }
    }

    @Override
    public void removeResponsibility(String idSpc) throws ResponsabilityNotThere {
        if (_species.containsKey(idSpc)) {
            _species.remove(idSpc);
        } else {
            throw new ResponsabilityNotThere(idSpc);
        }
    }

    protected Set<String> getSpeciesIds() {
        return Collections.unmodifiableSet(_species.keySet());
    }

    protected String speciesIdsToString() {
        StringBuilder sb = new StringBuilder();
        for (String idSpc : _species.keySet()) {
            sb.append(idSpc).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public String empToString() {
        return "VET" + super.empToString() + (_species.size() == 0 ? "" : speciesIdsToString());
    }
    
}
