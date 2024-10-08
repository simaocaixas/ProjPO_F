package hva.core.exception;

public class TreeAlreadyThere extends Exception {
  private static final long serialVersionUID = 202407081733L;
  String _idTree;

  public TreeAlreadyThere(String idTree) {
    super(idTree);
    _idTree = idTree;
  }

  public String idTree() {
    return _idTree;
  }

  public TreeAlreadyThere(Throwable cause) {
    super(cause);
  }
    
}
