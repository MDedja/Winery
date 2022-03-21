/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.faktura;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Faktura;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllFaktura extends AbstractSO {

    private ArrayList<Faktura> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Faktura)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Faktura!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> fakture = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Faktura>) (ArrayList<?>) fakture;
    }

    public ArrayList<Faktura> getLista() {
        return lista;
    }

}
