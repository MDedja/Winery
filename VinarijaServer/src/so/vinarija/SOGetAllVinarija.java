/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vinarija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Vinarija;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllVinarija extends AbstractSO {

    private ArrayList<Vinarija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vinarija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vinarija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> vinarije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Vinarija>) (ArrayList<?>) vinarije;
    }

    public ArrayList<Vinarija> getLista() {
        return lista;
    }

}
