/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author ~dreeki~
 */
public class KOBVak {
    private int id;
    private String data;

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
