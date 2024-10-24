package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Register;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    
    List<Register> registers = _receiver.getAllRegisters();
    
    for( Register r : registers) {
      _display.addLine(r);
    }
    _display.display();
  }
}
