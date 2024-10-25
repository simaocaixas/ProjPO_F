package hva.core;

import java.io.Serializable;
/**
 * Implements the satisfaction strategy for a zookeeper, calculating their 
 * satisfaction score based on the responsibilities associated with the habitats 
 * they manage.
 */
public class ZooKeeperSatisfactionStrategy implements SatisfactionStrategy, Serializable {

    private ZooKeeper _zookeeper;

    /**
     * Constructs a ZooKeeperSatisfactionStrategy for the specified zookeeper.
     *
     * @param zookeeper the zookeeper for whom this satisfaction strategy is created
     */
    ZooKeeperSatisfactionStrategy(ZooKeeper zookeeper) {
        _zookeeper = zookeeper;
    }

    /**
     * Calculates the satisfaction score of the zookeeper based on their managed habitats.
     *
     * The satisfaction is initialized to 300 and is decreased by the workload 
     * in each habitat divided by the number of zookeepers assigned to that habitat.
     *
     * @return the calculated satisfaction score
     */
    public double calculateSatisfaction() {
        double satisfaction = 300;
        
        for (Habitat habitat : _zookeeper.getHabitats()) {
            double work = calculateWorkInHabi(habitat);
            satisfaction -= work / habitat.getNumberOfZookeepers();
        }

        return satisfaction;
    }

    /**
     * Calculates the total workload in a given habitat.
     * 
     * The workload includes the habitat's area, its population, and the cleaning 
     * effort required for all trees within the habitat.
     *
     * @param habitat the habitat for which the workload is calculated
     * @return the total calculated workload in the habitat
     */
    private double calculateWorkInHabi(Habitat habitat) {
        double work = habitat.area(); 
        work += 3 * habitat.population();  

        for (Tree tree : habitat.getAllTrees()) {
            work += tree.cleaningEffort();
        }

        return work;
    }
}
