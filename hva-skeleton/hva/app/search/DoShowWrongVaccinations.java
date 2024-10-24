package hva.app.search;

import hva.core.Hotel;
import hva.core.Register;
import hva.core.Damage;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
//FIXME add more import s if needed

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    List<Register> registers = _receiver.getAllRegisters();

    for (Register regi : registers) {
      if (!regi.damage().equals(Damage.NORMAL)) {
        _display.addLine(regi);
      }
    }
    _display.display();
  }
}
