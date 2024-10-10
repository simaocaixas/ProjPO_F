package hva.core;
import hva.core.exception.*;

public abstract class Employee extends Identifier {
    
    protected Employee(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    public abstract void addResponsibility(String id) throws ResponsabilityNotThereException;

    public abstract void removeResponsibility(String id) throws ResponsabilityNotThereException;

    public String toString() {
        return "|" + super.toString();
    }

}
