/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "kostofbaatrij")
@Cacheable(false)

public class KOBRij implements Serializable {

    protected KOBRij() {

    }
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "KostOfBaatid")
    private KostOfBaat kostOfBaat;

    public KostOfBaat getKostOfBaat() {
        return kostOfBaat;
    }

    public void setKostOfBaat(KostOfBaat kostOfBaat) {
        this.kostOfBaat = kostOfBaat;
    }

    @OneToMany(mappedBy = "kobrij",fetch = FetchType.EAGER)
    private List<KOBVak> vakken;

    @Transient
    public double resultaat;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "KOBRijId")
    // geen idee hoe da komt ma ik moe da misspellen of het mapt ni
    // <3 jpa 
    private int kOBRijId;

    public int getkOBRijId() {
        return kOBRijId;
    }

    public void setkOBRijId(int kOBRijId) {
        this.kOBRijId = kOBRijId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KOBVak> getVakken() {
        return vakken;
    }

    public void setVakken(List<KOBVak> vakken) {
        this.vakken = vakken;
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
        if (!(object instanceof KOBRij)) {
            return false;
        }
        KOBRij other = (KOBRij) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.KOBRij[ id=" + id + " ]";
    }

    public double getResultaat() {
        return resultaat;
    }

    public void setResultaat(double resultaat) {
        this.resultaat = resultaat;
    }

    public KOBVak geefKOBVakMetNummer(int i) {
        for (KOBVak vak : vakken) {
            if (vak.getKobVakId() == i) {
                return vak;
            }
        }
        return null;
    }

    public KOBRij(int vraag) {
        this.kOBRijId = vraag;
    }
}
