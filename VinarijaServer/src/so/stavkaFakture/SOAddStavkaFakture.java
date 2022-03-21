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
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddStavkaFakture extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaFakture)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaFakture!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
