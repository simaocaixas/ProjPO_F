package hva.core.exception;

public class TreeNotKnownException extends Exception {

  private String _idTree;

  public TreeNotKnownException(String idTree) {
    super("Árvore não existe" + idTree);
    _idTree = idTree;
  }

  public String idTree() {
    return _idTree;
  }

  public TreeNotKnownException(Throwable cause) {
    super(cause);
  }
    
}
