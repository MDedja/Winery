/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkaFakture;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaFakture;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllStavkaFakture extends AbstractSO {

    private ArrayList<StavkaFakture> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaFakture)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaFakture!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> stavkeFakture = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaFakture>) (ArrayList<?>) stavkeFakture;
    }

    public ArrayList<StavkaFakture> getLista() {
        return lista;
    }

}
