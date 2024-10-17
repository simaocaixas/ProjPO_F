package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.app.main.Prompt;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
import java.io.FileNotFoundException;
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
  
    if(_receiver.getFileName().isEmpty()) {
      addStringField("filenameToSaveAs", Prompt.newSaveAs());
    } else {
      addStringField("filenameToSaveAs", Prompt.saveAs());
    }
  }

  @Override
  protected final void execute() {

    try {
      if (_receiver.getFileName().isEmpty()) {
        _receiver.saveAs(stringField("filenameToSaveAs"));
      } else {
        _receiver.save(); 
      }
    } catch (IOException | MissingFileAssociationException e) {
      System.out.println("File not found");
    }
  }
}


