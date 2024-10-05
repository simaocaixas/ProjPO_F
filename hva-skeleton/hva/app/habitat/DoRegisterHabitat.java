package hva.app.habitat;

import hva.core.Hotel;
import hva.app.habitat.Prompt;
import hva.app.exception.DuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {

    super(Label.REGISTER_HABITAT, receiver);
    addStringField("idHabi", Prompt.habitatKey());
    addStringField("nameHabi", Prompt.habitatName());
    addIntegerField("area", Prompt.habitatArea());

  }
  
  @Override
  protected void execute() throws CommandException {

    try {
      
      _receiver.registerHabitat(stringField("idHabi"), stringField("nameHabi"), integerField("area"));

      // AQUI
    } catch (DuplicateHabitatKeyException e) {

        System.err.println("This habitat already exists!!!");

    }
  
  }
}
