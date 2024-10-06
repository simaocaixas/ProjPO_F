package hva.core;
import java.util.*;

import hva.app.exception.*;

public class Veterinarian extends Emplooye {

    private HashMap<String,Specie> _species = new HashMap<String,Specie>();

    public Veterinarian(Hotel hotel, String idEmp, String nameEmp) {
        super(hotel, idEmp, nameEmp);
    }

    @Override
    protected void newResponsability(String idSpc) throws NoResponsibilityException {
        try {
            Hotel hotel = hotel();
            Specie specie = hotel.getSpecieById(idSpc);
            _species.put(idSpc, specie);
        } catch (UnknownSpeciesKeyException e) {
            throw new NoResponsibilityException(idEmp(),idSpc);
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

    @Override
    public String empToString() {
        return "VET" + super.empToString() + (_species.size() == 0 ? "" : speciesIdsToString());
    }
    
}
