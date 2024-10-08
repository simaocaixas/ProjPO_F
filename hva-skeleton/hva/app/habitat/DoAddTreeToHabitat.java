package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Tree;
import hva.core.exception.HabitatNotKnown;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("idHabi", Prompt.habitatKey());
    addStringField("idTree", Prompt.treeKey());
    addStringField("nameTree", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("difficulty", Prompt.treeDifficulty());
    addStringField("treeType", Prompt.treeType());
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command TO COMPLETE
    try {
    _receiver.registerTree(stringField("idHabi"), stringField("idTree"), stringField("nameTree"), integerField("age"), integerField("difficulty"),stringField("treeType"));
    } catch (HabitatNotKnown e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
    }
  }
}
