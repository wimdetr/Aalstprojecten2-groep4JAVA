package domein;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author wim
 */
public class Departement {
    // needs JPA mapping!

    private final StringProperty naam = new SimpleStringProperty();

    // OneToMany (bidirectioneel met analyse) (er kunnen 1 of meerdere analyses voorkomen voor een dep.)
    private List<Analyse> analyses;

    public List<Analyse> getAnalyses() {
        return analyses;
    }

    //ManyToOne (bidirectioneel met werkgever)
    private Werkgever werkgever;

    public Departement(String naam) {
        setNaam(naam);
        analyses = new ArrayList<>();
    }

    public Werkgever getWerkgever() {
        return werkgever;
    }

    public void addAnalyse(Analyse a) {
        analyses.add(a);
    }

    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String value) {
        naam.set(value);
    }

    public StringProperty naamProperty() {
        return naam;
    }

}
