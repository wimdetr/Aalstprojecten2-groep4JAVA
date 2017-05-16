/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "adminmail")
public class AdminMail implements Serializable, Comparable<AdminMail> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminMailId")
    private Integer adminMailId;
    @Column(name = "Inhoud")
    private String inhoud;
    @Column(name = "IsGelezen")
    private boolean isGelezen;
    @Column(name = "Onderwerp")
    private String onderwerp;
    @Column(name = "VerzendDatum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verzendDatum;

    @ManyToOne
    @JoinColumn(name = "AfzenderMail")
    private JobCoach afzender;

    public JobCoach getAfzender() {
        return afzender;
    }

    public void setAfzender(JobCoach afzender) {
        this.afzender = afzender;
    }

    public AdminMail() {
    }

    public Integer getAdminMailId() {
        return adminMailId;
    }

    public void setAdminMailId(Integer adminMailId) {
        this.adminMailId = adminMailId;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
    }

    public boolean getIsGelezen() {
        return isGelezen;
    }

    public void setIsGelezen(boolean isGelezen) {
        this.isGelezen = isGelezen;
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
        hash += (adminMailId != null ? adminMailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminMail)) {
            return false;
        }
        AdminMail other = (AdminMail) object;
        if ((this.adminMailId == null && other.adminMailId != null) || (this.adminMailId != null && !this.adminMailId.equals(other.adminMailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domein.Adminmail[ adminMailId=" + adminMailId + " ]";
    }

    @Override
    public int compareTo(AdminMail a) {
        return getVerzendDatum().compareTo(a.getVerzendDatum());

    }

}
