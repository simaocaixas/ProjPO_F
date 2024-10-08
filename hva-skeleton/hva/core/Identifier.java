package hva.core;

public class Identifier {

    private String _id;
    private String _name;
    
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

    public int compareTo(Identifier id) {
        return _id.compareTo(id.id());
    }
}
