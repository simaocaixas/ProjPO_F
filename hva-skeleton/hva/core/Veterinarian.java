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

    @Override
    protected String empToString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
