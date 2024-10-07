package hva.core.exception;

public class HabitatAlreadyThere extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public HabitatAlreadyThere(String idHab) {
    super(idHab);
  }

  public HabitatAlreadyThere(Throwable cause) {
    super(cause);
  }
    
}
