package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Habitat;
import hva.core.Tree;
import hva.core.exception.HabitatNotKnownException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed
import java.util.*;
/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("idHabi", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      Habitat habitat = _receiver.getHabitatById(stringField("idHabi"));
      List<Tree> trees = habitat.getAllTrees();
      for(Tree tree : trees) {
        _display.addLine(tree);
      }
      _display.display();
    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
    }
  }
}
