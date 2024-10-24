package hva.app.vaccine;

import java.util.List;

import hva.core.Hotel;
import hva.core.Vaccine;
import hva.app.exception.*;
import hva.core.exception.*;

import hva.app.employee.Prompt;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("idVac", hva.app.vaccine.Prompt.vaccineKey());
    addStringField("nameVac", hva.app.vaccine.Prompt.vaccineName());
    addStringField("idSpcs", hva.app.vaccine.Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    List<Vaccine> vaccines = _receiver.getAllVaccines();
    if(_receiver.containsKeyIgnoreCase(vaccines, stringField("idVac"))) {
      throw new DuplicateVaccineKeyException(stringField("idVac"));
    } else {
      try {
        _receiver.registerVaccine(stringField("idVac"), stringField("nameVac"), stringField("idSpcs"));
      } catch(SpeciesNotKnownException e) {
        throw new UnknownSpeciesKeyException(stringField("idSpcs"));
      }
    }
  }
}
