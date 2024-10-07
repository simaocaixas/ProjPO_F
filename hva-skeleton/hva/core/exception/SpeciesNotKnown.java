package hva.core.exception;

public class SpeciesNotKnown extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public SpeciesNotKnown(String idSpc) {
    super(idSpc);
  }

  public SpeciesNotKnown(Throwable cause) {
    super(cause);
  }

}
