package hva.core;
import hva.core.exception.ResponsabilityNotThere;

public interface Responsibility {
    void addResponsibility(String id) throws ResponsabilityNotThere;
    void removeResponsibility(String id) throws ResponsabilityNotThere;
}
