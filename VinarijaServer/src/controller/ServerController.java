/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Faktura;
import domain.Firma;
import domain.StavkaFakture;
import domain.Vinarija;
import domain.Vino;
import domain.VrstaVina;
import java.util.ArrayList;
import so.AbstractSO;
import so.administrator.SOGetAllAdministrator;
import so.faktura.SOAddFaktura;
import so.faktura.SOGetAllFaktura;
import so.firma.SOAddFirma;
import so.firma.SODeleteFirma;
import so.firma.SOGetAllFirma;
import so.firma.SOUpdateFirma;
import so.stavkaFakture.SOAddStavkaFakture;
import so.stavkaFakture.SOGetAllStavkaFakture;
import so.vinarija.SOAddVinarija;
import so.vinarija.SODeleteVinarija;
import so.vinarija.SOGetAllVinarija;
import so.vinarija.SOUpdateVinarija;
import so.vino.SOAddVino;
import so.vino.SODeleteVino;
import so.vino.SOGetAllVino;
import so.vrstaVina.SOGetAllVrstaVina;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addFaktura(Faktura faktura) throws Exception {
        AbstractSO aso = new SOAddFaktura();
        aso.templateExecute(faktura);
    }

    public void addFirma(Firma firma) throws Exception {
        AbstractSO aso = new SOAddFirma();
        aso.templateExecute(firma);
    }

    public void addStavkaFakture(StavkaFakture stavkaFakture) throws Exception {
        AbstractSO aso = new SOAddStavkaFakture();
        aso.templateExecute(stavkaFakture);
    }

    public void addVinarija(Vinarija vinarija) throws Exception {
        AbstractSO aso = new SOAddVinarija();
        aso.templateExecute(vinarija);
    }

    public void addVino(Vino vino) throws Exception {
        AbstractSO aso = new SOAddVino();
        aso.templateExecute(vino);
    }

    public void deleteFirma(Firma firma) throws Exception {
        AbstractSO aso = new SODeleteFirma();
        aso.templateExecute(firma);
    }

    public void deleteVinarija(Vinarija vinarija) throws Exception {
        AbstractSO aso = new SODeleteVinarija();
        aso.templateExecute(vinarija);
    }

    public void deleteVino(Vino vino) throws Exception {
        AbstractSO aso = new SODeleteVino();
        aso.templateExecute(vino);
    }

    public void updateFirma(Firma firma) throws Exception {
        AbstractSO aso = new SOUpdateFirma();
        aso.templateExecute(firma);
    }

    public void updateVinarija(Vinarija vinarija) throws Exception {
        AbstractSO aso = new SOUpdateVinarija();
        aso.templateExecute(vinarija);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Firma> getAllFirma() throws Exception {
        SOGetAllFirma so = new SOGetAllFirma();
        so.templateExecute(new Firma());
        return so.getLista();
    }

    public ArrayList<Vinarija> getAllVinarija() throws Exception {
        SOGetAllVinarija so = new SOGetAllVinarija();
        so.templateExecute(new Vinarija());
        return so.getLista();
    }

    public ArrayList<Vino> getAllVino(Vinarija vinarija) throws Exception {
        SOGetAllVino so = new SOGetAllVino();
        
        Vino v = new Vino();
        v.setVinarija(vinarija);
        
        so.templateExecute(v);
        return so.getLista();
    }

    public ArrayList<Faktura> getAllFaktura() throws Exception {
        SOGetAllFaktura so = new SOGetAllFaktura();
        so.templateExecute(new Faktura());
        return so.getLista();
    }

    public ArrayList<StavkaFakture> getAllStavkaFakture() throws Exception {
        SOGetAllStavkaFakture so = new SOGetAllStavkaFakture();
        so.templateExecute(new VrstaVina());
        return so.getLista();
    }

    public ArrayList<VrstaVina> getAllVrstaVina() throws Exception {
        SOGetAllVrstaVina so = new SOGetAllVrstaVina();
        so.templateExecute(new VrstaVina());
        return so.getLista();
    }

}
