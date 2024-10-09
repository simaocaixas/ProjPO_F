package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.*;
import hva.core.Vaccine;
//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {

    List<Vaccine> vaccines = _receiver.getAllVaccines();
    for (Vaccine vaccine : vaccines) {
      _display.addLine(vaccine.toString());
    }
    _display.display();
  }
}
