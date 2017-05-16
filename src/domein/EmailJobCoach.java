/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author wimde
 */
@Entity
@Table(name = "internemailjobcoaches")
public class EmailJobCoach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "IsGelezen")
    private boolean isGelezen;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InterneMailId")
    private Email mail;

    @ManyToOne
    @JoinColumn(name = "JobcoachEmail")
    private JobCoach coach;

    public JobCoach getCoach() {
        return coach;
    }

    public void setCoach(JobCoach coach) {
        this.coach = coach;
    }

    public EmailJobCoach() {
    }

    public EmailJobCoach(Email e, JobCoach j) {
        mail = e;
        coach = j;
        isGelezen = false;
    }

    public boolean getIsGelezen() {
        return isGelezen;
    }

    public void setIsGelezen(boolean isGelezen) {
        this.isGelezen = isGelezen;
    }

    @Override
    public String toString() {
        return "domein.Internemailjobcoaches[ internemailjobcoachesPK=" + id + " ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
