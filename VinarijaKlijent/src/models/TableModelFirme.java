/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Firma;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelFirme extends AbstractTableModel implements Runnable {

    private ArrayList<Firma> lista;
    private String[] kolone = {"ID", "PIB", "Naziv firme", "Adresa", "Broj telefona", "Email"};
    private String parametar = "";

    public TableModelFirme() {
        try {
            lista = ClientController.getInstance().getAllFirma();
        } catch (Exception ex) {
            Logger.getLogger(TableModelFirme.class.getName()).log(Level.SEVERE, null, ex);
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
        Firma f = lista.get(row);

        switch (column) {
            case 0:
                return f.getFirmaID();
            case 1:
                return f.getPIB();
            case 2:
                return f.getNazivFirme();
            case 3:
                return f.getAdresa();
            case 4:
                return f.getBrojTelefona();
            case 5:
                return f.getEmail();

            default:
                return null;
        }
    }

    public Firma getSelectedFirma(int row) {
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
            Logger.getLogger(TableModelFirme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllFirma();
            if (!parametar.equals("")) {
                ArrayList<Firma> novaLista = new ArrayList<>();
                for (Firma f : lista) {
                    if (f.getNazivFirme().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(f);
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
