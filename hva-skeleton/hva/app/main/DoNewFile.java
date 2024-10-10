package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.main.Prompt;


/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {

    if(_receiver.hasUnsavedChanges() && Form.confirm(Prompt.saveBeforeExit())) {
      DoSaveFile save = new DoSaveFile(_receiver);
      save.execute();
    }
      _receiver.newHotel();
      _receiver.setHotelState(false);
    }
}
