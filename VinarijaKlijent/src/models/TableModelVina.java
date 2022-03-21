/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Vinarija;
import domain.Vino;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelVina extends AbstractTableModel {

    private ArrayList<Vino> lista;
    private String[] kolone = {"Rb", "Vrsta vina", "Naziv vina", "Mililitraza", "Cena"};
    private int rb = 0;
    
    public TableModelVina() {
        lista = new ArrayList<>();
    }

    public TableModelVina(Vinarija v) {
        try {
            lista = ClientController.getInstance().getAllVino(v);
        } catch (Exception ex) {
            Logger.getLogger(TableModelVina.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Vino v = lista.get(row);

        switch (column) {
            case 0:
                return v.getRbVinaVinarije();
            case 1:
                return v.getVrstaVina();
            case 2:
                return v.getNazivVina();
            case 3:
                return v.getMililitraza();
            case 4:
                return v.getCena() + "din";

            default:
                return null;
        }
    }

    public void dodajVino(Vino v) {
        rb = lista.size();
        v.setRbVinaVinarije(++rb);
        lista.add(v);
        fireTableDataChanged();
    }

    public boolean postojiNaziv(Vino v) {
        for (Vino vino : lista) {
            if(vino.getNazivVina().equals(v.getNazivVina())){
                return true;
            }
        }
        return false;
    }

    public void obrisiVino(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Vino vino : lista) {
            vino.setRbVinaVinarije(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<Vino> getLista() {
        return lista;
    }

}
