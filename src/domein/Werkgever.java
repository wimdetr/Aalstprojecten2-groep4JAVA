/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Werkgever.findAll", query = "SELECT w FROM Werkgever w")})
public class Werkgever implements Serializable {

    protected Werkgever() {

    }

    @OneToMany(mappedBy = "werkgever",fetch = FetchType.EAGER)
    private List<Departement> departementen;

    public List<Departement> getDepartementen() {
        return departementen;
    }

    public void setDepartementen(List<Departement> departementen) {
        this.departementen = departementen;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WerkgeverId")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Werkgever(String bedrijf, int i, String gemeente) {
        naam = bedrijf;
        id = i;
    }

    @Column(name = "Naam")
    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Column(name = "PatronaleBijdrage")
    private int patronaleBijdrage;

    public int getPatronaleBijdrage() {
        return patronaleBijdrage;
    }

    public void setPatronaleBijdrage(int patronaleBijdrage) {
        this.patronaleBijdrage = patronaleBijdrage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Werkgever)) {
            return false;
        }
        Werkgever other = (Werkgever) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Werkgever[ id=" + id + " ]";
    }

}
