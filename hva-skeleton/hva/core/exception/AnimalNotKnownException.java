package hva.core.exception;

public class AnimalNotKnownException extends Exception {

  public AnimalNotKnownException(String idAni) {
    super("Animal nao encontrado " + idAni);
  }

  public AnimalNotKnownException(Throwable cause) {
    super(cause);
  }
    
}