package hva.core;
import hva.app.exception.*;
import hva.core.exception.*;

public abstract class Emplooye extends Identifier implements Responsibility {
    
    private Hotel _hotel;

    protected Emplooye(Hotel hotel, String idEmp, String nameEmp) {
        super(idEmp, nameEmp);
        _hotel = hotel;
    }

    @Override
    public abstract void addResponsibility(String id) throws ResponsabilityNotThere;

    @Override
    public abstract void removeResponsibility(String id) throws ResponsabilityNotThere;

    abstract protected String getType();

    public String empToString() {
        return "|" + id() + "|" + name() + "|";
    }

    protected Hotel hotel() {
        return _hotel;
    }


}
