package hva.core;

public interface SeasonState {
    void changeSeasonInfo(Tree tree);
    SeasonState next();
}