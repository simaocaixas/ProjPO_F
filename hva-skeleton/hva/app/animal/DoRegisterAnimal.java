package hva.app.animal;

import java.util.List;
import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Specie;
import hva.core.exception.AnimalAlreadyThereException;
import hva.core.exception.HabitatNotKnownException;
import hva.core.exception.SpeciesAlreadyThereException;
import hva.core.exception.SpeciesNotKnownException;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

  /**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("idAni",Prompt.animalKey());
    addStringField("nomeAni",Prompt.animalName());
    addStringField("idSpc",Prompt.speciesKey());
    addStringField("idHabi",hva.app.habitat.Prompt.habitatKey()); 
  }

  @Override
  protected final void execute() throws CommandException {

    List<Specie> species = _receiver.getAllSpecies();

    if(!_receiver.containsKeyIgnoreCase(species,stringField("idSpc"))) {

      try {
          String nomeSpc = Form.requestString(Prompt.speciesName()); 
          _receiver.registerSpecie(stringField("idSpc"), nomeSpc);
        } catch (SpeciesAlreadyThereException e) {
          throw new DuplicateAnimalKeyException(stringField("idSpc"));
        }
    } 

    try {
      Specie specie = _receiver.getSpecieById(stringField("idSpc"));
      _receiver.registerAnimal(stringField("idAni"), stringField("nomeAni"), stringField("idSpc"), stringField("idHabi")); 

      } catch (SpeciesNotKnownException e) {
        throw new UnknownSpeciesKeyException(stringField("idSpc"));
      } catch (HabitatNotKnownException e) {
        throw new UnknownHabitatKeyException(stringField("idHabi"));
      } catch (AnimalAlreadyThereException e) {
        throw new DuplicateAnimalKeyException(stringField("idAni"));
      }
  }
}

