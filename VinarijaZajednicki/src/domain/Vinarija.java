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
public class Vinarija extends AbstractDomainObject implements Serializable {
    
    private Long vinarijaID;
    private String nazivVinarije;
    private String adresa;
    private ArrayList<Vino> listaVina;

    @Override
    public String toString() {
        return nazivVinarije;
    }

    public Vinarija(Long vinarijaID, String nazivVinarije, String adresa, ArrayList<Vino> listaVina) {
        this.vinarijaID = vinarijaID;
        this.nazivVinarije = nazivVinarije;
        this.adresa = adresa;
        this.listaVina = listaVina;
    }
    public Vinarija() {
    }
    
    @Override
    public String nazivTabele() {
        return " vinarija ";
    }
    
    @Override
    public String alijas() {
        return " v ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Vinarija v = new Vinarija(rs.getLong("VinarijaID"),
                    rs.getString("NazivVinarije"), rs.getString("Adresa"), null);

            lista.add(v);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivVinarije, Adresa) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " VinarijaID = " + vinarijaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivVinarije + "', '" + adresa + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Adresa = '" + adresa + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getVinarijaID() {
        return vinarijaID;
    }

    public void setVinarijaID(Long vinarijaID) {
        this.vinarijaID = vinarijaID;
    }

    public String getNazivVinarije() {
        return nazivVinarije;
    }

    public void setNazivVinarije(String nazivVinarije) {
        this.nazivVinarije = nazivVinarije;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public ArrayList<Vino> getListaVina() {
        return listaVina;
    }

    public void setListaVina(ArrayList<Vino> listaVina) {
        this.listaVina = listaVina;
    }
}
