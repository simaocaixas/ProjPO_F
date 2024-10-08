package hva.app.main;

import hva.core.HotelManager;
import hva.app.main.Prompt;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filenameToOpen", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
        _receiver.load(stringField("filenameToOpen"));
      } catch (UnavailableFileException efe) {
          throw new FileOpenFailedException(efe);
      }
  }
}
