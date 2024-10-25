package hva.core;

import java.io.Serializable;

/**
 * Represents a general identifier object for entities such as animals, trees, employees, etc. 
 * An Identifier has a unique ID, name, and may be associated with a specific hotel.
 * This class implements Serializable, allowing its instances to be serialized and deserialized.
 */
public class Identifier implements Serializable {

    private String _id;
    private String _name;
    private Hotel _hotel;

    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs an Identifier with the specified ID, name, and associated hotel.
     * 
     * @param id    the unique identifier of the entity
     * @param name  the name of the entity
     * @param hotel the hotel associated with the entity
     */
    Identifier(String id, String name, Hotel hotel) {
        _id = id;
        _name = name;
        _hotel = hotel;
    }

    /**
     * Constructs an Identifier with the specified ID and name.
     * This constructor is used when there is no associated hotel.
     * 
     * @param id   the unique identifier of the entity
     * @param name the name of the entity
     */
    Identifier(String id, String name) {
        _id = id;
        _name = name;
    }

    /**
     * Returns the ID of the entity.
     * 
     * @return the entity's ID
     */
    String id() {
        return _id;
    }

    /**
     * Returns the name of the entity.
     * 
     * @return the entity's name
     */
    String name() {
        return _name;
    }

    /**
     * Returns the hotel associated with the entity, if any.
     * 
     * @return the associated hotel, or null if not available
     */
    Hotel hotel() {
        return _hotel;
    }

    /**
     * Returns a string representation of the Identifier object.
     * The format is: ID|Name.
     * 
     * @return a string representing the identifier
     */
    public String toString() {
        return _id + "|" + _name;
    }
}
