/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vrstaVina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VrstaVina;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllVrstaVina extends AbstractSO {

    private ArrayList<VrstaVina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VrstaVina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaVina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> vrsteVina = DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaVina>) (ArrayList<?>) vrsteVina;
    }

    public ArrayList<VrstaVina> getLista() {
        return lista;
    }

}
