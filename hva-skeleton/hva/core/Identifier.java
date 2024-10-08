package hva.core;

public class Identifier {

    private String _id;
    private String _name;
    private Hotel _hotel;
    
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

    public int compareTo(Identifier id) {
        return _id.compareTo(id.id());
    }

    public String toString() {
        return _id + "|" + _name;
    }
}
