package hva.core;

import java.util.*;

public class Vaccine extends Identifier  {

    private List<Specie> _species = new ArrayList<Specie>();
    private List<Register> _registers = new ArrayList<>();

    public Vaccine(Hotel hotel, String idVaccine, String VaccineName, List<Specie> species) {
        super(idVaccine, VaccineName, hotel);
        _species = species;
    }

    public String speciedIdSorted() {
        StringBuilder sb = new StringBuilder();
        String prefix = "|";  

        _species.sort(Comparator.comparing(specie -> specie.id()));
        for (Specie specie : _species) {
            sb.append(prefix);   
            sb.append(specie.id());
            prefix = ",";       
        }
        return sb.toString();
    }

    public String toString() {
        return "VACINA" + "|" + super.toString() + "|" + _registers.size() + speciedIdSorted();
    }
}
