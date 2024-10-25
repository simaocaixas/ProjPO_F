package hva.core.exception;

public class TreeAlreadyThereException extends Exception {

  private String _idTree;

  public TreeAlreadyThereException(String idTree) {
    super("Árvore já registada" + idTree);
    _idTree = idTree;
  }

  public String idTree() {
    return _idTree;
  }

    
}
