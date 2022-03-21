/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Faktura;
import domain.Firma;
import domain.StavkaFakture;
import domain.Vinarija;
import domain.Vino;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_FAKTURA:
                    ServerController.getInstance().addFaktura((Faktura) req.getData());
                    break;
                case Operation.ADD_FIRMA:
                    ServerController.getInstance().addFirma((Firma) req.getData());
                    break;
                case Operation.ADD_STAVKA_FAKTURE:
                    ServerController.getInstance().addStavkaFakture((StavkaFakture) req.getData());
                    break;
                case Operation.ADD_VINO:
                    ServerController.getInstance().addVino((Vino) req.getData());
                    break;
                case Operation.ADD_VINARIJA:
                    ServerController.getInstance().addVinarija((Vinarija) req.getData());
                    break;
                case Operation.DELETE_FIRMA:
                    ServerController.getInstance().deleteFirma((Firma) req.getData());
                    break;
                case Operation.DELETE_VINARIJA:
                    ServerController.getInstance().deleteVinarija((Vinarija) req.getData());
                    break;
                case Operation.DELETE_VINO:
                    ServerController.getInstance().deleteVino((Vino) req.getData());
                    break;
                case Operation.UPDATE_FIRMA:
                    ServerController.getInstance().updateFirma((Firma) req.getData());
                    break;
                case Operation.UPDATE_VINARIJA:
                    ServerController.getInstance().updateVinarija((Vinarija) req.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    res.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_FIRMA:
                    res.setData(ServerController.getInstance().getAllFirma());
                    break;
                case Operation.GET_ALL_VINO:
                    res.setData(ServerController.getInstance().getAllVino((Vinarija)req.getData()));
                    break;
                case Operation.GET_ALL_VINARIJA:
                    res.setData(ServerController.getInstance().getAllVinarija());
                    break;
                case Operation.GET_ALL_FAKTURA:
                    res.setData(ServerController.getInstance().getAllFaktura());
                    break;
                case Operation.GET_ALL_STAVKA_FAKTURE:
                    res.setData(ServerController.getInstance().getAllStavkaFakture());
                    break;
                case Operation.GET_ALL_VRSTA_VINA:
                    res.setData(ServerController.getInstance().getAllVrstaVina());
                    break;
                case Operation.LOGIN:
                    ArrayList<Administrator> administratori = ServerController.getInstance().getAllAdministrator();
                    Administrator a = (Administrator) req.getData();
                    for (Administrator administrator : administratori) {
                        if (administrator.getUsername().equals(a.getUsername())
                                && administrator.getPassword().equals(a.getPassword())) {
                            res.setData(administrator);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
