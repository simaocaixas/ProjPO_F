package hva.core;
import java.util.*;

import hva.app.exception.*;
import hva.core.exception.SpeciesNotKnown;

public class Veterinarian extends Emplooye {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();

    public Veterinarian(Hotel hotel, String idEmp, String nameEmp) {
        super(hotel, idEmp, nameEmp);
    }

    @Override
    protected void newResponsability(String idSpc) throws SpeciesNotKnown {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
        } catch (SpeciesNotKnown e) {
            throw new SpeciesNotKnown(idSpc);
        }
    }

    protected void removeResponsability(String idSpc) {
        _species.remove(idSpc);
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
