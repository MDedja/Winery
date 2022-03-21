/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.firma;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Firma;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllFirma extends AbstractSO {

    private ArrayList<Firma> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Firma)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Firma!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> firme = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Firma>) (ArrayList<?>) firme;
    }

    public ArrayList<Firma> getLista() {
        return lista;
    }

}
