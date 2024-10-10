package hva.core.exception;

public class SpeciesNotKnownException extends Exception {

  String _idSpc;

  public SpeciesNotKnownException(String idSpc) {
    super("Espécie não registada" + idSpc);
    _idSpc = idSpc;
  }

  public String idSpc() {
    return _idSpc;
  }

  public SpeciesNotKnownException(Throwable cause) {
    super(cause);
  }

}
