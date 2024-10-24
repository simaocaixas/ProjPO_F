package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.EmployeeNotKnownException;
import hva.core.exception.ResponsabilityNotThereException;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("idEmp", Prompt.employeeKey());
    addStringField("idRes", Prompt.responsibilityKey());
  }

  
  @Override
  protected void execute() throws CommandException {
    try {
      _receiver.removeResponsability(stringField("idEmp"), stringField("idRes"));
    } catch (EmployeeNotKnownException ece) {
      throw new UnknownEmployeeKeyException(stringField("idEmp"));
    } catch (ResponsabilityNotThereException ece) {
      throw new NoResponsibilityException(stringField("idEmp"),stringField("idRes"));
    } 
  }
}
