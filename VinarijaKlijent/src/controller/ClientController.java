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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addFirma(Firma firma) throws Exception {
        sendRequest(Operation.ADD_FIRMA, firma);
    }

    public void addFaktura(Faktura faktura) throws Exception {
        sendRequest(Operation.ADD_FAKTURA, faktura);
    }

    public void addStavkaFakture(StavkaFakture stavkaFakture) throws Exception {
        sendRequest(Operation.ADD_STAVKA_FAKTURE, stavkaFakture);
    }

    public void addVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.ADD_VINARIJA, vinarija);
    }

    public void addVino(Vino vino) throws Exception {
        sendRequest(Operation.ADD_VINO, vino);
    }

    public void deleteVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.DELETE_VINARIJA, vinarija);
    }

    public void deleteVino(Vino vino) throws Exception {
        sendRequest(Operation.DELETE_VINO, vino);
    }

    public void deleteFirma(Firma firma) throws Exception {
        sendRequest(Operation.DELETE_FIRMA, firma);
    }

    public void updateFirma(Firma firma) throws Exception {
        sendRequest(Operation.UPDATE_FIRMA, firma);
    }

    public void updateVinarija(Vinarija vinarija) throws Exception {
        sendRequest(Operation.UPDATE_VINARIJA, vinarija);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Firma> getAllFirma() throws Exception {
        return (ArrayList<Firma>) sendRequest(Operation.GET_ALL_FIRMA, null);
    }

    public ArrayList<Faktura> getAllFaktura() throws Exception {
        return (ArrayList<Faktura>) sendRequest(Operation.GET_ALL_FAKTURA, null);
    }

    public ArrayList<StavkaFakture> getAllStavkaFakture() throws Exception {
        return (ArrayList<StavkaFakture>) sendRequest(Operation.GET_ALL_STAVKA_FAKTURE, null);
    }

    public ArrayList<Vinarija> getAllVinarija() throws Exception {
        return (ArrayList<Vinarija>) sendRequest(Operation.GET_ALL_VINARIJA, null);
    }

    public ArrayList<Vino> getAllVino(Vinarija v) throws Exception {
        return (ArrayList<Vino>) sendRequest(Operation.GET_ALL_VINO, v);
    }

    public ArrayList<VrstaVina> getAllVrstaVina() throws Exception {
        return (ArrayList<VrstaVina>) sendRequest(Operation.GET_ALL_VRSTA_VINA, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }
}
