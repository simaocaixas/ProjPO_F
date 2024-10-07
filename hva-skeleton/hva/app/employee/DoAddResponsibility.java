package hva.app.employee;

import hva.core.Hotel;
import hva.app.exception.*;
import hva.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    //FIXME add command fields
    addStringField("idEmp", Prompt.employeeKey());
    addStringField("idRes", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {

    try {
      _receiver.newResponsability(stringField("idEmp"), stringField("idRes"));
    } catch (EmployeeNotKnown ece) {
      throw new UnknownEmployeeKeyException(stringField("idEmp"));
    } catch (ResponsabilityNotThere ece) {
      throw new NoResponsibilityException(stringField("idRes"),  stringField("idRes"));
    } 
  }
}
