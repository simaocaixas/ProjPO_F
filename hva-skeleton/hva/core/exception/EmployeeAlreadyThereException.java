package hva.core.exception;

public class EmployeeAlreadyThereException extends Exception {

  public EmployeeAlreadyThereException(String idEmp) {
    super("Funcionario já registado" + idEmp);
  }
    
}
