/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author wimde
 */
@Cacheable(false)

@Entity
@Table(name = "kostofbaatvak")
public class KOBVak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOBRijid")
    public KOBRij kobrij;

    public KOBRij getKobrij() {
        return kobrij;
    }

    public void setKobrij(KOBRij kobrij) {
        this.kobrij = kobrij;
    }

    @Column(name = "KOBVakId")
    private int kobVakId;

    @Column(name = "Data")
    private String dataVak;

    public String getDataVak() {
        return dataVak;
    }

    public void setDataVak(String dataVak) {
        this.dataVak = dataVak;
    }

    public int getKobVakId() {
        return kobVakId;
    }

    public void setKobVakId(int kobVakId) {
        this.kobVakId = kobVakId;
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
        if (!(object instanceof KOBVak)) {
            return false;
        }
        KOBVak other = (KOBVak) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.KOBVak[ id=" + id + " ]";
    }

    public double geefDataAlsDouble() {
        return Double.parseDouble(dataVak.replace(",", "."));
    }
}
