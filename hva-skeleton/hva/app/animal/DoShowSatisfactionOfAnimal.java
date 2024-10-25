package hva.app.animal;

import hva.core.Hotel;
import hva.core.Animal;
import hva.core.exception.AnimalNotKnownException;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {
  
  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("idAni", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      Animal animal = _receiver.getAnimalById(stringField("idAni"));
      _display.addLine(Math.round(animal.calculateSatisfaction()));
      _display.display();
    } catch (AnimalNotKnownException e) {
      throw new UnknownAnimalKeyException(stringField("idAni"));
    }
  }
}
