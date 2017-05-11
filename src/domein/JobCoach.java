
package domein;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "persoon")
@NamedQueries({
    @NamedQuery(name = "JobCoach.findAll", query = "SELECT p FROM JobCoach p")
}
)
@NamedNativeQuery(name = "findAnalyses",
        query = "SELECT * FROM analyse a WHERE a.JobCoachEmail =?1", resultClass = Analyse.class)
@Access(AccessType.FIELD)
public class JobCoach implements Serializable {

    // AFBLIJVEN!! Het werkt eindelijk met properties maar ik weet zelf ook niet hoe
    private static final long serialVersionUID = 1L;

    @Transient
    private final StringProperty email = new SimpleStringProperty();

    @OneToMany(mappedBy = "jobcoach", fetch = FetchType.EAGER)
    @Access(AccessType.FIELD)
    private List<Analyse> analyses;

    public List<Analyse> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analyse> analyses) {
        this.analyses = analyses;
    }

    @Id
    @Column(name = "Email")
    @Access(AccessType.PROPERTY)
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Transient
    private final StringProperty naam = new SimpleStringProperty();

    @Column(name = "Naam")
    @Access(AccessType.PROPERTY)
    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String value) {
        naam.set(value);
    }

    public StringProperty naamProperty() {
        return naam;
    }

    @Transient
    private final StringProperty voornaam = new SimpleStringProperty();

    @Column(name = "Voornaam")
    @Access(AccessType.PROPERTY)
    public String getVoornaam() {
        return voornaam.get();
    }

    public void setVoornaam(String value) {
        voornaam.set(value);
    }

    public StringProperty voornaamProperty() {
        return voornaam;
    }

    @Transient
    private final StringProperty busBedrijf = new SimpleStringProperty();

    @Column(name = "BusBedrijf")
    @Access(AccessType.PROPERTY)
    public String getBusBedrijf() {
        return busBedrijf.get();
    }

    public void setBusBedrijf(String value) {
        busBedrijf.set(value);
    }

    public StringProperty busBedrijfProperty() {
        return busBedrijf;
    }

    @Transient
    private final StringProperty gemeenteBedrijf = new SimpleStringProperty();

    @Column(name = "GemeenteBedrijf")
    @Access(AccessType.PROPERTY)
    public String getGemeenteBedrijf() {
        return gemeenteBedrijf.get();
    }

    public void setGemeenteBedrijf(String value) {
        gemeenteBedrijf.set(value);
    }

    public StringProperty gemeenteBedrijfProperty() {
        return gemeenteBedrijf;
    }

    @Transient
    private final StringProperty naamBedrijf = new SimpleStringProperty();

    @Column(name = "NaamBedrijf")
    @Access(AccessType.PROPERTY)
    public String getNaamBedrijf() {
        return naamBedrijf.get();
    }

    public void setNaamBedrijf(String value) {
        naamBedrijf.set(value);
    }

    public StringProperty naamBedrijfProperty() {
        return naamBedrijf;
    }

    @Transient
    private final IntegerProperty postcodeBedrijf = new SimpleIntegerProperty();

    @Column(name = "PostcodeBedrijf")
    @Access(AccessType.PROPERTY)
    public int getPostcodeBedrijf() {
        return postcodeBedrijf.get();
    }

    public void setPostcodeBedrijf(int value) {
        postcodeBedrijf.set(value);
    }

    public IntegerProperty postcodeBedrijfProperty() {
        return postcodeBedrijf;
    }

    @Transient
    private final StringProperty straatBedrijf = new SimpleStringProperty();

    @Column(name = "StraatBedrijf")
    @Access(AccessType.PROPERTY)
    public String getStraatBedrijf() {
        return straatBedrijf.get();
    }

    public void setStraatBedrijf(String value) {
        straatBedrijf.set(value);
    }

    public StringProperty straatBedrijfProperty() {
        return straatBedrijf;
    }

    @Transient
    private final IntegerProperty nummerBedrijf = new SimpleIntegerProperty();

    @Column(name = "NummerBedrijf")
    @Access(AccessType.PROPERTY)
    public int getNummerBedrijf() {
        return nummerBedrijf.get();
    }

    public void setNummerBedrijf(int value) {
        nummerBedrijf.set(value);
    }

    public IntegerProperty nummerBedrijfProperty() {
        return nummerBedrijf;
    }

    public JobCoach() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobCoach)) {
            return false;
        }
        JobCoach other = (JobCoach) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Transient
    private BooleanProperty checked = new SimpleBooleanProperty(false);

    public ObservableBooleanValue isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    @Override
    public String toString() {
        return "persistence.Persoon[ email=" + email + " ]";
    }

    public JobCoach(String naam, String voornaam, String email, String naamOrganisatie, String straatBedrijf, int nummerBedrijf, int postcodeBedrijf, String gemeenteBedrijf, String busBedrijf) {
        setNaam(naam);
        setVoornaam(voornaam);
        setEmail(email);
        setNaamBedrijf(naamOrganisatie);
        setStraatBedrijf(straatBedrijf);
        setNummerBedrijf(nummerBedrijf);
        setPostcodeBedrijf(postcodeBedrijf);
        setGemeenteBedrijf(gemeenteBedrijf);
        setBusBedrijf(busBedrijf);

    }
}
