package hva.core.exception;

public class VetNotAuthorizedException extends Exception {

    private String _idVet;
    private String _idSpc;
  
    public VetNotAuthorizedException(String idVet, String idSpc) {
      super("Veterinário" + idVet + "não autorizado a vacinar éspecie" + idSpc);
      _idVet = idVet;
      _idSpc = idSpc;
    }
  
    public String idVet() {
      return _idVet;
    }
    public String idSpc() {
      return _idSpc;
    }
  
    public VetNotAuthorizedException(Throwable cause) {
      super(cause);
    }
   
  }
  