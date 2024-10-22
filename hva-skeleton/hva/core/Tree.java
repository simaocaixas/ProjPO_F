package hva.core;

public abstract class Tree extends Identifier {

    private Season _season;
    private int _age; 
    private int _diff;
    private int _seasonEffort;
    private String _biologicalCycle;
    
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

    public int getSeasonEffort() { 
        return _seasonEffort;
    }

    protected String getBiologicalCycle() { 
        return _biologicalCycle;
    }

    protected void setSeasonEffort(int seasonEffort) { 
        _seasonEffort = seasonEffort;
    }

    protected void setBiologicalCycle(String biologicalCycle) { 
        _biologicalCycle = biologicalCycle;
    }

    protected abstract void advanceSeason(); 

    protected String treeToString() {
        return "ARVORE" + "|" + super.toString() + "|" + age() + "|" + diff() + "|";
    }

}

