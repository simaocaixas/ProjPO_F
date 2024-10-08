package hva.core;
import java.util.*;

public abstract class Tree extends Identifier {

    private Season _season; 
    private int _age; 
    private int _diff;
    
    private Habitat _habitat;
    
    protected Tree(Habitat habitat, String idTree, String treeName, int diff, Season season) {
        super(idTree, treeName);
        _habitat = habitat;
        _diff = diff;
        _season = season;
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

    protected Season season() {
        return _season;
    }

    protected String seasonName() {
        return _season.name();
    }

    protected abstract String getBiologicalCycle(); 

    protected String treeToString() {
        return "ARVORE" + "|" + id() + "|" + name() + "|" + age() + "|" + diff() + "|";
    }

}

