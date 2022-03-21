/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Vino extends AbstractDomainObject implements Serializable {
    
    private Vinarija vinarija;
    private int rbVinaVinarije;
    private String nazivVina;
    private int mililitraza;
    private String opis;
    private double cena;
    private VrstaVina vrstaVina;

    @Override
    public String toString() {
        return nazivVina + " (Vinarija: " + vinarija.getNazivVinarije() + ", Mililitraza: "
                + mililitraza + "ml, Cena: " + cena + "din)";
    }

    public Vino(Vinarija vinarija, int rbVinaVinarije, String nazivVina, int mililitraza, String opis, double cena, VrstaVina vrstaVina) {
        this.vinarija = vinarija;
        this.rbVinaVinarije = rbVinaVinarije;
        this.nazivVina = nazivVina;
        this.mililitraza = mililitraza;
        this.opis = opis;
        this.cena = cena;
        this.vrstaVina = vrstaVina;
    }

    public Vino() {
    }

    
    
    @Override
    public String nazivTabele() {
        return " vino ";
    }
    
    @Override
    public String alijas() {
        return " vin ";
    }

    @Override
    public String join() {
        return " JOIN VINARIJA V USING (VINARIJAID)"
                + " JOIN VRSTAVINA VV USING (VRSTAVINAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Vinarija v = new Vinarija(rs.getLong("VinarijaID"),
                    rs.getString("NazivVinarije"), rs.getString("Adresa"), null);
            
            VrstaVina vv = new VrstaVina(rs.getLong("VrstaVinaID"),
                    rs.getString("NazivVrsteVina"));
            
            Vino vino = new Vino(v, rs.getInt("rbVinaVinarije"), rs.getString("nazivVina"), 
                    rs.getInt("mililitraza"), rs.getString("opis"), rs.getDouble("cena"), vv);

            lista.add(vino);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (VinarijaID, rbVinaVinarije, nazivVina, mililitraza, opis, cena, vrstaVinaID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " VinarijaID = " + vinarija.getVinarijaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return vinarija.getVinarijaID() + ", " + rbVinaVinarije + ", "
                + "'" + nazivVina + "', " + mililitraza + ", '" + opis + "', "
                + cena + ", " + vrstaVina.getVrstaVinaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE V.VINARIJAID = " + vinarija.getVinarijaID();
    }

    public Vinarija getVinarija() {
        return vinarija;
    }

    public void setVinarija(Vinarija vinarija) {
        this.vinarija = vinarija;
    }

    public int getRbVinaVinarije() {
        return rbVinaVinarije;
    }

    public void setRbVinaVinarije(int rbVinaVinarije) {
        this.rbVinaVinarije = rbVinaVinarije;
    }

    public String getNazivVina() {
        return nazivVina;
    }

    public void setNazivVina(String nazivVina) {
        this.nazivVina = nazivVina;
    }

    public int getMililitraza() {
        return mililitraza;
    }

    public void setMililitraza(int mililitraza) {
        this.mililitraza = mililitraza;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public VrstaVina getVrstaVina() {
        return vrstaVina;
    }

    public void setVrstaVina(VrstaVina vrstaVina) {
        this.vrstaVina = vrstaVina;
    }

    
}
