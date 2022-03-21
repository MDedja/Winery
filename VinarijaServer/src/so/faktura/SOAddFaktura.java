/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.faktura;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Faktura;
import domain.StavkaFakture;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddFaktura extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Faktura)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Faktura!");
        }

        Faktura f = (Faktura) ado;

        if (f.getListaStavki().isEmpty()) {
            throw new Exception("Faktura mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet kljuc = ps.getGeneratedKeys();
        kljuc.next();
        Long faktura = kljuc.getLong(1);
        
        Faktura f = (Faktura) ado;
        f.setFakturaID(faktura);
        
        for (StavkaFakture stavkaFakture : f.getListaStavki()) {
            stavkaFakture.setFaktura(f);
            DBBroker.getInstance().insert(stavkaFakture);
        }
    }

}
