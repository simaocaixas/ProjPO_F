package hva.app.animal;

import hva.core.Hotel;
import hva.core.Specie;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed
/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    //FIXME add command fields
    addStringField("idAni",Prompt.animalKey());
    addStringField("nomeAni",Prompt.animalName());
    addStringField("idSpc",Prompt.speciesKey());
    addStringField("idHabi",hva.app.habitat.Prompt.habitatKey()); 
  }

  @Override
  protected final void execute() throws CommandException { 
    try {
      Specie specie = _receiver.getSpecieById(stringField("idSpc"));
      _receiver.registerAnimal(stringField("idAni"), stringField("nomeAni"), stringField("idSpc"), stringField("idHabi")); 
    } catch (UnknownSpeciesKeyException exception) {
      String nomeSpc = Form.requestString(Prompt.speciesName());
      _receiver.registerSpecie(stringField("idSpc"),nomeSpc);
      execute();
    }
  }
}
