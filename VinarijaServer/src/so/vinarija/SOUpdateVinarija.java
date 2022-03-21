/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vinarija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Vinarija;
import domain.Vino;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateVinarija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vinarija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vinarija!");
        }

        Vinarija v = (Vinarija) ado;

        if (v.getListaVina().size() < 3) {
            throw new Exception("Vinarija mora imati barem 3 vrste vina!");
        }

        ArrayList<Vinarija> vinarije = (ArrayList<Vinarija>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Vinarija vinarija : vinarije) {
            if (!vinarija.getVinarijaID().equals(v.getVinarijaID())) {
                if (vinarija.getNazivVinarije().equals(v.getNazivVinarije())) {
                    throw new Exception("Vec postoji vinarija sa tim nazivom!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        // prvo updatujemo vinariju
        DBBroker.getInstance().update(ado);
        
        Vinarija v = (Vinarija) ado;
        // brisemo sva stara vina vinarije
        // ova linija koda izvrsava naredbu
        // DELETE FROM VINO WHERE VINARIJAID = nekiID
        // cime se brisu SVA vina te vinarije ODJEDNOM !!!
        DBBroker.getInstance().delete(v.getListaVina().get(0));
        
        // dodajemo nova
        for (Vino vino : v.getListaVina()) {
            DBBroker.getInstance().insert(vino);
        }
        
    }

}
