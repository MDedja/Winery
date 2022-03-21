/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Vinarija;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelVinarije extends AbstractTableModel implements Runnable {

    private ArrayList<Vinarija> lista;
    private String[] kolone = {"ID", "Naziv vinarije", "Adresa"};
    private String parametar = "";

    public TableModelVinarije() {
        try {
            lista = ClientController.getInstance().getAllVinarija();
        } catch (Exception ex) {
            Logger.getLogger(TableModelVinarije.class.getName()).log(Level.SEVERE, null, ex);
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
        Vinarija v = lista.get(row);

        switch (column) {
            case 0:
                return v.getVinarijaID();
            case 1:
                return v.getNazivVinarije();
            case 2:
                return v.getAdresa();

            default:
                return null;
        }
    }

    public Vinarija getSelectedVinarija(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelVinarije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllVinarija();
            if (!parametar.equals("")) {
                ArrayList<Vinarija> novaLista = new ArrayList<>();
                for (Vinarija v : lista) {
                    if (v.getNazivVinarije().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(v);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
