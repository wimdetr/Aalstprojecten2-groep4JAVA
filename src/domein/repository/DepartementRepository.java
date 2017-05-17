package domein.repository;

import domein.Departement;
import domein.Werkgever;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import persistentie.DepartementMapper;

/**
 *
 * @author wimde
 */
public class DepartementRepository {

    private List<Departement> lijst;
    private DepartementMapper mapper;

    public DepartementRepository() {
        lijst = new ArrayList<>();
        mapper = new DepartementMapper();
        getAll();
    }

    public void getAll() {
        lijst = mapper.getAll();

    }

    public List<Departement> getLijst() {
        return lijst;
    }

    public List<Departement> zoekNaam(String query) {
        return lijst.stream()
                .filter(p -> p.getWerkgever().getNaam().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Departement> zoekAfdeling(String query) {
        return lijst.stream()
                .filter(p -> p.getNaam().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Departement> zoekGemeente(String query) {
        return lijst.stream()
                .filter(p -> p.getGemeente().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
