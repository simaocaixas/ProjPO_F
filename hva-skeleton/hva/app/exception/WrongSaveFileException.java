package hva.app.exception;

public class WrongSaveFileException extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public WrongSaveFileException(String idFile) {
    super(idFile);
  }

  public WrongSaveFileException() {
    super();
  }

  public WrongSaveFileException(Throwable cause) {
    super(cause);
  }
    
}
