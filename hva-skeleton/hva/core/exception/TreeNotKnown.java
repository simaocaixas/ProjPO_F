package hva.core.exception;

public class TreeNotKnown extends Exception {
  private static final long serialVersionUID = 202407081733L;
  String _idTree;

  public TreeNotKnown(String idTree) {
    super(idTree);
    _idTree = idTree;
  }

  public String idTree() {
    return _idTree;
  }

  public TreeNotKnown(Throwable cause) {
    super(cause);
  }
    
}
