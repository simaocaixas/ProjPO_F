package hva.core;
import hva.app.exception.*;
import hva.core.exception.*;

public abstract class Employee extends Identifier implements Responsibility {
    
    protected Employee(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    @Override
    public abstract void addResponsibility(String id) throws ResponsabilityNotThere;

    @Override
    public abstract void removeResponsibility(String id) throws ResponsabilityNotThere;

    public String empToString() {
        return "|" + super.toString();
    }

}
