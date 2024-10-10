package hva.core;
import java.util.*;
import hva.core.exception.*;

public class ZooKeeper extends Employee {

    private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>();


    public ZooKeeper(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    public void addResponsibility(String idHabi) throws ResponsabilityNotThereException {
        try {
            Hotel hotel = hotel();
            Habitat habitat = hotel.getHabitatById(idHabi);
            _habitats.put(idHabi, habitat);
        } catch (HabitatNotKnownException e) {
            throw new ResponsabilityNotThereException(idHabi);
        }
    }

    public void removeResponsibility(String idHabi) throws ResponsabilityNotThereException {
        if (_habitats.containsKey(idHabi)) {
            _habitats.remove(idHabi);
        } else {
            throw new ResponsabilityNotThereException(idHabi);
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
    public String toString() {
        return "TRT" + super.toString() + (_habitats.size() == 0 ? "" : habitatIdsToString());
    }
}
