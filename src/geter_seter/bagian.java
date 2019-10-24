/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geter_seter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author pacevil
 */
public class bagian {

    private final StringProperty idb;
    private final StringProperty idperb;
    private final StringProperty tglb;
    private final StringProperty nmb;

    public bagian(String idb, String idperb, String tglb, String nmb) {
        this.idb = new SimpleStringProperty (idb);
        this.idperb = new SimpleStringProperty (idperb);
        this.tglb = new SimpleStringProperty (tglb);
        this.nmb = new SimpleStringProperty (nmb);
    }

    public final void setIdb(String value) {
        idb.set(value);
    }

    public final String getIdb() {
        return idb.get();
    }

    public final StringProperty idbProperty() {
        return idb;
    }

    public final void setIdperb(String value) {
        idperb.set(value);
    }

    public final String getIdperb() {
        return idperb.get();
    }

    public final StringProperty idperbProperty() {
        return idperb;
    }

    public final void setTglb(String value) {
        tglb.set(value);
    }

    public final String getTglb() {
        return tglb.get();
    }

    public final StringProperty tglbProperty() {
        return tglb;
    }

    public final void setNmb(String value) {
        nmb.set(value);
    }

    public final String getNmb() {
        return nmb.get();
    }

    public final StringProperty nmbProperty() {
        return nmb;
    }
    
}
