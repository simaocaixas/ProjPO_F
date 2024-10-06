package hva.app.employee;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import hva.core.Emplooye; 
import java.util.*;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() {
    //FIXME implement command
    List<Emplooye> emplooyes = _receiver.getAllEmployees();
    for (Emplooye emplooye : emplooyes) {
      _display.addLine(emplooye.empToString());
    }
    _display.display();
  }

}
