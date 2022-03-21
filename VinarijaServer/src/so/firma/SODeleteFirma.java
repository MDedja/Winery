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
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteFirma extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Firma)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Firma!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().delete(ado);
    }

}
