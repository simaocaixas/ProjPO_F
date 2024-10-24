package hva.core;
import java.util.*;

public class Vaccine extends Identifier  {

    private List<Specie> _species = new ArrayList<Specie>();
    private List<Register> _registers = new ArrayList<>();

    public Vaccine(Hotel hotel, String idVaccine, String vaccineName, List<Specie> species) {
        super(idVaccine, vaccineName, hotel);
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

    public boolean hasSpecie(Specie specie) {
        return _species.contains(specie);
    }

    public int calculateDamage(Specie specie) {
        
        int differ, majorDiffer = 0;
        
        if (_species.isEmpty()) {
            return specie.name().length();
        }

        for (Specie s : _species) {
            differ = calculateNameDifference(specie.name(), s.name());
            if (differ > majorDiffer) {
                majorDiffer = differ;
            }
        }

        return majorDiffer;
    }

    public int calculateNameDifference(String name, String name2) {

        char[] str = name.toCharArray();
        char[] str2 = name2.toCharArray();
        int count = 0, biggerName;     

        for (char c : str) {
            for(int i = 0; i < str2.length; i++) {
                if (c == str2[i]) {
                    str2[i] = 0;
                    count++;
                }
            }
        }
        biggerName = name.length() > name2.length() ? name.length() : name2.length();
            
        return biggerName - count;
    }
    
    public void addRegister(Register register) {
        _registers.add(register);
    }

    public String toString() {
        return "VACINA" + "|" + super.toString() + "|" + _registers.size() + speciedIdSorted();
    }
}
