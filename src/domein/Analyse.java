package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "analyse")
@NamedQueries({
    @NamedQuery(name = "Analyse.findAll", query = "SELECT a FROM Analyse a")})
public class Analyse implements Serializable,Comparable<Analyse> {

    protected Analyse() {

    }

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JobCoachEmail")
    private JobCoach jobcoach;

    public JobCoach getJobcoach() {
        return jobcoach;
    }

    public void setJobcoach(JobCoach jobcoach) {
        this.jobcoach = jobcoach;
    }

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

    @Transient
    private double resultaat;

    @Transient
    private double resultaatBaten;

    @Transient
    private double resultaatKosten;

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

    public double getResultaat() {
        return resultaat;
    }

    public void setResultaat(double resultaat) {
        this.resultaat = resultaat;
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

    public boolean controleerOfVraagNummerAlIngevuldIs(KOBEnum e, int vraagnummer) {
        for (KostOfBaat kob : kostenEnBaten) {
            if (kob.getVraagId() == vraagnummer && kob.getKobEnum() == e) {
                return true;
            }
        }
        return false;
    }

    public KostOfBaat geefKostOfBaatMetNummer(KOBEnum e, int vraagnummer) {
        for (KostOfBaat kob : kostenEnBaten) {
            if (kob.getVraagId() == vraagnummer && kob.getKobEnum() == e) {
                return kob;
            }
        }
        return null;
    }

    public double getResultaatBaten() {
        return resultaatBaten;
    }

    public double getResultaatKosten() {
        return resultaatKosten;
    }

    private double berekenSubTotaal(KOBEnum e) {
        double sum = 0;
        for (KostOfBaat kob : kostenEnBaten) {
            if (kob.getKobEnum() == e) {
                sum += kob.getResultaat();
            }
        }
        return sum;
    }

    public void berekenVolledigResultaat() {
        resultaatBaten = round(berekenSubTotaal(KOBEnum.Baat));
        resultaatKosten = round(berekenSubTotaal(KOBEnum.Kost));
        resultaat = round(resultaatBaten - resultaatKosten);
    }

    private double round(double a) {
        return Math.round(a * 100.00) / 100.00;
    }
    
    @Override
    public int compareTo(Analyse a){
        return getLaatsteAanpasDatum().compareTo(a.getLaatsteAanpasDatum());
    }

}
