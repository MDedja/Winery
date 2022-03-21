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
public class VrstaVina extends AbstractDomainObject implements Serializable {
    
    private Long vrstaVinaID;
    private String nazivVrsteVina;

    @Override
    public String toString() {
        return nazivVrsteVina;
    }

    public VrstaVina(Long vrstaVinaID, String nazivVrsteVina) {
        this.vrstaVinaID = vrstaVinaID;
        this.nazivVrsteVina = nazivVrsteVina;
    }

    public VrstaVina() {
    }
    
    @Override
    public String nazivTabele() {
        return " vrstaVina ";
    }
    
    @Override
    public String alijas() {
        return " vv ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            VrstaVina vv = new VrstaVina(rs.getLong("VrstaVinaID"),
                    rs.getString("NazivVrsteVina"));

            lista.add(vv);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " VrstaVinaID = " + vrstaVinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getVrstaVinaID() {
        return vrstaVinaID;
    }

    public void setVrstaVinaID(Long vrstaVinaID) {
        this.vrstaVinaID = vrstaVinaID;
    }

    public String getNazivVrsteVina() {
        return nazivVrsteVina;
    }

    public void setNazivVrsteVina(String nazivVrsteVina) {
        this.nazivVrsteVina = nazivVrsteVina;
    }
}
