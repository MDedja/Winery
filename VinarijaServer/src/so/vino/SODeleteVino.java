/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vino;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Vino;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteVino extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vino)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vino!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().delete(ado);
    }

}
