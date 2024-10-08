package hva.core.exception;

public class SpeciesNotKnown extends Exception {
  private static final long serialVersionUID = 202407081733L;
  String _idSpc;

  public SpeciesNotKnown(String idSpc) {
    super(idSpc);
    _idSpc = idSpc;
  }

  public String idSpc() {
    return _idSpc;
  }

  public SpeciesNotKnown(Throwable cause) {
    super(cause);
  }

}
