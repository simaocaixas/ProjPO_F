package hva.core;
import java.util.*;
import hva.core.exception.*;
import hva.app.exception.*;
import java.io.Serializable;

public class ZooKeeper extends Employee implements Responsibility {

    private HashMap<String,Habitat> _habitats = new HashMap<String,Habitat>();


    public ZooKeeper(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    @Override
    public void addResponsibility(String idHabi) throws ResponsabilityNotThere {
        try {
            Hotel hotel = hotel();
            Habitat habitat = hotel.getHabitatById(idHabi);
            _habitats.put(idHabi, habitat);
        } catch (HabitatNotKnown e) {
            throw new ResponsabilityNotThere(idHabi);
        }
    }

    @Override
    public void removeResponsibility(String idHabi) throws ResponsabilityNotThere {
        if (_habitats.containsKey(idHabi)) {
            _habitats.remove(idHabi);
        } else {
            throw new ResponsabilityNotThere(idHabi);
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
