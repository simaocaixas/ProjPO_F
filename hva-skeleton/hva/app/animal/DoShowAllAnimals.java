package hva.app.animal;

import hva.core.Hotel;
import hva.core.Animal; // ?????? 
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed
import java.util.*;
/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    List<Animal> animals = _receiver.getAllAnimals();
    for (Animal animal : animals) {
      _display.addLine(animal.toString());
    }
    _display.display();
  }
}
