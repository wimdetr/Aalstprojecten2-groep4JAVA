/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ~dreeki~
 */
@Entity
@Table(name = "kostofbaatvak")
public class KOBVak implements Serializable{
    private final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "KOBVakId")
    private int id;
    @Column(name = "Data")
    private String data;

    protected KOBVak(){
        
    }
    
    public KOBVak(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void vulDataIn(String antwoord){
        this.data = antwoord;
    }
    
    public String geefData(){
        return data;
    }
    
    public double geefDataAlsDouble(){
        return Double.parseDouble(data);
    }
}
