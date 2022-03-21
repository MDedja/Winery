/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.StavkaFakture;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelStavkeFakture extends AbstractTableModel {

    private ArrayList<StavkaFakture> lista;
    private String[] kolone = {"Rb", "Vinarija", "Vino", "Kolicina", "Mililitraza", "Cena stavke"};
    private int rb = 0;
    
    public TableModelStavkeFakture() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaFakture sf = lista.get(row);

        switch (column) {
            case 0:
                return sf.getRbStavke();
            case 1:
                return sf.getVino().getVinarija();
            case 2:
                return sf.getVino().getNazivVina();
            case 3:
                return sf.getKolicina();
            case 4:
                return sf.getVino().getMililitraza() + "ml";
            case 5:
                return sf.getCenaStavke() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaFakture sf) {
    
        for (StavkaFakture stavkaFakture : lista) {
            if((stavkaFakture.getVino().getVinarija().getVinarijaID()
                    .equals(sf.getVino().getVinarija().getVinarijaID())
                    && stavkaFakture.getVino().getRbVinaVinarije() == 
                    sf.getVino().getRbVinaVinarije())){
                stavkaFakture.setKolicina(stavkaFakture.getKolicina() + sf.getKolicina());
                stavkaFakture.setCenaStavke(stavkaFakture.getCenaStavke() + sf.getCenaStavke());
                fireTableDataChanged();
                return;
            }
        }
        
        rb = lista.size();
        sf.setRbStavke(++rb);
        lista.add(sf);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);
        
        rb = 0;
        for (StavkaFakture stavkaFakture : lista) {
            stavkaFakture.setRbStavke(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<StavkaFakture> getLista() {
        return lista;
    }

    public StavkaFakture getStavka(int row) {
        return lista.get(row);
    }

    public void isprazniTabelu() {
        lista.clear();
        fireTableDataChanged();
    }

}
