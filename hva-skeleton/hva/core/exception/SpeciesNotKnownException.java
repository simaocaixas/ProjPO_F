package hva.core.exception;

public class SpeciesNotKnownException extends Exception {

  private String _idSpc;

  public SpeciesNotKnownException(String idSpc) {
    super("Espécie não registada" + idSpc);
    _idSpc = idSpc;
  }

  public String idSpc() {
    return _idSpc;
  }


}
