package hva.app.employee;

import hva.core.Hotel;
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
    addStringField("empType", Prompt.employeeType());
  }
  
  @Override 
  protected void execute() throws CommandException {
    String empType = "";
    while (true) {
      if ((stringField("empType").equals("VET") || stringField("empType").equals("TRT") || empType.equals("VET")) || empType.equals("TRT") ) {
        break;
      } else {
        empType = Form.requestString(Prompt.employeeType());
      }
    } 

    try {
      _receiver.registerEmployee(stringField("idEmp"), stringField("nameEmp"), stringField("empType"));
    } catch (DuplicateEmployeeKeyException e) {
      System.err.println("This employee already exists!!!");
    }
  }
}
