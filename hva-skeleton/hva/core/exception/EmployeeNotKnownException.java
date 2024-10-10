package hva.core.exception;

public class EmployeeNotKnownException extends Exception {
        
    public EmployeeNotKnownException(String idEmp) {
        super("Funcionário não registado" + idEmp);
    }
    
    public EmployeeNotKnownException(Throwable cause) {
        super(cause);
    }
}



