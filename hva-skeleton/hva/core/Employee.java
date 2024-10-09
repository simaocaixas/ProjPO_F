package hva.core;
import java.io.Serializable;
import hva.app.exception.*;
import hva.core.exception.*;

public abstract class Employee extends Identifier {
    
    protected Employee(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    public abstract void addResponsibility(String id) throws ResponsabilityNotThere;

    public abstract void removeResponsibility(String id) throws ResponsabilityNotThere;

    public String toString() {
        return "|" + super.toString();
    }

}
