package hva.app.habitat;

import java.util.List;

import hva.core.Animal;
import hva.core.Habitat;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    List<Habitat> habitats = _receiver.getAllHabitats();
    for (Habitat habitat : habitats) {
      _display.addLine(habitat.habitatToString());
    }
    _display.display();
  }
}
