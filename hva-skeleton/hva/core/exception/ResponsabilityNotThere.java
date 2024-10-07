package hva.core.exception;

public class ResponsabilityNotThere extends Exception {

    private static final long serialVersionUID = 202407081733L;

    public ResponsabilityNotThere(String idRes) {
        super(idRes);
    }

    public ResponsabilityNotThere(Throwable cause) {
        super(cause);
    }
    
}
