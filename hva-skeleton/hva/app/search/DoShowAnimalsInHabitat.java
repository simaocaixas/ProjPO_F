package hva.app.search;

import hva.core.Hotel;
import hva.app.exception.*;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.habitat.Prompt;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("idHabi",Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
      _display.addLine(_receiver.showAnimalsFromOneHabitat(stringField("idHabi")));
    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));  
    }
  }
}

