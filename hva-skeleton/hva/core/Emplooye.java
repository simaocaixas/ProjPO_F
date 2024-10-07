package hva.core;
import hva.app.exception.*;
import hva.core.exception.*;

public abstract class Emplooye {
    
    private String _idEmp;
    private String _namString;
    private Hotel _hotel;

    protected Emplooye(Hotel hotel, String idEmp, String nameEmp) {
        _idEmp = idEmp;
        _namString = nameEmp;
        
        _hotel = hotel;
    }


    abstract protected void newResponsability(String idSomething) throws HabitatNotKnown, SpeciesNotKnown;

    abstract protected String getType();

    public String empToString() {
        return "|" + idEmp() + "|" + nameEmp() + "|";
    }

    protected Hotel hotel() {
        return _hotel;
    }

    protected String idEmp() {
        return _idEmp;
    }

    protected String nameEmp() {
        return _namString;
    }


}
