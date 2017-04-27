package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "analyse")
public class Analyse implements Serializable {

    protected Analyse() {

    }
    

    private static final long serialVersionUID = 1L;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="JobCoachEmail")
    private JobCoach jobcoach;
    
    @OneToOne(mappedBy = "analyse")
    private Werkgever werkgever;

    public void setWerkgever(Werkgever werkgever) {
        this.werkgever = werkgever;
    }

    public Werkgever getWerkgever() {
        return werkgever;
    }

    @Id
    @Column(name = "AnalyseId")
    private int id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "AnalyseId", referencedColumnName = "AnalyseId")
    private List<KostOfBaat> kostenEnBaten;

    @Column(name = "LaatsteAanpasDatum")
    @Temporal(TemporalType.DATE)
    private Date laatsteAanpasDatum;

    public Analyse(int i, Date date) {
        id = i;
        laatsteAanpasDatum = date;
    }

    public Date getLaatsteAanpasDatum() {
        return laatsteAanpasDatum;
    }

    public void setLaatsteAanpasDatum(Date laatsteAanpasDatum) {
        this.laatsteAanpasDatum = laatsteAanpasDatum;
    }

    public List<KostOfBaat> getKostenEnBaten() {
        return kostenEnBaten;
    }

    public void setKostenEnBaten(List<KostOfBaat> kostenEnBaten) {
        this.kostenEnBaten = kostenEnBaten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(object instanceof Analyse)) {
            return false;
        }
        Analyse other = (Analyse) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Analyse[ id=" + id + " ]";
    }

    public void slaWerkgeverOp(Werkgever werk2) {
        setWerkgever(werk2);
    }

    boolean controleerOfKostMetNummerAlIngevuldIs(int kostId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    KostOfBaat geefKostMetNummer(int kostId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean controleerOfBaatMetNummerAlIngevuldIs(int kostOfBaatId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    KostOfBaat geefBaatMetNummer(int kostOfBaatId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Iterable<KostOfBaat> getBaten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Iterable<KostOfBaat> getKosten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
