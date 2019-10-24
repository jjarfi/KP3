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
public class karyawan {

    private final StringProperty idk;
    private final StringProperty namak;
    private final StringProperty tempatk;
    private final StringProperty tglk;
    private final StringProperty jkk;
    private final StringProperty agamak;
    private final StringProperty alamatk;
    private final StringProperty stkark;
    private final StringProperty pendidikank;
    private final StringProperty tglmak;
    private final StringProperty nohpk;
    private final StringProperty idklak;
    private final StringProperty noktpk;
    private final StringProperty norekk;
    private final StringProperty nobpjskenakerk;
    private final StringProperty nobpjskesehatank;
    private final StringProperty npwpk;
    private final StringProperty emailk;
    private final StringProperty suratk;
    private final StringProperty fotoktpk;
    private final StringProperty fotokkk;
    private final StringProperty fotoijasahk;
    private final StringProperty fotonpwpk;
    private final StringProperty fotobpjskenakerk;
    private final StringProperty fotobpjssehatk;
    private final StringProperty pasfotok;
    private final StringProperty fotok;

    public karyawan(String idk, String namak, String tempatk, String tglk, String jkk, String agamak, String alamatk, String stkark, String pendidikank, String tglmak, String nohpk, String idklak, String noktpk, String norekk, String nobpjskenakerk, String nobpjskesehatank, String npwpk, String emailk, String suratk, String fotoktpk, String fotokkk, String fotoijasahk, String fotonpwpk, String fotobpjskenakerk, String fotobpjssehatk, String pasfotok, String fotok) {
        this.idk = new SimpleStringProperty(idk);
        this.namak = new SimpleStringProperty(namak);
        this.tempatk = new SimpleStringProperty(tempatk);
        this.tglk = new SimpleStringProperty(tglk);
        this.jkk = new SimpleStringProperty(jkk);
        this.agamak = new SimpleStringProperty(agamak);
        this.alamatk = new SimpleStringProperty(alamatk);
        this.stkark = new SimpleStringProperty(stkark);
        this.pendidikank = new SimpleStringProperty(pendidikank);
        this.tglmak = new SimpleStringProperty(tglmak);
        this.nohpk = new SimpleStringProperty(nohpk);
        this.idklak = new SimpleStringProperty(idklak);
        this.noktpk = new SimpleStringProperty(noktpk);
        this.norekk = new SimpleStringProperty(norekk);
        this.nobpjskenakerk = new SimpleStringProperty(nobpjskenakerk);
        this.nobpjskesehatank = new SimpleStringProperty(nobpjskesehatank);
        this.npwpk = new SimpleStringProperty(npwpk);
        this.emailk = new SimpleStringProperty(emailk);
        this.suratk = new SimpleStringProperty(suratk);
        this.fotoktpk = new SimpleStringProperty(fotoktpk);
        this.fotokkk = new SimpleStringProperty(fotokkk);
        this.fotoijasahk = new SimpleStringProperty(fotoijasahk);
        this.fotonpwpk = new SimpleStringProperty(fotonpwpk);
        this.fotobpjskenakerk = new SimpleStringProperty(fotobpjskenakerk);
        this.fotobpjssehatk = new SimpleStringProperty(fotobpjssehatk);
        this.pasfotok = new SimpleStringProperty(pasfotok);
        this.fotok = new SimpleStringProperty (fotok);
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

    public final void setNamak(String value) {
        namak.set(value);
    }

    public final String getNamak() {
        return namak.get();
    }

    public final StringProperty namakProperty() {
        return namak;
    }

    public final void setTempatk(String value) {
        tempatk.set(value);
    }

    public final String getTempatk() {
        return tempatk.get();
    }

    public final StringProperty tempatkProperty() {
        return tempatk;
    }

    public final void setTglk(String value) {
        tglk.set(value);
    }

    public final String getTglk() {
        return tglk.get();
    }

    public final StringProperty tglkProperty() {
        return tglk;
    }

    public final void setJkk(String value) {
        jkk.set(value);
    }

    public final String getJkk() {
        return jkk.get();
    }

    public final StringProperty jkkProperty() {
        return jkk;
    }

    public final void setAgamak(String value) {
        agamak.set(value);
    }

    public final String getAgamak() {
        return agamak.get();
    }

    public final StringProperty agamakProperty() {
        return agamak;
    }

    public final void setAlamatk(String value) {
        alamatk.set(value);
    }

    public final String getAlamatk() {
        return alamatk.get();
    }

    public final StringProperty alamatkProperty() {
        return alamatk;
    }

    public final void setStkark(String value) {
        stkark.set(value);
    }

    public final String getStkark() {
        return stkark.get();
    }

    public final StringProperty stkarkProperty() {
        return stkark;
    }

    public final void setPendidikank(String value) {
        pendidikank.set(value);
    }

    public final String getPendidikank() {
        return pendidikank.get();
    }

    public final StringProperty pendidikankProperty() {
        return pendidikank;
    }

    public final void setTglmak(String value) {
        tglmak.set(value);
    }

    public final String getTglmak() {
        return tglmak.get();
    }

    public final StringProperty tglmakProperty() {
        return tglmak;
    }

    public final void setNohpk(String value) {
        nohpk.set(value);
    }

    public final String getNohpk() {
        return nohpk.get();
    }

    public final StringProperty nohpkProperty() {
        return nohpk;
    }

    public final void setIdklak(String value) {
        idklak.set(value);
    }

    public final String getIdklak() {
        return idklak.get();
    }

    public final StringProperty idklakProperty() {
        return idklak;
    }

    public final void setNoktpk(String value) {
        noktpk.set(value);
    }

    public final String getNoktpk() {
        return noktpk.get();
    }

    public final StringProperty noktpkProperty() {
        return noktpk;
    }

    public final void setNorekk(String value) {
        norekk.set(value);
    }

    public final String getNorekk() {
        return norekk.get();
    }

    public final StringProperty norekkProperty() {
        return norekk;
    }

    public final void setNobpjskenakerk(String value) {
        nobpjskenakerk.set(value);
    }

    public final String getNobpjskenakerk() {
        return nobpjskenakerk.get();
    }

    public final StringProperty nobpjskenakerkProperty() {
        return nobpjskenakerk;
    }

    public final void setNobpjskesehatank(String value) {
        nobpjskesehatank.set(value);
    }

    public final String getNobpjskesehatank() {
        return nobpjskesehatank.get();
    }

    public final StringProperty nobpjskesehatankProperty() {
        return nobpjskesehatank;
    }

    public final void setNpwpk(String value) {
        npwpk.set(value);
    }

    public final String getNpwpk() {
        return npwpk.get();
    }

    public final StringProperty npwpkProperty() {
        return npwpk;
    }

    public final void setEmailk(String value) {
        emailk.set(value);
    }

    public final String getEmailk() {
        return emailk.get();
    }

    public final StringProperty emailkProperty() {
        return emailk;
    }

    public final void setSuratk(String value) {
        suratk.set(value);
    }

    public final String getSuratk() {
        return suratk.get();
    }

    public final StringProperty suratkProperty() {
        return suratk;
    }

    public final void setFotoktpk(String value) {
        fotoktpk.set(value);
    }

    public final String getFotoktpk() {
        return fotoktpk.get();
    }

    public final StringProperty fotoktpkProperty() {
        return fotoktpk;
    }

    public final void setFotokkk(String value) {
        fotokkk.set(value);
    }

    public final String getFotokkk() {
        return fotokkk.get();
    }

    public final StringProperty fotokkkProperty() {
        return fotokkk;
    }

    public final void setFotoijasahk(String value) {
        fotoijasahk.set(value);
    }

    public final String getFotoijasahk() {
        return fotoijasahk.get();
    }

    public final StringProperty fotoijasahkProperty() {
        return fotoijasahk;
    }

    public final void setFotonpwpk(String value) {
        fotonpwpk.set(value);
    }

    public final String getFotonpwpk() {
        return fotonpwpk.get();
    }

    public final StringProperty fotonpwpkProperty() {
        return fotonpwpk;
    }

    public final void setFotobpjskenakerk(String value) {
        fotobpjskenakerk.set(value);
    }

    public final String getFotobpjskenakerk() {
        return fotobpjskenakerk.get();
    }

    public final StringProperty fotobpjskenakerkProperty() {
        return fotobpjskenakerk;
    }

    public final void setFotobpjssehatk(String value) {
        fotobpjssehatk.set(value);
    }

    public final String getFotobpjssehatk() {
        return fotobpjssehatk.get();
    }

    public final StringProperty fotobpjssehatkProperty() {
        return fotobpjssehatk;
    }

    public final void setPasfotok(String value) {
        pasfotok.set(value);
    }

    public final String getPasfotok() {
        return pasfotok.get();
    }

    public final StringProperty pasfotokProperty() {
        return pasfotok;
    }

    public final void setFotok(String value) {
        fotok.set(value);
    }

    public final String getFotok() {
        return fotok.get();
    }

    public final StringProperty fotokProperty() {
        return fotok;
    }

    
   
}
