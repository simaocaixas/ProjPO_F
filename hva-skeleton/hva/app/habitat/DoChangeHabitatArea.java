package hva.app.habitat;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.exception.HabitatNotKnownException;

import java.util.HashMap;

import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    // AQUI
    addStringField("idHabi", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());

  }
  
  @Override
  protected void execute() throws CommandException {
    
    try {
    _receiver.changeHabitat(stringField("idHabi"), integerField("area"));
    } catch (HabitatNotKnownException ece) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
    }
  }
}
