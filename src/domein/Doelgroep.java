/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "doelgroep")
@NamedQueries({
    @NamedQuery(name = "Doelgroep.findAll", query = "SELECT d FROM Doelgroep d")})
public class Doelgroep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoelgroepId")
    private Integer doelgroepId;
    @Column(name = "DoelgroepMaxLoon")
    private double doelgroepMaxLoon;
    @Column(name = "DoelgroepText")
    private String doelgroepText;
    @Column(name = "DoelgroepWaarde")
    private double doelgroepWaarde;
    @Column(name = "IsVerwijdert")
    private boolean isVerwijderd;

    public Doelgroep() {
    }
    
    public Doelgroep(String doelgroepText,double doelgroepMaxLoon,double vermindering){
        this.doelgroepText= doelgroepText;
        this.doelgroepMaxLoon = doelgroepMaxLoon;
        this.doelgroepWaarde = vermindering;
        isVerwijderd = false;
    }

    public Integer getDoelgroepId() {
        return doelgroepId;
    }

    public void setDoelgroepId(Integer doelgroepId) {
        this.doelgroepId = doelgroepId;
    }

    public double getDoelgroepMaxLoon() {
        return doelgroepMaxLoon;
    }

    public void setDoelgroepMaxLoon(double doelgroepMaxLoon) {
        this.doelgroepMaxLoon = doelgroepMaxLoon;
    }

    public String getDoelgroepText() {
        return doelgroepText;
    }

    public void setDoelgroepText(String doelgroepText) {
        this.doelgroepText = doelgroepText;
    }

    public double getDoelgroepWaarde() {
        return doelgroepWaarde;
    }

    public void setDoelgroepWaarde(double doelgroepWaarde) {
        this.doelgroepWaarde = doelgroepWaarde;
    }

    public boolean getIsVerwijderd() {
        return isVerwijderd;
    }

    public void setIsVerwijderd(boolean isVerwijderd) {
        this.isVerwijderd = isVerwijderd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doelgroepId != null ? doelgroepId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doelgroep)) {
            return false;
        }
        Doelgroep other = (Doelgroep) object;
        if ((this.doelgroepId == null && other.doelgroepId != null) || (this.doelgroepId != null && !this.doelgroepId.equals(other.doelgroepId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domein.Doelgroep[ doelgroepId=" + doelgroepId + " ]";
    }

}
