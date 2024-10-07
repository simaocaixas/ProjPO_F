package hva.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Habitat {              // COMPLETAMENTE INCOMPLETO

    private String _nomeHabi;
    private String _idHabi;
    private int _area;


    private Set<String> _notSutables = new HashSet<>();
    private Set<String> _sutables = new HashSet<>();
    private HashMap<String, Animal> _animals = new HashMap<String, Animal>();
    private HashMap<String, Tree> _trees = new HashMap<String, Tree>();

    private Hotel _hotel;

    public Habitat(Hotel hotel, String idHabi, String nome, int area) {
        _hotel = hotel;
        _idHabi = idHabi;
        _nomeHabi = nome;
        _area = area;
    }

    public String habitatToString() {
        return "HABITAT" + "|" + idHabi() + "|" + nomeHabi() + "|" + area() + "|" + numberOfTrees();
    }

    public String AllTreesToString() {
        for (Tree tree : _trees.values()) {
            return tree.treeToString();
        }
    }

    protected void addAnimal(Animal animal) {
        _animals.put(animal.idAni(),animal);
    }

    protected void addTree(Tree tree) {
        _trees.put(tree.idTree(),tree);
    }

    protected int area() {
        return _area;
    }
    protected String idHabi() {
        return _idHabi;
    }

    protected String nomeHabi() {
        return _nomeHabi;
    }

    protected int numberOfTrees() {
        return _trees.size();
    }

    // AQUI
    protected void changeHabitat(Habitat habitat, int area) {

        _area = area;
    }
}
