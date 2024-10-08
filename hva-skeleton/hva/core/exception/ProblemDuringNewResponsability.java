package hva.core.exception;

public class ProblemDuringNewResponsability extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public ProblemDuringNewResponsability(String idResp) {
    super(idResp);
  }

  public ProblemDuringNewResponsability(Throwable cause) {
    super(cause);
  }
    
}
