package hva.core.exception;

public class HabitatNotKnownException extends Exception {

  public HabitatNotKnownException(String idHab) {
    super("Habitat nao encontrado " + idHab);
  }

  public HabitatNotKnownException(Throwable cause) {
    super(cause);
  }
    
}
