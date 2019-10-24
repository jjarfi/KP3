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
public class perusahaan {
    private final StringProperty idp;
    private final StringProperty namap;
    private final StringProperty direkturp;
    private final StringProperty wadirp;
    private final StringProperty tglp;
    private final StringProperty nomorp;

    public perusahaan(String idp, String namap, String direkturp, String wadirp, String tglp, String nomorp) {
        this.idp = new SimpleStringProperty (idp);
        this.namap = new SimpleStringProperty (namap);
        this.direkturp = new SimpleStringProperty (direkturp);
        this.wadirp = new SimpleStringProperty (wadirp);
        this.tglp = new SimpleStringProperty (tglp);
        this.nomorp = new SimpleStringProperty (nomorp);
    }

    public final void setIdp(String value) {
        idp.set(value);
    }

    public final String getIdp() {
        return idp.get();
    }

    public final StringProperty idpProperty() {
        return idp;
    }

    public final void setNamap(String value) {
        namap.set(value);
    }

    public final String getNamap() {
        return namap.get();
    }

    public final StringProperty namapProperty() {
        return namap;
    }

    public final void setDirekturp(String value) {
        direkturp.set(value);
    }

    public final String getDirekturp() {
        return direkturp.get();
    }

    public final StringProperty direkturpProperty() {
        return direkturp;
    }

    public final void setWadirp(String value) {
        wadirp.set(value);
    }

    public final String getWadirp() {
        return wadirp.get();
    }

    public final StringProperty wadirpProperty() {
        return wadirp;
    }

    public final void setTglp(String value) {
        tglp.set(value);
    }

    public final String getTglp() {
        return tglp.get();
    }

    public final StringProperty tglpProperty() {
        return tglp;
    }

    public final void setNomorp(String value) {
        nomorp.set(value);
    }

    public final String getNomorp() {
        return nomorp.get();
    }

    public final StringProperty nomorpProperty() {
        return nomorp;
    }

   
}
