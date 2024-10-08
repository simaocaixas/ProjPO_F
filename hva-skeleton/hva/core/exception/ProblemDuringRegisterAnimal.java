package hva.core.exception;

public class ProblemDuringRegisterAnimal extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public ProblemDuringRegisterAnimal(String idAnimal) {
    super(idAnimal);
  }

  public ProblemDuringRegisterAnimal() {
    super();
  }

  public ProblemDuringRegisterAnimal(Throwable cause) {
    super(cause);
  }
    
}
