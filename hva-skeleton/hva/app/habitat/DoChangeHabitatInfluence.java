package hva.app.habitat;

import hva.core.Hotel;
import hva.app.habitat.Prompt;
import pt.tecnico.uilib.forms.Form;

import hva.core.exception.HabitatNotKnownException;
import hva.core.exception.SpeciesNotKnownException;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("idHabi", Prompt.habitatKey());
    addStringField("idSpc", hva.app.animal.Prompt.speciesKey());
    addStringField("influence", Prompt.habitatInfluence());
  }
  
  @Override
  protected void execute() throws CommandException {
    
    String influence = stringField("influence");
    
    while (!influence.equals("POS") && !influence.equals("NEG") && !influence.equals("NEU")){

      influence = Form.requestString(Prompt.habitatInfluence());
    }

    try {
    _receiver.changeHabitatInfluence(stringField("idHabi"), stringField("idSpc"), influence);
    } catch (HabitatNotKnownException e) {
      throw new UnknownHabitatKeyException(stringField("idHabi"));
    } catch (SpeciesNotKnownException e) {
      throw new UnknownSpeciesKeyException(stringField("idSpc"));
    }
  }
}
