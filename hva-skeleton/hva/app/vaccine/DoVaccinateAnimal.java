package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine;
import hva.core.Animal;
import hva.core.Specie;

import hva.core.exception.*;
import hva.app.exception.*;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("idVac", Prompt.vaccineKey());
    addStringField("idVet", Prompt.veterinarianKey());
    addStringField("idAni", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      Vaccine vaccine = _receiver.getVaccineById(stringField("idVac"));
      Specie animalSpecie = _receiver.getAnimalById(stringField("idAni")).specie();
      
      if (!vaccine.hasSpecie(animalSpecie)) {
        _display.addLine(Message.wrongVaccine(stringField("idVac"), stringField("idAni")));
        _display.display();
      }
      _receiver.vaccineAnimal(stringField("idVac"), stringField("idAni"), stringField("idVet"));
      
    } catch (VaccineNotKnownException e) {
      throw new UnknownVaccineKeyException(stringField("idVac"));
    } catch (AnimalNotKnownException e) {
      throw new UnknownAnimalKeyException(stringField("idAni"));
    } catch (EmployeeNotKnownException e) {
      throw new UnknownVeterinarianKeyException(stringField("idVet"));
    } catch (VetNotAuthorizedException e) {
      throw new VeterinarianNotAuthorizedException(stringField("idVet"),e.idSpc());
    } 
  }
}
