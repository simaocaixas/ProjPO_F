package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.AnimalNotKnownException;
import hva.core.exception.HabitatNotKnownException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("idHab", hva.app.habitat.Prompt.habitatKey());
    addStringField("idAni", hva.app.animal.Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.transferAnimal(stringField("idAni"), stringField("idHab"));

    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHab"));
    } catch (AnimalNotKnownException e) {
      throw new UnknownAnimalKeyException(stringField("idAni"));
    }
  }
}
