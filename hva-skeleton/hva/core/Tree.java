package hva.core;
import java.util.*;

public abstract class Tree {

    private String Season; 
    private String _idTree;
    private String _treeName;
    private int _age; 
    private int _diff;
    
    private Habitat _habitat;
    
    protected Tree(Habitat habitat, String idTree, String treeName, int diff) {
        _habitat = habitat;
        _idTree = idTree;
        _treeName = treeName;
        _diff = diff;
    }

    protected String idTree() {
        return _idTree;
    }

    protected String treeName() {
        return _treeName;
    }

    protected int age() {
        return _age;
    }

    protected int diff() {
        return _diff;
    }

    protected void setAge(int age) {
        _age = age;
    }

    protected String treeString() {
        return "ARVORE" + "|" + idTree() + "|" + treeName() + "|" + age() + "|" + diff() + "|";
    }

}

