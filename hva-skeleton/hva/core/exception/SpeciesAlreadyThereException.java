package hva.core.exception;

public class SpeciesAlreadyThereException extends Exception {

  private String _idSpc;

  public SpeciesAlreadyThereException(String idSpc) {
    super("Especie já registada" + idSpc);
    _idSpc = idSpc;
  }

  public String idSpc() {
    return _idSpc;
  }

  public SpeciesAlreadyThereException(Throwable cause) {
    super(cause);
  }
    
}
