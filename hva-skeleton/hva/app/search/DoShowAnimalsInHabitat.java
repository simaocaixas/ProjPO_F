package hva.app.search;

import hva.core.Habitat;
import hva.core.Hotel;
import hva.core.Animal;
import hva.app.exception.*;
import hva.core.exception.*;
import java.util.*;
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
      Habitat habitat = _receiver.getHabitatById(stringField("idHabi"));
      List<Animal> animals = habitat.getAllAnimals();
      
      for (Animal animal : animals) {
        _display.addLine(animal.toString());
      
      _display.display();
    }
    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
   
    }
  
  }
}

