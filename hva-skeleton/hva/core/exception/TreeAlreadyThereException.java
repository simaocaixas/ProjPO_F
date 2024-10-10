package hva.core.exception;

public class TreeAlreadyThereException extends Exception {

  String _idTree;

  public TreeAlreadyThereException(String idTree) {
    super("Árvore já registada" + idTree);
    _idTree = idTree;
  }

  public String idTree() {
    return _idTree;
  }

  public TreeAlreadyThereException(Throwable cause) {
    super(cause);
  }
    
}