package hva.core.exception;

public class VaccineAlreadyThereException extends Exception {

    private String _idVaccine;
  
    public VaccineAlreadyThereException(String idVaccine) {
      super("Vacina já registada" + idVaccine);
      _idVaccine = idVaccine;
    }
  
    public String idVaccine() {
      return _idVaccine;
    }
  
    public VaccineAlreadyThereException(Throwable cause) {
      super(cause);
    }
      
  }
