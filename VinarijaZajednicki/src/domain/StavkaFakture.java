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
public class StavkaFakture extends AbstractDomainObject implements Serializable {

    private Faktura faktura;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private Vino vino;

    public StavkaFakture(Faktura faktura, int rbStavke, int kolicina, double cenaStavke, Vino vino) {
        this.faktura = faktura;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.vino = vino;
    }

    public StavkaFakture() {
    }

    @Override
    public String nazivTabele() {
        return " stavkaFakture ";
    }

    @Override
    public String alijas() {
        return " sf ";
    }

    @Override
    public String join() {
        return " JOIN FAKTURA FAK USING (FAKTURAID) "
                + "JOIN VINO VIN ON (VIN.VINARIJAID = SF.VINARIJAID AND VIN.RBVINAVINARIJE = SF.RBVINAVINARIJE) "
                + "JOIN FIRMA F ON (F.FIRMAID = FAK.FIRMAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = FAK.ADMINISTRATORID) "
                + "JOIN VRSTAVINA VV ON (VV.VRSTAVINAID = VIN.VRSTAVINAID) "
                + "JOIN VINARIJA V ON (V.VINARIJAID = VIN.VINARIJAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Firma f = new Firma(rs.getLong("FirmaID"),
                    rs.getString("PIB"), rs.getString("NazivFirme"),
                    rs.getString("Adresa"), rs.getString("BrojTelefona"),
                    rs.getString("Email"));

            Faktura faktura = new Faktura(rs.getLong("fakturaID"),
                    rs.getTimestamp("datumVreme"), rs.getDouble("cenaBezPDV"),
                    rs.getDouble("PDV"), rs.getDouble("cenaSaPDV"), f, a, null);

            Vinarija v = new Vinarija(rs.getLong("VinarijaID"),
                    rs.getString("NazivVinarije"), rs.getString("Adresa"), null);

            VrstaVina vv = new VrstaVina(rs.getLong("VrstaVinaID"),
                    rs.getString("NazivVrsteVina"));

            Vino vino = new Vino(v, rs.getInt("rbVinaVinarije"), rs.getString("nazivVina"),
                    rs.getInt("mililitraza"), rs.getString("opis"), rs.getDouble("cena"), vv);

            StavkaFakture sf = new StavkaFakture(faktura, rs.getInt("rbStavke"),
                    rs.getInt("kolicina"), rs.getDouble("cenaStavke"), vino);

            lista.add(sf);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (fakturaID, rbStavke, kolicina, cenaStavke, vinarijaID, rbVinaVinarije) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " fakturaID = " + faktura.getFakturaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return faktura.getFakturaID() + ", " + rbStavke + ", "
                + kolicina + ", " + cenaStavke + ", " + vino.getVinarija().getVinarijaID()
                + ", " + vino.getRbVinaVinarije();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE F.FAKTURAID = " + faktura.getFakturaID();
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Vino getVino() {
        return vino;
    }

    public void setVino(Vino vino) {
        this.vino = vino;
    }
}
