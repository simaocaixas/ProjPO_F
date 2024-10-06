package hva.core;
import java.util.*;

import hva.app.exception.*;

public class ZooKeeper extends Emplooye {

    private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>();


    public ZooKeeper(Hotel hotel, String idEmp, String nameEmp) {
        super(hotel, idEmp, nameEmp);
    }

     protected void newResponsability(String idHabi) throws NoResponsibilityException {
        try {
            Hotel hotel = hotel();
            Habitat habitat = hotel.getHabitatById(idHabi);
            _habitats.put(idHabi, habitat);
        } catch (UnknownHabitatKeyException e) {
            throw new NoResponsibilityException(idEmp(),idHabi);
        }
    }

    public String habitatIdsToString() {
        StringBuilder sb = new StringBuilder();
        for (String idHabi : _habitats.keySet()) {
            sb.append(idHabi).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public String empToString() {
        return "TRT" + super.empToString() + (_habitats.size() == 0 ? "" : habitatIdsToString());
    }
}
