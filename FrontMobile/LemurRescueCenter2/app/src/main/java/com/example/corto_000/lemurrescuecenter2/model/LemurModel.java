package com.example.corto_000.lemurrescuecenter2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corto_000 on 26/12/2016.
 */

public class LemurModel {

    private int idDB;
    private String name;
    private String idLemur;
    private String gender;
    private String birthDate;
    private String entryDate;
    private String origin;
    private String entryNature;
    private String lastOwner;
    private String leaveDate;
    private String leaveNature;
    private String leaveCommentary;
    private String weightDate;
    private final List<String> weight = new ArrayList<>();


    public  LemurModel(){

    }

    public LemurModel(int idDB, String name, String idLemur, String gender, String birthDate, String entryDate, String origin, String entryNature, String lastOwner, String leaveDate, String leaveNature, String leaveCommentary, String weightDate, List<String> weight ) {

        this.idDB = idDB;
        this.name = name;
        this.idLemur = idLemur;
        this.gender = gender;
        this.birthDate = birthDate;
        this.entryDate = entryDate;
        this.origin = origin;
        this.entryNature = entryNature;
        this.lastOwner = lastOwner;
        this.leaveDate = leaveDate;
        this.leaveNature = leaveNature;
        this.leaveCommentary = leaveCommentary;
    }

    public String getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(String weightDate) {
        this.weightDate = weightDate;
    }

    public List<String> getWeight() {
        return weight;
    }

    public int getIdDB() {
        return idDB;
    }
    public void setIdDB(int idDB) {
        this.idDB = idDB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdLemur() {
        return idLemur;
    }

    public void setIdLemur(String idLemur) {
        this.idLemur = idLemur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEntryNature() {
        return entryNature;
    }

    public void setEntryNature(String entryNature) {
        this.entryNature = entryNature;
    }

    public String getLastOwner() {
        return lastOwner;
    }

    public void setLastOwner(String lastOwner) {
        this.lastOwner = lastOwner;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveNature() {
        return leaveNature;
    }

    public void setLeaveNature(String leaveNature) {
        this.leaveNature = leaveNature;
    }

    public String getLeaveCommentary() {
        return leaveCommentary;
    }

    public void setLeaveCommentary(String leaveCommentary) {
        this.leaveCommentary = leaveCommentary;
    }

    public String getInfo() {
        StringBuilder s = new StringBuilder("Id : ")
                .append(idLemur)
                .append(" ")
                .append(" ")
                .append(gender);

        return s.toString();
    }
}
