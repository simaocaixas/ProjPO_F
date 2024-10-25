package hva.core;

import java.util.*;
import hva.core.exception.*;
/**
 * Represents a zookeeper in the system who is responsible for managing habitats.
 * This class extends the Employee class and includes functionalities related to
 * habitat management and satisfaction calculation.
 */
public class ZooKeeper extends Employee {

    private HashMap<String, Habitat> _habitats = new HashMap<>();
    private SatisfactionStrategy _satisfactionStrategy = new ZooKeeperSatisfactionStrategy(this);

    /**
     * Constructs a ZooKeeper with the specified ID, name, and hotel.
     *
     * @param idEmp the ID of the zookeeper
     * @param nameEmp the name of the zookeeper
     * @param hotel the hotel associated with the zookeeper
     */
    ZooKeeper(String idEmp, String nameEmp, Hotel hotel) {
        super(idEmp, nameEmp, hotel);
    }

    /**
     * Calculates the satisfaction score of the zookeeper based on their responsibilities.
     *
     * @return the calculated satisfaction score
     */
    public double calculateSatisfaction() {
        return _satisfactionStrategy.calculateSatisfaction();
    }

    /**
     * Adds a responsibility for managing a habitat identified by the given ID.
     * If the habitat does not exist, a ResponsabilityNotThereException is thrown.
     *
     * @param idHabi the ID of the habitat to manage
     * @throws ResponsabilityNotThereException if the habitat is not recognized
     */
    void addResponsibility(String idHabi) throws ResponsabilityNotThereException {
        try {
            Hotel hotel = hotel();
            Habitat habitat = hotel.getHabitatById(idHabi);
            _habitats.put(idHabi, habitat);
            habitat.addZooKeeper(this);
        } catch (HabitatNotKnownException e) {
            throw new ResponsabilityNotThereException(idHabi);
        }
    }

    /**
     * Removes the responsibility for managing a habitat identified by the given ID.
     * If the habitat is not managed by the zookeeper, a ResponsabilityNotThereException is thrown.
     *
     * @param idHabi the ID of the habitat to remove
     * @throws ResponsabilityNotThereException if the habitat is not managed by this zookeeper
     */
    void removeResponsibility(String idHabi) throws ResponsabilityNotThereException {
        if (_habitats.containsKey(idHabi)) {
            _habitats.remove(idHabi);
        } else {
            throw new ResponsabilityNotThereException(idHabi);
        }
    }

    /**
     * Converts the IDs of habitats managed by this zookeeper to a comma-separated string.
     *
     * @return a string of habitat IDs
     */
    String habitatIdsToString() {
        StringBuilder sb = new StringBuilder();
        for (String idHabi : _habitats.keySet()) {
            sb.append(idHabi).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Gets the collection of habitats managed by this zookeeper.
     *
     * @return an unmodifiable collection of habitats
     */
    Collection<Habitat> getHabitats() {
        return Collections.unmodifiableCollection(_habitats.values());
    }

    /**
     * Returns a string representation of the zookeeper,
     * including their ID, name, and managed habitats.
     *
     * @return a string representation of the zookeeper
     */
    public String toString() {
        return "TRT" + super.toString() + (_habitats.size() == 0 ? "" : "|" + habitatIdsToString());
    }
}
