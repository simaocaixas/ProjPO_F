package hva.app.main;

import hva.app.main.Prompt;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    
    if(_receiver.getFileName() == null) {
      addStringField("filenameToSaveAs", Prompt.newSaveAs());
    } else {
      addStringField("filenameToSaveAs", Prompt.saveAs());
    }
  }

  @Override
  protected final void execute() {
    
    try{  
      _receiver.saveAs(stringField("filenameToSaveAs"));
    } catch (IOException e) {
      System.err.println("Não foi possível serializar o ficheiro.");
    } catch (MissingFileAssociationException e) {
      System.err.println("Não foi possível associar o ficheiro.");
    }

  }
}
