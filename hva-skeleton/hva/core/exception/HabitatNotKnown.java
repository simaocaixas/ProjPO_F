package hva.core.exception;

public class HabitatNotKnown extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public HabitatNotKnown(String idHab) {
    super(idHab);
  }

  public HabitatNotKnown(Throwable cause) {
    super(cause);
  }
    
}
