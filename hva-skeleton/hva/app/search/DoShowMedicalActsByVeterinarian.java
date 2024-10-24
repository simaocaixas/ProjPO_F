package hva.app.search;

import hva.core.Hotel;
import hva.core.Employee;
import hva.core.Veterinarian;
import hva.core.exception.EmployeeNotKnownException;
import hva.core.Register;
import java.util.List;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("idVet", hva.app.employee.Prompt.employeeKey());
  }
  
  protected void execute() throws CommandException {
    
    try {
      Employee employee = _receiver.getEmployeeById(stringField("idVet"));
      if ( employee instanceof Veterinarian) {
        Veterinarian vet = (Veterinarian) employee;
        List<Register> registers = vet.getAllRegisters();
        for (Register regi : registers) {
          _display.addLine(regi);
        }
      } else {
        throw new UnknownVeterinarianKeyException(stringField("idVet"));
      }
      _display.display();
    } catch(EmployeeNotKnownException e) {
      throw new UnknownVeterinarianKeyException(stringField("idVet"));
    }
   
    
    
    
    
  }
}
