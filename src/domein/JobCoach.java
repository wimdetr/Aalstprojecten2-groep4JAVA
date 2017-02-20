/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ~dreeki~
 */
public class JobCoach extends Persoon{
    private String naamBedrijf;
    private String straatBedrijf;
    private String nummerBedrijf;
    private int postcodeBedrijf;
    private String gemeenteBedrijf;
    private List<Analyse> analyses;

    public JobCoach(String naam, String voornaam, String email, String naamBedrijf, String straatBedrijf, String nummerBedrijf, int postcodeBedrijf, String gemeenteBedrijf) {
        super(naam, voornaam, email);
        this.naamBedrijf = naamBedrijf;
        this.straatBedrijf = straatBedrijf;
        this.nummerBedrijf = nummerBedrijf;
        this.postcodeBedrijf = postcodeBedrijf;
        this.gemeenteBedrijf = gemeenteBedrijf;
        analyses = new ArrayList<>();
    }

    public String getNaamBedrijf() {
        return naamBedrijf;
    }

    public void setNaamBedrijf(String naamBedrijf) {
        this.naamBedrijf = naamBedrijf;
    }

    public String getStraatBedrijf() {
        return straatBedrijf;
    }

    public void setStraatBedrijf(String straatBedrijf) {
        this.straatBedrijf = straatBedrijf;
    }

    public String getNummerBedrijf() {
        return nummerBedrijf;
    }

    public void setNummerBedrijf(String nummerBedrijf) {
        this.nummerBedrijf = nummerBedrijf;
    }

    public int getPostcodeBedrijf() {
        return postcodeBedrijf;
    }

    public void setPostcodeBedrijf(int postcodeBedrijf) {
        this.postcodeBedrijf = postcodeBedrijf;
    }

    public String getGemeenteBedrijf() {
        return gemeenteBedrijf;
    }

    public void setGemeenteBedrijf(String gemeenteBedrijf) {
        this.gemeenteBedrijf = gemeenteBedrijf;
    }

    public List<Analyse> getAnalyses() {
        return analyses;
    }
    
    public void voegAnalyseToe(Analyse a){
        analyses.add(a);
    }
}
