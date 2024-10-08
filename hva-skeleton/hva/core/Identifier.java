package hva.core;

import java.io.Serializable;

public class Identifier implements Serializable {

    private String _id;
    private String _name;
    private Hotel _hotel;

    private static final long serialVersionUID = 1L;
    
    public Identifier(String id, String name, Hotel hotel) {
        _id = id;
        _name = name;
        _hotel = hotel;
    }

    public Identifier(String id, String name) {
        _id = id;
        _name = name;
    }


    public String id() {
        return _id;
    }

    public String name() {
        return _name;
    }

    public Hotel hotel() {
        return _hotel;
    }

    public String toString() {
        return _id + "|" + _name;
    }
}
