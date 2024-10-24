package hva.app.search;

import hva.core.Hotel;
import hva.core.Animal;
import hva.core.Register;
import java.util.List;
import hva.app.exception.UnknownAnimalKeyException;
import hva.core.exception.AnimalNotKnownException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("idAni", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    try {
      Animal animal = _receiver.getAnimalById(stringField("idAni"));
      List<Register> registers = animal.getAllRegisters();

      for (Register regi : registers) {
        _display.addLine(regi);
      }
      _display.display();
    } catch (AnimalNotKnownException e) {
      throw new UnknownAnimalKeyException(stringField("idAni"));
    }
  }
}
