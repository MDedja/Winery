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
public class SOUpdateFirma extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Firma)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Firma!");
        }

        Firma f = (Firma) ado;

        ArrayList<Firma> firme = (ArrayList<Firma>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Firma firma : firme) {
            if (!firma.getFirmaID().equals(f.getFirmaID())) {
                if (firma.getBrojTelefona().equals(f.getBrojTelefona())) {
                    throw new Exception("Vec postoji firma sa tim brojem telefona!");
                }
                if (firma.getEmail().equals(f.getEmail())) {
                    throw new Exception("Vec postoji firma sa tim emailom!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().update(ado);
    }

}
