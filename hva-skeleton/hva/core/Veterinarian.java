package hva.core;
import java.util.*;

import hva.app.exception.*;
import hva.core.exception.ResponsabilityNotThere;
import hva.core.exception.SpeciesNotKnown;

public class Veterinarian extends Emplooye {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();

    public Veterinarian(Hotel hotel, String idEmp, String nameEmp) {
        super(hotel, idEmp, nameEmp);
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

    protected String getType() {
        return "VET";
    }

    @Override
    public String empToString() {
        return "VET" + super.empToString() + (_species.size() == 0 ? "" : speciesIdsToString());
    }
    
}
