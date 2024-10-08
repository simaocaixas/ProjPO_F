package hva.core;
import hva.app.exception.*;
import hva.core.exception.*;

public abstract class Emplooye implements Responsibility {
    
    private String _idEmp;
    private String _namString;
    private Hotel _hotel;

    protected Emplooye(Hotel hotel, String idEmp, String nameEmp) {
        _idEmp = idEmp;
        _namString = nameEmp;
        _hotel = hotel;
    }

    @Override
    public abstract void addResponsibility(String id) throws ResponsabilityNotThere;

    @Override
    public abstract void removeResponsibility(String id) throws ResponsabilityNotThere;

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
