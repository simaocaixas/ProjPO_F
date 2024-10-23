package hva.core;

public interface TreeState {

    String biologicalState();
    
    int seasonalEffort();

    TreeState nextSeason();
}