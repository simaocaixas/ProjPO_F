package hva.core.exception;

public class FileNameAlreadyExistsExceptionCore extends Exception {

  public FileNameAlreadyExistsExceptionCore(String idFile) {
    super("O nome do ficheiro já existe" + idFile);
  }

  public FileNameAlreadyExistsExceptionCore() {
    super();
  }

  public FileNameAlreadyExistsExceptionCore(Throwable cause) {
    super(cause);
  }
    
}
