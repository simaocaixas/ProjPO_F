package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.EmployeeAlreadyThereException;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    //FIXME add command fields
    addStringField("idEmp", Prompt.employeeKey());
    addStringField("nameEmp", Prompt.employeeName());
    addOptionField("empType", Prompt.employeeType(),"VET","TRT");
  }
  
  @Override 
  protected void execute() throws CommandException {
    
    try {

      _receiver.registerEmployee(stringField("idEmp"), stringField("nameEmp"), stringField("empType"));

    } catch (EmployeeAlreadyThereException e) {
     throw new DuplicateEmployeeKeyException(stringField("idEmp"));
    }
  }
}
