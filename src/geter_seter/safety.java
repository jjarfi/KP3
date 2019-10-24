/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geter_seter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ASUS
 */
public class safety {
    private final IntegerProperty idsaf;
    private final IntegerProperty nosepatu;
    private final StringProperty nobaju;
    private final IntegerProperty nocelana;
    private final StringProperty nohelm;
    private final IntegerProperty nosarung;
    private final StringProperty idkars;

    public safety(int idsaf,int nosepatu, String nobaju, int nocelana, String nohelm, int nosarung, String idkars) {
        this.idsaf = new SimpleIntegerProperty(idsaf);
        this.nosepatu = new SimpleIntegerProperty(nosepatu);
        this.nobaju = new SimpleStringProperty(nobaju);
        this.nocelana = new SimpleIntegerProperty(nocelana);
        this.nohelm = new SimpleStringProperty(nohelm);
        this.nosarung = new SimpleIntegerProperty(nosarung);
        this.idkars = new SimpleStringProperty(idkars);
    }

    public final void setIdsaf(Integer value) {
        idsaf.set(value);
    }

    public final Integer getIdsaf() {
        return idsaf.get();
    }

    public final IntegerProperty idsafProperty() {
        return idsaf;
    }

    public final void setNosepatu(Integer value) {
        nosepatu.set(value);
    }

    public final Integer getNosepatu() {
        return nosepatu.get();
    }

    public final IntegerProperty nosepatuProperty() {
        return nosepatu;
    }

    public final void setNobaju(String value) {
        nobaju.set(value);
    }

    public final String getNobaju() {
        return nobaju.get();
    }

    public final StringProperty nobajuProperty() {
        return nobaju;
    }

    public final void setNocelana(Integer value) {
        nocelana.set(value);
    }

    public final Integer getNocelana() {
        return nocelana.get();
    }

    public final IntegerProperty nocelanaProperty() {
        return nocelana;
    }

    public final void setNohelm(String value) {
        nohelm.set(value);
    }

    public final String getNohelm() {
        return nohelm.get();
    }

    public final StringProperty nohelmProperty() {
        return nohelm;
    }

    public final void setNosarung(Integer value) {
        nosarung.set(value);
    }

    public final Integer getNosarung() {
        return nosarung.get();
    }

    public final IntegerProperty nosarungProperty() {
        return nosarung;
    }

    public final void setIdkars(String value) {
        idkars.set(value);
    }

    public final String getIdkars() {
        return idkars.get();
    }

    public final StringProperty idkarsProperty() {
        return idkars;
    }

}
