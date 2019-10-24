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
public class klasifikasi {

    private final StringProperty idk;
    private final StringProperty idbagk;
    private final StringProperty nmk;

    public klasifikasi(String idk, String idbagk, String nmk) {
        this.idk = new SimpleStringProperty (idk);
        this.idbagk = new SimpleStringProperty (idbagk);
        this.nmk = new SimpleStringProperty (nmk);
        
    }

    public final void setIdk(String value) {
        idk.set(value);
    }

    public final String getIdk() {
        return idk.get();
    }

    public final StringProperty idkProperty() {
        return idk;
    }

    public final void setIdbagk(String value) {
        idbagk.set(value);
    }

    public final String getIdbagk() {
        return idbagk.get();
    }

    public final StringProperty idbagkProperty() {
        return idbagk;
    }

    public final void setNmk(String value) {
        nmk.set(value);
    }

    public final String getNmk() {
        return nmk.get();
    }

    public final StringProperty nmkProperty() {
        return nmk;
    }
    
}
