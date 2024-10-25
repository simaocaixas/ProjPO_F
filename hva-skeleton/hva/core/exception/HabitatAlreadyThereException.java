package hva.core.exception;

public class HabitatAlreadyThereException extends Exception {

  public HabitatAlreadyThereException(String idHab) {
    super("O habitat já está registado" + idHab);
  }


    
}
