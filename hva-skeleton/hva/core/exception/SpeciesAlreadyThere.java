package hva.core.exception;

public class SpeciesAlreadyThere extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public SpeciesAlreadyThere(String idSpc) {
    super(idSpc);
  }

  public SpeciesAlreadyThere(Throwable cause) {
    super(cause);
  }
    
}
