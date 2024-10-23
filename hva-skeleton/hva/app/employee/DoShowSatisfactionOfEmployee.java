package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.core.exception.EmployeeNotKnownException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("idEmp", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try {
      Employee employee = _receiver.getEmployeeById(stringField("idEmp"));
      _display.addLine(Math.round(employee.calculateSatisfaction()));
      _display.display();
    } catch (EmployeeNotKnownException e) {
      throw new UnknownEmployeeKeyException(stringField("idEmp"));
    }
  }
}
