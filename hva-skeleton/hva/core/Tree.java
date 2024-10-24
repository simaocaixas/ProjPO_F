package hva.core;

public abstract class Tree extends Identifier {

    private Season _season;
    private final Season _birthSeason; 
    private int _age; 
    private int _diff;
    private TreeState _state;
    private Habitat _habitat;
    
    protected Tree(Habitat habitat, String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName);
        _age = age;
        _habitat = habitat;
        _diff = diff;
        _season = season;
        _birthSeason = season;
    }

    protected Tree(String idTree, String treeName, int age, int diff, Season season) {
        super(idTree, treeName);
        _age = age;
        _diff = diff;
        _season = season;
        _birthSeason = season;
    }

    protected double cleaningEffort() {
        return _diff * _state.seasonalEffort() * Math.log(_age + 1);
    }

    protected void updateTreeAge() {
        if(_birthSeason.equals(_season)) {
            _age++;
        }
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

    protected TreeState getState() {
        return _state;
    }
    
    protected void setState(TreeState state) {
        _state = state;
    }

    protected void advanceSeason(){
        _season = _season.nextSeason();
        _state = _state.nextSeason();
        updateTreeAge();
    }

    protected void updateTreeState() {
        int i = 0;
        int seasonInteger = season().getSeasonNumber();

        while (i++ < seasonInteger) {
            _state.nextSeason();
        }
    }

    public String toString() {
        return "ÁRVORE" + "|" + super.toString() + "|" + age() + "|" + diff() + "|";
    }

}

