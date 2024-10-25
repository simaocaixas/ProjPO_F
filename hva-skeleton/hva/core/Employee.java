package hva.core;

import hva.core.exception.*;

/**
 * Represents an abstract employee in the hotel. Each employee is uniquely identified by an ID and name.
 * Employees are responsible for managing certain tasks or responsibilities, which can be added or removed.
 * 
 * This class defines methods for calculating employee satisfaction, adding and removing responsibilities, 
 * and provides a common structure for employee subclasses.
 * 
 * Subclasses must implement the methods for calculating satisfaction and managing responsibilities.
 */
public abstract class Employee extends Identifier {

    /**
     * Constructs an Employee with the specified ID, name, and hotel.
     * 
     * @param idEmp   the unique identifier of the employee
     * @param nameEmp the name of the employee
     * @param hotel   the hotel where the employee works
     */
    protected Employee(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    /**
     * Calculates the satisfaction level of the employee.
     * Subclasses must implement this method.
     * 
     * @return the satisfaction level as a double
     */
    public abstract double calculateSatisfaction();

    /**
     * Adds a responsibility to the employee.
     * Subclasses must implement this method.
     * 
     * @param id the ID of the responsibility to be added
     * @throws ResponsabilityNotThereException if the responsibility is not found
     */
    abstract void addResponsibility(String id) throws ResponsabilityNotThereException;

    /**
     * Removes a responsibility from the employee.
     * Subclasses must implement this method.
     * 
     * @param id the ID of the responsibility to be removed
     * @throws ResponsabilityNotThereException if the responsibility is not found
     */
    abstract void removeResponsibility(String id) throws ResponsabilityNotThereException;

    /**
     * Returns a string representation of the employee.
     * 
     * @return a string representing the employee
     */
    public String toString() {
        return "|" + super.toString();
    }
}
