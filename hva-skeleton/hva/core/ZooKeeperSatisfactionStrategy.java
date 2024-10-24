package hva.core;

import java.io.Serializable;

public class ZooKeeperSatisfactionStrategy implements SatisfactionStrategy, Serializable {
    
    private ZooKeeper _zookeeper; 

    public ZooKeeperSatisfactionStrategy(ZooKeeper zookeeper) {
        _zookeeper = zookeeper;
    }

    public double calculateSatisfaction() {
        double satisfaction = 300;
        
        for (Habitat habitat : _zookeeper.getHabitats()) {

            double work = calculateWorkInHabi(habitat);

            satisfaction -= work / habitat.getNumberOfZookeepers();
        }

        return satisfaction;
    }

    private double calculateWorkInHabi(Habitat habitat) {

        double work = habitat.area(); 
        work += 3 * habitat.population();  

        for (Tree tree : habitat.getAllTrees()) {
            work += tree.cleaningEffort();
        }

        return work;
    }
}
