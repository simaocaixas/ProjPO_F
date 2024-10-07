package hva.core.exception;

public class FileNameAlreadyExistsExceptionCore extends Exception {
  private static final long serialVersionUID = 202407081733L;

  public FileNameAlreadyExistsExceptionCore(String idFile) {
    super(idFile);
  }

  public FileNameAlreadyExistsExceptionCore() {
    super();
  }

  public FileNameAlreadyExistsExceptionCore(Throwable cause) {
    super(cause);
  }
    
}
