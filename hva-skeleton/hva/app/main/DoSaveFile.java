package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.app.exception.WrongSaveFileException;
import hva.app.main.Prompt;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import hva.core.exception.*;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  
    if(_receiver.getFileName().length() == 0) {
      addStringField("filenameToSaveAs", Prompt.newSaveAs());
    } else {
      addStringField("filenameToSaveAs", Prompt.saveAs());
    }
  }

  @Override
  protected final void execute() {

    try {
      _receiver.FileNameCheck();
      _receiver.saveAs(stringField("filenameToSaveAs"));
      
    } catch (FileNameAlreadyExistsExceptionCore ece) {
      try {
        _receiver.setHotelState(false);
        _receiver.save();
      } catch (IOException ece2) {
        System.err.println("Error saving file");
      } catch (MissingFileAssociationException ece2) {
        System.err.println("Error saving file");
      }
    } catch (IOException ece) {
        System.err.println("Error saving file");
    } catch (MissingFileAssociationException ece) {
        System.err.println("Error saving file");
    }
  }

}