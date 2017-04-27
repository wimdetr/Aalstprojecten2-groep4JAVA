/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "kostofbaat")
public class KostOfBaat implements Serializable {

    protected KostOfBaat() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "VraagId")
    private int vraagId;

    @Column(name = "KostOfBaatEnum")
    private KOBEnum kobEnum;

    @Column(name = "Formule")
    private Formule formule;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "KostOfBaatid", referencedColumnName = "id")
    private List<KOBRij> rijen;

    @Transient
    private double resultaat;

    public int getVraagId() {
        return vraagId;
    }

    public void setVraagId(int vraagId) {
        this.vraagId = vraagId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KOBEnum getKobEnum() {
        return kobEnum;
    }

    public void setKobEnum(KOBEnum kobEnum) {
        this.kobEnum = kobEnum;
    }

    public double getResultaat() {
        return resultaat;
    }

    public void setResultaat(double resultaat) {
        this.resultaat = resultaat;
    }

    public List<KOBRij> getRijen() {
        return rijen;
    }

    public void setRijen(List<KOBRij> rijen) {
        this.rijen = rijen;
    }

    public void setFormule(Formule formule) {
        this.formule = formule;
    }

    public Formule getFormule() {
        return formule;
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
        if (!(object instanceof KostOfBaat)) {
            return false;
        }
        KostOfBaat other = (KostOfBaat) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.KostOfBaat[ id=" + id + " ]";
    }

    public boolean controleerOfKOBRijMetNummerAlIngevuldIs(int rijId) {
        try{
            rijen.get(rijId);
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }

    public KOBRij geefKOBRijMetNummer(int rijId) {
        return rijen.get(rijId);
    }

    void berekenResultaat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
