package hva.core.exception;

public class ResponsabilityNotThereException extends Exception {

    public ResponsabilityNotThereException(String idRes) {
        super("Responsabilidade não existe" + idRes);
    }
    
}
