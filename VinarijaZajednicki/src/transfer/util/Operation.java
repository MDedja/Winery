/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int GET_ALL_ADMINISTRATOR = 1;

    public static final int ADD_VINARIJA = 2;
    public static final int DELETE_VINARIJA = 3;
    public static final int UPDATE_VINARIJA = 4;
    public static final int GET_ALL_VINARIJA = 5;

    public static final int ADD_VINO = 6;
    public static final int DELETE_VINO = 7;
    public static final int GET_ALL_VINO = 8;
    
    public static final int GET_ALL_VRSTA_VINA = 9;

    public static final int ADD_FIRMA = 10;
    public static final int DELETE_FIRMA = 11;
    public static final int UPDATE_FIRMA = 12;
    public static final int GET_ALL_FIRMA = 13;

    public static final int ADD_FAKTURA = 14;
    public static final int GET_ALL_FAKTURA = 15;
    
    public static final int ADD_STAVKA_FAKTURE = 16;
    public static final int GET_ALL_STAVKA_FAKTURE = 17;

}
