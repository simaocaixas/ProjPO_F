package hva.core.exception;

public class EmployeeAlreadyThere extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public EmployeeAlreadyThere(String idEmp) {
    super(idEmp);
  }

  public EmployeeAlreadyThere(Throwable cause) {
    super(cause);
  }
    
}
