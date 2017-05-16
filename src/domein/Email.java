package domein;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "internemail")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InterneMailId")
    private Integer mailId;
    @Column(name = "Inhoud")
    private String inhoud;
    @Column(name = "Onderwerp")
    private String onderwerp;
    @Column(name = "VerzendDatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verzendDatum;

    @ManyToOne
    @JoinColumn(name = "AfzenderEmail")
    public Admin afzender;

    public Admin getAfzender() {
        return afzender;
    }

    public void setAfzender(Admin afzender) {
        this.afzender = afzender;
    }

    @OneToMany(mappedBy = "mail")
    private List<EmailJobCoach> ontvangers;

    public List<EmailJobCoach> getOntvangers() {
        return ontvangers;
    }

    public void setOntvangers(List<EmailJobCoach> ontvangers) {
        this.ontvangers = ontvangers;
    }

    public Email() {
    }

    public Email(Integer interneMailId) {
        this.mailId = interneMailId;
    }

    public Email(Admin afzender, String onderwerp, String inhoud) {
        this.inhoud = inhoud;
        this.onderwerp = onderwerp;
        this.afzender = afzender;
        verzendDatum = new Date();
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public Date getVerzendDatum() {
        return verzendDatum;
    }

    public void setVerzendDatum(Date verzendDatum) {
        this.verzendDatum = verzendDatum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mailId != null ? mailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.mailId == null && other.mailId != null) || (this.mailId != null && !this.mailId.equals(other.mailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domein.Internemail[ interneMailId=" + mailId + " ]";
    }

}
