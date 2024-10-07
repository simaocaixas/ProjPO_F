package hva.core.exception;

public class EmployeeNotKnown extends Exception {
    
    private static final long serialVersionUID = 202407081733L;
    
    public EmployeeNotKnown(String idEmp) {
        super(idEmp);
    }
    
    public EmployeeNotKnown(Throwable cause) {
        super(cause);
    }
}



