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
public class Firma extends AbstractDomainObject implements Serializable {
    
    private Long firmaID;
    private String PIB;
    private String nazivFirme;
    private String adresa;
    private String brojTelefona;
    private String email;

    @Override
    public String toString() {
        return nazivFirme;
    }

    public Firma(Long firmaID, String PIB, String nazivFirme, String adresa, String brojTelefona, String email) {
        this.firmaID = firmaID;
        this.PIB = PIB;
        this.nazivFirme = nazivFirme;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.email = email;
    }

    public Firma() {
    }
    
    @Override
    public String nazivTabele() {
        return " firma ";
    }
    
    @Override
    public String alijas() {
        return " f ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Firma f = new Firma(rs.getLong("FirmaID"),
                    rs.getString("PIB"), rs.getString("NazivFirme"),
                    rs.getString("Adresa"), rs.getString("BrojTelefona"),
                    rs.getString("Email"));

            lista.add(f);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (PIB, NazivFirme, Adresa, BrojTelefona, Email) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " FirmaID = " + firmaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + PIB + "', '" + nazivFirme + "', "
                + "'" + adresa + "', '" + brojTelefona + "', '" + email + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " adresa = '" + adresa + "', "
                + "brojTelefona = '" + brojTelefona + "', email = '" + email + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getFirmaID() {
        return firmaID;
    }

    public void setFirmaID(Long firmaID) {
        this.firmaID = firmaID;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getNazivFirme() {
        return nazivFirme;
    }

    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
