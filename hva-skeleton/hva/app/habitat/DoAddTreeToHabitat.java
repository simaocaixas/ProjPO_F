package hva.app.habitat;

import hva.core.Hotel;
import hva.core.Tree;
import hva.core.exception.*;  
import hva.app.exception.*;

import pt.tecnico.uilib.forms.Form;

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

      String treeType = stringField("treeType");
    
      while (!treeType.equals("PERENE") && !treeType.equals("CADUCA")) {

        treeType = Form.requestString(Prompt.treeType());
      }

    try {
      _receiver.registerTree(stringField("idHabi"), stringField("idTree"), stringField("nameTree"), integerField("age"), integerField("difficulty"),stringField("treeType"));
      Tree tree = _receiver.getTreeById(stringField("idTree"));
      _display.addLine(tree);
      _display.display();
    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
    } catch (TreeAlreadyThereException e) {
      throw new DuplicateTreeKeyException(stringField("idTree"));
    } catch (TreeNotKnownException e) {
      throw new UnknownTreeKeyException(stringField("idTree"));
    }
  }
}
