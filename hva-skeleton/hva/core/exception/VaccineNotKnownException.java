package hva.core.exception;

public class VaccineNotKnownException extends Exception {

    private String _idVaccine;
  
    public VaccineNotKnownException(String idVaccine) {
      super("Árvore não existe" + idVaccine);
      _idVaccine = idVaccine;
    }
  
    public String idVaccine() {
      return _idVaccine;
    }
  
    public VaccineNotKnownException(Throwable cause) {
      super(cause);
    }
      
  }
  