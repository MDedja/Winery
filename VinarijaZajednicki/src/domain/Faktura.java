/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Faktura extends AbstractDomainObject implements Serializable {
    
    private Long fakturaID;
    private Date datumVreme;
    private double cenaBezPDV;
    private double PDV;
    private double cenaSaPDV;
    private Firma firma;
    private Administrator administrator;
    private ArrayList<StavkaFakture> listaStavki;

    public Faktura(Long fakturaID, Date datumVreme, double cenaBezPDV, double PDV, double cenaSaPDV, Firma firma, Administrator administrator, ArrayList<StavkaFakture> listaStavki) {
        this.fakturaID = fakturaID;
        this.datumVreme = datumVreme;
        this.cenaBezPDV = cenaBezPDV;
        this.PDV = PDV;
        this.cenaSaPDV = cenaSaPDV;
        this.firma = firma;
        this.administrator = administrator;
        this.listaStavki = listaStavki;
    }

    public Faktura() {
    }
    
    @Override
    public String nazivTabele() {
        return " faktura ";
    }
    
    @Override
    public String alijas() {
        return " fa ";
    }

    @Override
    public String join() {
        return " JOIN FIRMA F USING (FIRMAID)"
                + " JOIN ADMINISTRATOR A USING (ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"),
                    rs.getString("Prezime"),
                    rs.getString("Username"),
                    rs.getString("Password"));
            
            Firma f = new Firma(rs.getLong("FirmaID"),
                    rs.getString("PIB"),
                    rs.getString("NazivFirme"),
                    rs.getString("Adresa"),
                    rs.getString("BrojTelefona"),
                    rs.getString("Email"));
            
            Faktura faktura = new Faktura(rs.getLong("fakturaID"), 
                    rs.getTimestamp("datumVreme"),
                    rs.getDouble("cenaBezPDV"), 
                    rs.getDouble("PDV"),
                    rs.getDouble("cenaSaPDV"), f, a, null);

            lista.add(faktura);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, cenaBezPDV, PDV, cenaSaPDV, firmaID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " fakturaID = " + fakturaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', " + cenaBezPDV + ", "
                + PDV + ", " + cenaSaPDV + ", " + firma.getFirmaID() + ", " 
                + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getFakturaID() {
        return fakturaID;
    }

    public void setFakturaID(Long fakturaID) {
        this.fakturaID = fakturaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getCenaBezPDV() {
        return cenaBezPDV;
    }

    public void setCenaBezPDV(double cenaBezPDV) {
        this.cenaBezPDV = cenaBezPDV;
    }

    public double getPDV() {
        return PDV;
    }

    public void setPDV(double PDV) {
        this.PDV = PDV;
    }

    public double getCenaSaPDV() {
        return cenaSaPDV;
    }

    public void setCenaSaPDV(double cenaSaPDV) {
        this.cenaSaPDV = cenaSaPDV;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaFakture> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaFakture> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public String toString() {
        return fakturaID.toString();
    }
    
    
}
